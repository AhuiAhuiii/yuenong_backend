
package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.dao.AdminUserMapper;
import ltd.newbee.mall.dao.NewBeeAdminUserTokenMapper;
import ltd.newbee.mall.entity.AdminUser;
import ltd.newbee.mall.entity.AdminUserToken;
import ltd.newbee.mall.service.AdminUserService;
import ltd.newbee.mall.util.MD5Util;
import ltd.newbee.mall.util.NumberUtil;
import ltd.newbee.mall.util.SystemUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Resource
    private NewBeeAdminUserTokenMapper newBeeAdminUserTokenMapper;

    @Override
    public String login(String userName, String password) {
        AdminUser loginAdminUser = adminUserMapper.login(userName, password); //通过select进行数据库比对是否有根据username和password相同的数据
        if (loginAdminUser != null) {
            //登录后即执行修改token的操作
            String token = getNewToken(System.currentTimeMillis() + "", loginAdminUser.getAdminUserId()); //获取token
            AdminUserToken adminUserToken = newBeeAdminUserTokenMapper.selectByPrimaryKey(loginAdminUser.getAdminUserId()); //去user_token表中按照userId寻找相应的adminUserToken
            //当前时间
            Date now = new Date();
            //过期时间
            Date expireTime = new Date(now.getTime() + 2 * 24 * 3600 * 1000);//过期时间 48 小时
            //当发现这个用户在数据库的admin_user_token表中没有token的时候,为其创建一个新的token
            if (adminUserToken == null) {
                adminUserToken = new AdminUserToken();
                adminUserToken.setAdminUserId(loginAdminUser.getAdminUserId());
                adminUserToken.setToken(token);
                adminUserToken.setUpdateTime(now);
                adminUserToken.setExpireTime(expireTime);
                //新增一条token数据,新增token
                if (newBeeAdminUserTokenMapper.insertSelective(adminUserToken) > 0) {
                    //新增成功后返回
                    return token; //下放token
                }
            } else { //当已经是在admin_user_token表中有数据的时候怎将token进行更新
                adminUserToken.setToken(token);
                adminUserToken.setUpdateTime(now);
                adminUserToken.setExpireTime(expireTime);
                //更新
                if (newBeeAdminUserTokenMapper.updateByPrimaryKeySelective(adminUserToken) > 0) {
                    //修改成功后返回
                    return token;
                }
            }

        }
        return ServiceResultEnum.LOGIN_ERROR.getResult();
    }


    /**
     * 获取token值
     *
     * @param timeStr
     * @param userId
     * @return
     */
    private String getNewToken(String timeStr, Long userId) {
        String src = timeStr + userId + NumberUtil.genRandomNum(6);
        return SystemUtil.genToken(src);
    }


    @Override
    public AdminUser getUserDetailById(Long loginUserId) {
        return adminUserMapper.selectByPrimaryKey(loginUserId);
    }

    @Override
    public Boolean updatePassword(Long loginUserId, String originalPassword, String newPassword) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            //比较原密码是否正确
            if (originalPassword.equals(adminUser.getLoginPassword())) {
                //设置新密码并修改
                adminUser.setLoginPassword(newPassword);
                if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0 && newBeeAdminUserTokenMapper.deleteByPrimaryKey(loginUserId) > 0) {
                    //修改成功且清空当前token则返回true
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean updateName(Long loginUserId, String loginUserName, String nickName) {
        AdminUser adminUser = adminUserMapper.selectByPrimaryKey(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            //设置新名称并修改
            adminUser.setLoginUserName(loginUserName);
            adminUser.setNickName(nickName);
            if (adminUserMapper.updateByPrimaryKeySelective(adminUser) > 0) {
                //修改成功则返回true
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean logout(Long adminUserId) {
        return newBeeAdminUserTokenMapper.deleteByPrimaryKey(adminUserId) > 0;
    }
}
