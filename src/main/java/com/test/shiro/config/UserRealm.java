package com.test.shiro.config;

import com.test.shiro.dao.UserMapper;
import com.test.shiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author gaohaojie
 * @Date 2019/7/12
 * @Des:
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;
    //执行授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权执行+++++++++++++++++++++");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //给用户添加权限
//        info.addStringPermission("user:add");
//        到数据库查询登录用户的权限信息
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        User user1 = userMapper.selectByPrimaryKey(user.getId());
        info.addStringPermission(user1.getPerms());
        return info;
    }
    //执行认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证执行+++++++++++++++++++++");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //TODO：从数据库中获取数据
        String name = token.getUsername();
        User user = userMapper.selectByAccount(name);
//        String name = "tom";
//        String password = "123";
//        System.out.println(name);
//        System.out.println(user.getPassword());
        //shiro判断逻辑，判断用户名和密码
        //判断用户名

        if (user==null){
            //用户名不存在
            return null;//shiro地城会抛出UnknowAccountException
        }
        //2.判断密码
        return new SimpleAuthenticationInfo("",user.getPassword(),"");

    }
}
