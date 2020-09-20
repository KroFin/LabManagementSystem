package com.iotdreamclub.demo.service.impl;

import com.iotdreamclub.demo.dao.RoleModuleMapper;
import com.iotdreamclub.demo.entity.RoleModule;
import com.iotdreamclub.demo.service.RoleModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleModuleServiceImpl implements RoleModuleService {

    @Autowired
    private RoleModuleMapper roleModuleMapper;

    @Override
    public List<RoleModule> selectAllByLimit(String limit) {
        return roleModuleMapper.selectAllByLimit(limit);
    }
}
