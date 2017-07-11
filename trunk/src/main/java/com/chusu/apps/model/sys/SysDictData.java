package com.chusu.apps.model.sys;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_dict_data")
public class SysDictData {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 数据类别ID
     */
    @Column(name = "dict_code")
    private String dictCode;

    /**
     * 创建时间
     */
    @Column(name = "dictdata_value")
    private String dictdataValue;

    /**
     * 创建时间
     */
    @Column(name = "dictdata_name")
    private String dictdataName;

    /**
     * 是否可读
     */
    @Column(name = "is_fixed")
    private Integer isFixed;

    /**
     * 排序
     */
    @Column(name = "order_no")
    private Integer orderNo;

    /**
     * 修改时间
     */
    private String description;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 是否禁用，1是0否
     */
    @Column(name = "is_visible")
    private Integer isVisible;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取数据类别ID
     *
     * @return dict_code - 数据类别ID
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * 设置数据类别ID
     *
     * @param dictCode 数据类别ID
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    /**
     * 获取创建时间
     *
     * @return dictdata_value - 创建时间
     */
    public String getDictdataValue() {
        return dictdataValue;
    }

    /**
     * 设置创建时间
     *
     * @param dictdataValue 创建时间
     */
    public void setDictdataValue(String dictdataValue) {
        this.dictdataValue = dictdataValue;
    }

    /**
     * 获取创建时间
     *
     * @return dictdata_name - 创建时间
     */
    public String getDictdataName() {
        return dictdataName;
    }

    /**
     * 设置创建时间
     *
     * @param dictdataName 创建时间
     */
    public void setDictdataName(String dictdataName) {
        this.dictdataName = dictdataName;
    }

    /**
     * 获取是否可读
     *
     * @return is_fixed - 是否可读
     */
    public Integer getIsFixed() {
        return isFixed;
    }

    /**
     * 设置是否可读
     *
     * @param isFixed 是否可读
     */
    public void setIsFixed(Integer isFixed) {
        this.isFixed = isFixed;
    }

    /**
     * 获取排序
     *
     * @return order_no - 排序
     */
    public Integer getOrderNo() {
        return orderNo;
    }

    /**
     * 设置排序
     *
     * @param orderNo 排序
     */
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取修改时间
     *
     * @return description - 修改时间
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置修改时间
     *
     * @param description 修改时间
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取是否禁用，1是0否
     *
     * @return is_visible - 是否禁用，1是0否
     */
    public Integer getIsVisible() {
        return isVisible;
    }

    /**
     * 设置是否禁用，1是0否
     *
     * @param isVisible 是否禁用，1是0否
     */
    public void setIsVisible(Integer isVisible) {
        this.isVisible = isVisible;
    }
}