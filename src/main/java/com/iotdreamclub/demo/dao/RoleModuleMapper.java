package com.iotdreamclub.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iotdreamclub.demo.entity.RoleModule;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleModuleMapper extends BaseMapper<RoleModule> {
    List<RoleModule> selectAllByLimit(String limit);
}
