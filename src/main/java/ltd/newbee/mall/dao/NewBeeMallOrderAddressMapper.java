/**
 *苦心人，天不负
 */
package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.NewBeeMallOrderAddress;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewBeeMallOrderAddressMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(NewBeeMallOrderAddress record);

    int insertSelective(NewBeeMallOrderAddress record);

    NewBeeMallOrderAddress selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(NewBeeMallOrderAddress record);

    int updateByPrimaryKey(NewBeeMallOrderAddress record);
}