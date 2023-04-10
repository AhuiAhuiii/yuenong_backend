/**
 *苦心人，天不负
 */
package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.MallUserToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewBeeMallUserTokenMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(MallUserToken record);

    int insertSelective(MallUserToken record);

    MallUserToken selectByPrimaryKey(Long userId);

    MallUserToken selectByToken(String token);

    int updateByPrimaryKeySelective(MallUserToken record);

    int updateByPrimaryKey(MallUserToken record);
}