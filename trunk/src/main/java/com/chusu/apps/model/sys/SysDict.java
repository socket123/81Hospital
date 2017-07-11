package com.chusu.apps.model.sys;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_dict")
public class SysDict {
    /**
     * 主键
     */
    @Id
    @Column(name = "dict_code")
    private String dictCode;

    /**
     * 字典名字
     */
    @Column(name = "dict_name")
    private String dictName;

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
     * @return dict_code - 主键
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * 设置主键
     *
     * @param dictCode 主键
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    /**
     * 获取字典名字
     *
     * @return dict_name - 字典名字
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * 设置字典名字
     *
     * @param dictName 字典名字
     */
    public void setDictName(String dictName) {
        this.dictName = dictName;
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