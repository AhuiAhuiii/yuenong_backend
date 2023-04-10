/**
 *苦心人，天不负
 */
package ltd.newbee.mall.config.handler;

import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.common.NewBeeMallException;
import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.config.annotation.TokenToAdminUser;
import ltd.newbee.mall.dao.NewBeeAdminUserTokenMapper;
import ltd.newbee.mall.entity.AdminUserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class TokenToAdminUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private NewBeeAdminUserTokenMapper newBeeAdminUserTokenMapper;

    public TokenToAdminUserMethodArgumentResolver() {
    }

    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToAdminUser.class)) {
            return true;
        }
        return false;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        //寻找controller中的参数有@TokenToAdminUser注解的参数
        if (parameter.getParameterAnnotation(TokenToAdminUser.class) instanceof TokenToAdminUser) {
            //获取token的值
            String token = webRequest.getHeader("token");
            if (null != token && !"".equals(token) && token.length() == Constants.TOKEN_LENGTH) {
                AdminUserToken adminUserToken = newBeeAdminUserTokenMapper.selectByToken(token);
                if (adminUserToken == null) {  //没有对应token
                    NewBeeMallException.fail(ServiceResultEnum.ADMIN_NOT_LOGIN_ERROR.getResult());
                } else if (adminUserToken.getExpireTime().getTime() <= System.currentTimeMillis()) { //token过期
                    NewBeeMallException.fail(ServiceResultEnum.ADMIN_TOKEN_EXPIRE_ERROR.getResult());
                }
                return adminUserToken; //token正确
            } else { //token错误
                NewBeeMallException.fail(ServiceResultEnum.ADMIN_NOT_LOGIN_ERROR.getResult());
            }
        }
        return null;
    }

}
