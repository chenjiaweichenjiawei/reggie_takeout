package com.cjw.reggie_takeout.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjw.reggie_takeout.pojo.Category;
import com.cjw.reggie_takeout.service.CategoryService;
import com.cjw.reggie_takeout.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜品及套餐分类 前端控制器
 * </p>
 *
 * @author CJW
 * @since 2023-04-11
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增
     *
     * @param category
     * @return
     */
    @PostMapping
    public Result<String> insertCategory(@RequestBody Category category) {
        categoryService.save(category);
        return Result.success("新增分类成功");
    }

    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result<Page<Category>> getByPage(Long page, Long pageSize) {
        Page<Category> page1 = new Page<>(page, pageSize);
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        categoryService.page(page1, queryWrapper);
        return Result.success(page1);
    }

    @DeleteMapping
    public Result<String> deleteCategory(Long ids) {
        categoryService.remove(ids);
        return Result.success("分类删除成功");
    }

    @PutMapping
    public Result<String> update(@RequestBody Category category) {
        categoryService.updateById(category);
        return Result.success("修改分类成功");
    }

    @GetMapping("/list")
    public Result<List<Category>> getCategoryListByType(Integer type) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        //根据sort升序排列，后按照更新时间降序排列
        queryWrapper.orderByAsc("sort").orderByDesc("update_time");
        List<Category> list = categoryService.list(queryWrapper);
        return Result.success(list);
    }
}







