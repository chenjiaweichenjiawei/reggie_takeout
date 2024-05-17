package com.cjw.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cjw.reggie_takeout.pojo.*;
import com.cjw.reggie_takeout.mapper.SetmealMapper;
import com.cjw.reggie_takeout.service.CategoryService;
import com.cjw.reggie_takeout.service.SetmealDishService;
import com.cjw.reggie_takeout.service.SetmealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 套餐 服务实现类
 * </p>
 *
 * @author CJW
 * @since 2023-04-11
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;
    @Autowired
    private CategoryService categoryService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addSetmeal(SetmealDTO setmealDTO) {
        //保存套餐基本信息
        this.save(setmealDTO);
        //保存套餐含有的菜品
        List<SetmealDish> setmealDishList = setmealDTO.getSetmealDishes();
        for (SetmealDish setmealDish : setmealDishList) {
            setmealDish.setSetmealId(setmealDTO.getId());
            setmealDishService.save(setmealDish);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SetmealDTO getByMyId(Long id) {
        SetmealDTO setmealDTO=new SetmealDTO();
        Setmeal setmeal=this.getById(id);
        BeanUtils.copyProperties(setmeal,setmealDTO);
        Category category=categoryService.getById(setmeal.getCategoryId());
        setmealDTO.setCategoryName(category.getName());
        QueryWrapper<SetmealDish> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("setmeal_id",setmeal.getId());
        List<SetmealDish> list = setmealDishService.list(queryWrapper);
        setmealDTO.setSetmealDishes(list);
        return setmealDTO;
    }

    @Override
    public void deleteSetmealById(Long id) {
        this.removeById(id);
        QueryWrapper<SetmealDish> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("setmeal_id",id);
        setmealDishService.remove(queryWrapper);
    }
}













