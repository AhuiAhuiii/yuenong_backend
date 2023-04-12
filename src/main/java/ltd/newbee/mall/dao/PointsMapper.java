package ltd.newbee.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ltd.newbee.mall.entity.Points;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PointsMapper extends BaseMapper<Points> {

    //按照userId获取积分
    @Select("select * from tb_newbee_mall_discounts where userId=#{userId}")
    public Points getPoints(Long userId);

    //按照userId添加积分
    @Insert("INSERT INTO tb_newbee_mall_discounts VALUES (#{pointsAdd},#{userId}})")
    public void addPoints(Long userId,Integer pointsAdd);

    //按照userId消耗积分
    @Update("UPDATE tb_newbee_mall_discounts set points=points+#{pointsReduce} where user_id=#{userId}")
    public void reducePoints(Long userId,Integer pointsReduce);
}
