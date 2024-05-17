package com.cjw.reggie_takeout.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjw.reggie_takeout.pojo.*;
import com.cjw.reggie_takeout.service.CategoryService;
import com.cjw.reggie_takeout.service.SetmealDishService;
import com.cjw.reggie_takeout.service.SetmealService;
import com.cjw.reggie_takeout.util.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 套餐 前端控制器
 * </p>
 *
 * @author CJW
 * @since 2023-04-11
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SetmealDishService setmealDishService;

    @PostMapping
    public Result<String> addSetMeal(@RequestBody SetmealDTO setmealDTO) {
        setmealService.addSetmeal(setmealDTO);
        return Result.success("新增成功");
    }

    @GetMapping("/page")
    public Result<Page<SetmealDTO>> page(Long page, Long pageSize, String name) {
        Page<Setmeal> page1 = new Page<>(page, pageSize);
        QueryWrapper<Setmeal> setmealQueryWrapper = new QueryWrapper<>();
        setmealQueryWrapper.like(name != null, "name", name);
        Page<Setmeal> page2 = setmealService.page(page1, setmealQueryWrapper);
        Page<SetmealDTO> page3 = new Page<>(page, pageSize);
        BeanUtils.copyProperties(page2, page3, "records");
        List<Setmeal> setmealsList = page2.getRecords();
        List<SetmealDTO> setmealDTOList = new ArrayList<>();
        for (Setmeal setmeal : setmealsList) {
            SetmealDTO setmealDTO = new SetmealDTO();
            BeanUtils.copyProperties(setmeal, setmealDTO);
            Category category = categoryService.getById(setmeal.getCategoryId());
            setmealDTO.setCategoryName(category.getName());
            setmealDTOList.add(setmealDTO);
        }
        page3.setRecords(setmealDTOList);
        return Result.success(page3);
    }

    @PostMapping("/status/0")
    public Result<String> setStatues0(Long[] ids) {
        for (Long id : ids) {
            UpdateWrapper<Setmeal> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", id);
            updateWrapper.set("status", 0);
            setmealService.update(updateWrapper);
        }
        return Result.success("修改成功");
    }


    @PostMapping("/status/1")
    public Result<String> setStatues1(Long[] ids) {
        for (Long id : ids) {
            UpdateWrapper<Setmeal> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", id);
            updateWrapper.set("status", 1);
            setmealService.update(updateWrapper);
        }
        return Result.success("修改成功");
    }

    @GetMapping("/{id}")
    public Result<SetmealDTO> getById(@PathVariable Long id) {
        SetmealDTO setmealDTO = setmealService.getByMyId(id);
        return Result.success(setmealDTO);
    }

    @DeleteMapping
    public Result<String> deleteById(Long[] ids) {
        for (Long id : ids) {
            setmealService.deleteSetmealById(id);
        }
        return Result.success("删除成功");
    }

    @Transactional(rollbackFor = Exception.class)
    @PutMapping
    public Result<String> updateSetmeal(@RequestBody SetmealDTO setmealDTO) {
        setmealService.deleteSetmealById(setmealDTO.getId());
        setmealDTO.setId(null);
        setmealService.addSetmeal(setmealDTO);
        return Result.success("更新成功");
    }
}
