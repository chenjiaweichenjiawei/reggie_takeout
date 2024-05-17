package com.cjw.reggie_takeout.pojo;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 套餐菜品关系
 * </p>
 *
 * @author CJW
 * @since 2023-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SetmealDish对象", description = "套餐菜品关系")
public class SetmealDish implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "套餐id ")
    private Long setmealId;

    @ApiModelProperty(value = "菜品id")
    private Long dishId;

    @ApiModelProperty(value = "菜品名称 （冗余字段）")
    private String name;

    @ApiModelProperty(value = "菜品原价（冗余字段）")
    private BigDecimal price;

    @ApiModelProperty(value = "份数")
    private Integer copies;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除")
    @TableLogic
    private Integer isDeleted;


}
