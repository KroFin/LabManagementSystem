package com.iotdreamclub.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tb_role")
public class Role {

  private long roleId;
  private String roleName;
  private String roleType;


  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }


  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }


  public String getRoleType() {
    return roleType;
  }

  public void setRoleType(String roleType) {
    this.roleType = roleType;
  }

}
