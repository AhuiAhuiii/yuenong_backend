package ltd.newbee.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ltd.newbee.mall.entity.Points;

public interface NewBeePointsService extends IService<Points> {
    //获取积分数量
   public Integer getPoints(Long userId) ;

   public  Integer addPOints(Long userId ,Integer pointsAdd);

   public Integer reducePoints(Long userId ,Integer pointsReduce);
}
