package com.cjw.reggie_takeout.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 地址管理
 * </p>
 *
 * @author CJW
 * @since 2023-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AddressBook对象", description="地址管理")
public class AddressBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "收货人")
    private String consignee;

    @ApiModelProperty(value = "性别 0 女 1 男")
    private Integer sex;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "省级区划编号")
    private String provinceCode;

    @ApiModelProperty(value = "省级名称")
    private String provinceName;

    @ApiModelProperty(value = "市级区划编号")
    private String cityCode;

    @ApiModelProperty(value = "市级名称")
    private String cityName;

    @ApiModelProperty(value = "区级区划编号")
    private String districtCode;

    @ApiModelProperty(value = "区级名称")
    private String districtName;

    @ApiModelProperty(value = "详细地址")
    private String detail;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "默认 0 否 1是")
    private Boolean isDefault;

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
