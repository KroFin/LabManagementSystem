package com.iotdreamclub.demo.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iotdreamclub.demo.dao.RoleMapper;
import com.iotdreamclub.demo.dao.UserDao;
import com.iotdreamclub.demo.entity.Role;
import com.iotdreamclub.demo.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<Role> roles = roleMapper.selectRoleByAccount(username);
        roles.forEach(role -> info.addRole(role.getRoleName()));
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        User user = userDao.selectOne(new QueryWrapper<User>().eq("user_name",username));
        if (user == null){
            throw new UnknownAccountException("不存在用户");
        }
        return new SimpleAuthenticationInfo(username,user.getUserPassword(), ByteSource.Util.bytes(user.getUserName()),getName());
    }
}
