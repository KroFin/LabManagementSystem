package com.iotdreamclub.demo.service;

import com.iotdreamclub.demo.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> selectRoleByAccount(String account);
    List<Role> selectAllRoleByTable();
}
