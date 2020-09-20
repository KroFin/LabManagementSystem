package com.iotdreamclub.demo.service;

import com.iotdreamclub.demo.entity.RoleModule;

import java.util.List;

public interface RoleModuleService {
    List<RoleModule> selectAllByLimit(String limit);
}
