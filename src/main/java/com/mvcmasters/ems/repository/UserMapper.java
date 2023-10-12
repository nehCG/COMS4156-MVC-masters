package com.mvcmasters.ems.repository;

import com.mvcmasters.ems.base.BaseMapper;
import com.mvcmasters.ems.vo.User;

public interface UserMapper extends BaseMapper<User, Integer> {

    public User queryUserByName(String userName);
}