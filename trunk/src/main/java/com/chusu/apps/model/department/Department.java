package com.chusu.apps.model.department;

import com.chusu.apps.model.type.Type;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "department_introduce")
    private String departmentIntroduce;



    @Transient
    private List<Type> typeList;


    @Transient
    private Integer  typeId;


    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public List<Type> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Type> typeList) {
        this.typeList = typeList;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return department_name
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return department_introduce
     */
    public String getDepartmentIntroduce() {
        return departmentIntroduce;
    }

    /**
     * @param departmentIntroduce
     */
    public void setDepartmentIntroduce(String departmentIntroduce) {
        this.departmentIntroduce = departmentIntroduce;
    }
}