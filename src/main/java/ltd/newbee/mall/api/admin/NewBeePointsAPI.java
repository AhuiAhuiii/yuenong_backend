package ltd.newbee.mall.api.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import ltd.newbee.mall.config.annotation.TokenToAdminUser;
import ltd.newbee.mall.entity.MallUser;
import ltd.newbee.mall.entity.MallUserToken;
import ltd.newbee.mall.entity.Points;
import ltd.newbee.mall.service.NewBeePointsService;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@Slf4j
@RestController
@Api(value = "v1" ,tags = "积分相关的接口")
@RequestMapping("/manage-api/v1")
public class NewBeePointsAPI {
    @Resource
    private NewBeePointsService newBeePointsService;

    //获取积分
    @GetMapping("/getPoints")
    @ApiOperation(value = "获取积分",notes = "获取积分数量")
    public Result<HashMap<String,Integer>> getPoints(@TokenToAdminUser MallUserToken mallUser){
        log.info("mallUser: {}",mallUser);

        Integer points= newBeePointsService.getPoints(mallUser.getUserId());

        HashMap<String,Integer> map =new HashMap<>();
        map.put("points",points);
        return ResultGenerator.genSuccessResult(map);
    }

    //改变积分
    @PostMapping("/updatePoints")
    @ApiOperation(value = "改变积分数量",notes="改变积分数量")
    public Result<HashMap<String,Integer>> updatePoints(@ApiParam Integer updatePoints,@TokenToAdminUser MallUserToken mallUser){
        log.info("mallUser: {} , updatePoints: {}",mallUser,updatePoints);
        Points points= new Points();
        points.setUserId(mallUser.getUserId());
        points.setPoints(updatePoints);

        boolean r = newBeePointsService.updateById(points);

        if(r){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("更新积分失败");
        }

    }


}
