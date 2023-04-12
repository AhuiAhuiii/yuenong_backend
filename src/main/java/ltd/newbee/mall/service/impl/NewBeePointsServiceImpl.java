package ltd.newbee.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtQueryChainWrapper;
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import ltd.newbee.mall.dao.PointsMapper;
import ltd.newbee.mall.entity.Points;
import ltd.newbee.mall.service.NewBeePointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class NewBeePointsServiceImpl  implements NewBeePointsService {

   @Autowired
    private PointsMapper pointsMapper;

    @Override
    public Integer getPoints(Long userId) {

        return null;
    }

    @Override
    public Integer addPOints(Long userId, Integer pointsAdd) {
        return null;
    }

    @Override
    public Integer reducePoints(Long userId, Integer pointsReduce) {
        return null;
    }

    @Override
    public boolean saveBatch(Collection<Points> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Points> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Points> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Points entity) {
        return false;
    }

    @Override
    public Points getOne(Wrapper<Points> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Points> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Points> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Points> getBaseMapper() {
        return null;
    }

    @Override
    public Class<Points> getEntityClass() {
        return null;
    }
}
