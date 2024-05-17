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
 * 菜品管理
 * </p>
 *
 * @author CJW
 * @since 2023-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Dish对象", description = "菜品管理")
public class Dish implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "菜品名称")
    private String name;

    @ApiModelProperty(value = "菜品分类id")
    private Long categoryId;

    @ApiModelProperty(value = "菜品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "商品码")
    private String code;

    @ApiModelProperty(value = "图片")
    private String image;

    @ApiModelProperty(value = "描述信息")
    private String description;

    @ApiModelProperty(value = "0 停售 1 起售")
    private Integer status;

    @ApiModelProperty(value = "顺序")
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
