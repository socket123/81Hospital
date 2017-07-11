package com.chusu.apps.model.type;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "type_des")
    private String typeDes;




    /**
     * 报警阀
     */
    private String police;

    /**
     * 种类类别 1、消耗品 2、非消耗品
     */
    private String status;



    @Transient
    private Integer numbers;


    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }




    /**
     * 获取种类类别 1、消耗品 2、非消耗品
     *
     * @return status - 种类类别 1、消耗品 2、非消耗品
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置种类类别 1、消耗品 2、非消耗品
     *
     * @param status 种类类别 1、消耗品 2、非消耗品
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取报警阀
     *
     * @return police - 报警阀
     */
    public String getPolice() {
        return police;
    }

    /**
     * 设置报警阀
     *
     * @param police 报警阀
     */
    public void setPolice(String police) {
        this.police = police;
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
     * @return type_name
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
     * @return type_des
     */
    public String getTypeDes() {
        return typeDes;
    }

    /**
     * @param typeDes
     */
    public void setTypeDes(String typeDes) {
        this.typeDes = typeDes;
    }
}