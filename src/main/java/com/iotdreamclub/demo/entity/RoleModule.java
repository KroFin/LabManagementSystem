package com.iotdreamclub.demo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tb_role_module")
public class RoleModule {

  private String roleModuleId;
  private String roleModuleType;


  public String getRoleModuleId() {
    return roleModuleId;
  }

  public void setRoleModuleId(String roleModuleId) {
    this.roleModuleId = roleModuleId;
  }


  public String getRoleModuleType() {
    return roleModuleType;
  }

  public void setRoleModuleType(String roleModuleType) {
    this.roleModuleType = roleModuleType;
  }

}
