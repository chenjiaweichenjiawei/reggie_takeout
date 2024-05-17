package com.cjw.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cjw.reggie_takeout.pojo.Dish;
import com.cjw.reggie_takeout.mapper.DishMapper;
import com.cjw.reggie_takeout.pojo.DishDTO;
import com.cjw.reggie_takeout.pojo.DishFlavor;
import com.cjw.reggie_takeout.service.DishFlavorService;
import com.cjw.reggie_takeout.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjw.reggie_takeout.util.CustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 菜品管理 服务实现类
 * </p>
 *
 * @author CJW
 * @since 2023-04-11
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addWithFlavor(DishDTO dishDTO) {
        QueryWrapper<Dish> dishQueryWrapper = new QueryWrapper<>();
        dishQueryWrapper.eq("name", dishDTO.getName());
        Dish one = this.getOne(dishQueryWrapper);
        if (one != null) {
            throw new CustomException("菜品名称重复");
        }
        //保存菜品
        boolean save = this.save(dishDTO);
        //取出菜品id
        Long dishId = dishDTO.getId();
        //保存口味
        List<DishFlavor> dishFlavorList = dishDTO.getFlavors();
        for (DishFlavor dishFlavor : dishFlavorList) {
            dishFlavor.setDishId(dishId);
        }
        dishFlavorService.saveBatch(dishFlavorList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateWithFlavor(DishDTO dishDTO) {
        //更新Dish
        this.updateById(dishDTO);
        //清理相关Flavor
        QueryWrapper<DishFlavor> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("dish_id",dishDTO.getId());
        dishFlavorService.remove(queryWrapper);
        //插入新的口味
        for(DishFlavor dishFlavor:dishDTO.getFlavors()){
            dishFlavor.setDishId(dishDTO.getId());
            dishFlavor.setId(null);
        }
        dishFlavorService.saveBatch(dishDTO.getFlavors());
    }
}
