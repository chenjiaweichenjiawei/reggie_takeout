package com.cjw.reggie_takeout.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjw.reggie_takeout.pojo.DishDTO;
import com.cjw.reggie_takeout.pojo.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cjw.reggie_takeout.pojo.SetmealDTO;

/**
 * <p>
 * 套餐 服务类
 * </p>
 *
 * @author CJW
 * @since 2023-04-11
 */
public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐
     * @param setmealDTO
     */
    public void addSetmeal(SetmealDTO setmealDTO);

    /**
     * 根据id返回套餐信息
     * @param id
     * @return
     */
    public SetmealDTO getByMyId(Long id);

    /**
     * 根据id删除套餐
     * @param id
     */
    public void deleteSetmealById(Long id);
}
