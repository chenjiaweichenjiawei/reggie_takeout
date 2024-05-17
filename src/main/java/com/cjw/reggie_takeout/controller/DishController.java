package com.cjw.reggie_takeout.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjw.reggie_takeout.pojo.Category;
import com.cjw.reggie_takeout.pojo.Dish;
import com.cjw.reggie_takeout.pojo.DishDTO;
import com.cjw.reggie_takeout.pojo.DishFlavor;
import com.cjw.reggie_takeout.service.CategoryService;
import com.cjw.reggie_takeout.service.DishFlavorService;
import com.cjw.reggie_takeout.service.DishService;
import com.cjw.reggie_takeout.util.Result;
import com.mysql.cj.log.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜品管理 前端控制器
 * </p>
 *
 * @author CJW
 * @since 2023-04-11
 */
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private DishFlavorService dishFlavorService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 新增菜品
     *
     * @param dishDTO
     * @return
     */
    @PostMapping
    public Result<String> addDish(@RequestBody DishDTO dishDTO) {
        dishService.addWithFlavor(dishDTO);
        return Result.success("菜品添加成功");
    }

    @GetMapping("/page")
    public Result<Page> getByPage(Long page, Long pageSize, String name) {
        Page<Dish> page1 = new Page<>(page, pageSize);
        QueryWrapper<Dish> dishQueryWrapper = new QueryWrapper<>();
        dishQueryWrapper.like(name != null, "name", name);
        dishQueryWrapper.orderByDesc("update_time");
        Page<Dish> dishPage = dishService.page(page1, dishQueryWrapper);
        //注入分类名称
        Page<DishDTO> dishDTOPage = new Page<>();
        BeanUtils.copyProperties(dishPage, dishDTOPage, "records");
        List<Dish> records = dishPage.getRecords();
        List<DishDTO> list = new ArrayList<>();
        for (Dish dish : records) {
            //将dish拷贝到dishDTO并加上分类名称
            Long id = dish.getCategoryId();
            Category category = categoryService.getById(id);
            DishDTO dishDTO = new DishDTO();
            BeanUtils.copyProperties(dish, dishDTO);
            dishDTO.setCategoryName(category.getName());
            list.add(dishDTO);
        }
        dishDTOPage.setRecords(list);
        return Result.success(dishDTOPage);
    }

    @PostMapping("/status/0")
    public Result<String> updateStatus0(Long[] ids) {
        for (Long id : ids) {
            UpdateWrapper<Dish> dishUpdateWrapper = new UpdateWrapper<>();
            dishUpdateWrapper.eq("id", id);
            dishUpdateWrapper.set("status", 0);
            dishService.update(dishUpdateWrapper);
        }
        return Result.success("修改成功");
    }

    @PostMapping("/status/1")
    public Result<String> updateStatus1(Long[] ids) {
        for (Long id : ids) {
            UpdateWrapper<Dish> dishUpdateWrapper = new UpdateWrapper<>();
            dishUpdateWrapper.eq("id", id);
            dishUpdateWrapper.set("status", 1);
            dishService.update(dishUpdateWrapper);
        }
        return Result.success("修改成功");
    }

    @DeleteMapping
    public Result<String> deleteDish(Long[] ids) {
        for (Long id : ids) {
            dishService.removeById(id);
        }
        return Result.success("删除成功");
    }

    @GetMapping("/{id}")
    public Result<DishDTO> gteById(@PathVariable Long id) {
        Dish dish = dishService.getById(id);
        QueryWrapper<DishFlavor> dishFlavorQueryWrapper = new QueryWrapper<>();
        dishFlavorQueryWrapper.eq("dish_id", id);
        List<DishFlavor> dishFlavorList = dishFlavorService.list(dishFlavorQueryWrapper);
        DishDTO dishDTO = new DishDTO();
        BeanUtils.copyProperties(dish, dishDTO);
        dishDTO.setFlavors(dishFlavorList);
        Category category = categoryService.getById(dish.getCategoryId());
        dishDTO.setCategoryName(category.getName());
        return Result.success(dishDTO);
    }

    @PutMapping
    public Result<String> updateDish(@RequestBody DishDTO dishDTO){
        dishService.updateWithFlavor(dishDTO);
        return Result.success("修改成功");
    }

    @GetMapping("/list")
    public Result<List<Dish>> getDishList(Dish dish){
        QueryWrapper<Dish> dishQueryWrapper=new QueryWrapper<>();
        dishQueryWrapper.eq(dish.getCategoryId()!=null,"category_id",dish.getCategoryId());
        dishQueryWrapper.like(dish.getName()!=null,"name",dish.getName());
        dishQueryWrapper.eq("status",1);
        dishQueryWrapper.orderByAsc("sort").orderByDesc("update_time");
        List<Dish> dishList = dishService.list(dishQueryWrapper);
        return Result.success(dishList);
    }
}


















