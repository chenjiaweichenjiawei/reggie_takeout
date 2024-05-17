package com.cjw.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cjw.reggie_takeout.pojo.Category;
import com.cjw.reggie_takeout.mapper.CategoryMapper;
import com.cjw.reggie_takeout.pojo.Dish;
import com.cjw.reggie_takeout.pojo.Setmeal;
import com.cjw.reggie_takeout.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjw.reggie_takeout.service.DishService;
import com.cjw.reggie_takeout.service.SetmealService;
import com.cjw.reggie_takeout.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜品及套餐分类 服务实现类
 * </p>
 *
 * @author CJW
 * @since 2023-04-11
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    /**
     * 根据ids删除分类，删除之前要进行判断是否该分类下有商品
     *
     * @param ids
     */
    @Override
    public void remove(Long ids) {
        //查询当前分类是否有关联的菜品，有就抛出异常
        QueryWrapper<Dish> dishQueryWrapper = new QueryWrapper<>();
        dishQueryWrapper.eq("category_id", ids);
        int count = dishService.count(dishQueryWrapper);
        if (count > 0) {
            throw new CustomException("当前分类关联了菜品，无法删除");
        }
        //查询当前分类是否有关联的套餐，有就抛出异常
        QueryWrapper<Setmeal> setmealQueryWrapper = new QueryWrapper<>();
        setmealQueryWrapper.eq("category_id", ids);
        int count1 = setmealService.count(setmealQueryWrapper);
        if (count1 > 0) {
            throw new CustomException("当前分类关联了套餐，无法删除");
        }
        //正常删除
        super.removeById(ids);
    }
}
