package com.chusu.apps.model.sys;

import javax.persistence.*;

@Table(name = "sys_admin_role")
public class SysAdminRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "admin_id")
    private String adminId;

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
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return admin_id
     */
    public String getAdminId() {
        return adminId;
    }

    /**
     * @param adminId
     */
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}