package com.smart.dao;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by liuhang on 2017/7/10.
 */
@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    private final static String MATCH_COUNT_SQL = "SELECT count(*) FROM t_user WHERE uer_name = ? and password = ?";
    private final static String UPDATE_LOGIN_INFO_SQL = "UPDATE t_user SET last_visit=?,last_ip=?,credits=? where " +
            "user_id=?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String userName, String password) {
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[]{userName, password}, Integer.class);
    }

    public User findUserByUserName(final String userName) {
        String sqlStr = "select user_id, user_name, credits from t_user where user_name = ?";
        final User user = new User();
        jdbcTemplate.query(sqlStr, new Object[]{userName}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(userName);
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

    public void updatLoginInfo(User user) {
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, new Object[]{user.getLastVisit(), user.getLastIp(), user.getCredits
                (), user.getUserId()});
    }
}
