package com.cjw.reggie_takeout.service;

import com.cjw.reggie_takeout.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 菜品及套餐分类 服务类
 * </p>
 *
 * @author CJW
 * @since 2023-04-11
 */
public interface CategoryService extends IService<Category> {
    /**
     * 删除分类
     *
     * @param ids
     */
    public void remove(Long ids);
}
