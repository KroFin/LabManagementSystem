package com.iotdreamclub.demo.service.impl;

import com.iotdreamclub.demo.dao.RoleMapper;
import com.iotdreamclub.demo.entity.Role;
import com.iotdreamclub.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectRoleByAccount(String account) {
        return roleMapper.selectRoleByAccount(account);
    }

    @Override
    public List<Role> selectAllRoleByTable() {
        return roleMapper.selectAllRoleByTable();
    }
}
