package com.cjw.reggie_takeout.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author CJW
 * @date 2023/4/14
 */
@Data
public class SetmealDTO extends Setmeal {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜品分类id")
    private Long categoryId;

    @ApiModelProperty(value = "套餐名称")
    private String name;

    @ApiModelProperty(value = "套餐价格")
    private BigDecimal price;

    @ApiModelProperty(value = "状态 0:停用 1:启用")
    private Integer status;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "描述信息")
    private String description;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty("套餐含有的菜品")
    private List<SetmealDish> setmealDishes;

    @ApiModelProperty("套餐分类名称")
    private String categoryName;
}
