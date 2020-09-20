package com.iotdreamclub.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iotdreamclub.demo.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends BaseMapper<RoleMapper> {
    List<Role> selectRoleByAccount(String account);
}
