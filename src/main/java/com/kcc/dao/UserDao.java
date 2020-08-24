package com.kcc.dao;

import com.kcc.pojo.po.UserPO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserPO loadUserByUsername(String username) {

        List<UserPO> userList = jdbcTemplate.query("select * from [user] where username = ?",
                new Object[]{username},
                new BeanPropertyRowMapper<>(UserPO.class));

        if (userList != null && userList.size() == 1) {
            return userList.get(0);
        } else {
            return null;
        }
    }

}
