package com.cjw.reggie_takeout.service;

import com.cjw.reggie_takeout.pojo.Dish;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cjw.reggie_takeout.pojo.DishDTO;

/**
 * <p>
 * 菜品管理 服务类
 * </p>
 *
 * @author CJW
 * @since 2023-04-11
 */
public interface DishService extends IService<Dish> {
    /**
     * 保存菜品和菜品口味
     *
     * @param dishDTO
     */
    public void addWithFlavor(DishDTO dishDTO);

    /**
     * 更新菜品信息和口味信息
     * @param dishDTO
     */
    public void updateWithFlavor(DishDTO dishDTO);
}
