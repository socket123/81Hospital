package com.chusu.apps.model.order;

import java.util.Date;
import javax.persistence.*;

@Table(name = "orders")
public class Orders {
    /**
     * 订单表
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 0:医院内部采购，1:医院外部采购
     */
    @Column(name = "order_type")
    private String orderType;

    /**
     * 部门 关联 id
     */
    @Column(name = "department_id")
    private Integer departmentId;

    /**
     * 种类id
     */
    @Column(name = "type_id")
    private Integer typeId;

    /**
     * 申请或采购时间
     */
    private Date time;

    /**
     * 状态
     */
    private String status;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 订单备注
     */
    @Column(name = "order_des")
    private String orderDes;



    @Transient
    private  String department_name ;

    @Transient
    private  String type_name;
    @Transient
    private  Integer  numbers;

    @Transient
    private  Integer  type_Id;

    @Transient
    private  Integer  police;


    public Integer getPolice() {
        return police;
    }

    public void setPolice(Integer police) {
        this.police = police;
    }

    public Integer getType_Id() {
        return type_Id;
    }

    public void setType_Id(Integer type_Id) {
        this.type_Id = type_Id;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    /**
     * 获取订单表
     *
     * @return id - 订单表
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置订单表
     *
     * @param id 订单表
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取0:医院内部采购，1:医院外部采购
     *
     * @return order_type - 0:医院内部采购，1:医院外部采购
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 设置0:医院内部采购，1:医院外部采购
     *
     * @param orderType 0:医院内部采购，1:医院外部采购
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取部门 关联 id
     *
     * @return department_id - 部门 关联 id
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置部门 关联 id
     *
     * @param departmentId 部门 关联 id
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 获取种类id
     *
     * @return type_id - 种类id
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 设置种类id
     *
     * @param typeId 种类id
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取申请或采购时间
     *
     * @return time - 申请或采购时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置申请或采购时间
     *
     * @param time 申请或采购时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取数量
     *
     * @return number - 数量
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * 设置数量
     *
     * @param number 数量
     */
    public void setNumber(Integer number) {
        this.number = number;
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
     * 获取订单备注
     *
     * @return order_des - 订单备注
     */
    public String getOrderDes() {
        return orderDes;
    }

    /**
     * 设置订单备注
     *
     * @param orderDes 订单备注
     */
    public void setOrderDes(String orderDes) {
        this.orderDes = orderDes;
    }
}