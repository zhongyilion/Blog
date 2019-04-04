package org.blog.dao.daoimpl;

import org.blog.DButil.BaseDB;
import org.blog.dao.UserDao;
import org.blog.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImp extends BaseDB implements UserDao {
    /**
     * 根据用户名和密码查询数据
     * @param name
     * @param psw
     * @return
     */
    @Override
    public User login(String name, String psw) {
        JdbcTemplate jdbcTemplate = jdbcTemplate();
        String sql = "select * from user where user_name = ? and password = ?";
        User user = null;
        try {
            user =  jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),name,psw);
        } catch (DataAccessException e) {

        }
        return user;
    }

    /**
     * 插入用户数据
     * @param user
     */
    @Override
    public void register(User user) {
        JdbcTemplate jdbcTemplate = jdbcTemplate();
        String sql = "insert into user values (null,?,?,?,?,?,?,?,'res/imgs/1.jpg')";
        jdbcTemplate.update(sql,user.getUserName(),user.getPassword(),user.getNickname(),user.getSex(),user.getAge(),user.getTelephone(),user.getEmail());
    }

    /**
     * 根据用户名查询用户，如果没查到返回空，jdbcTemplate一定要进行异常处理，不然会报错
     * @param name
     * @return
     */
    @Override
    public User queryByName(String name) {
        JdbcTemplate jdbcTemplate = jdbcTemplate();
        String sql = "select * from user where user_name = ?";
        User user=null;
        try{
           user =  jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),name);
        }catch (Exception e){
        }
        return user;
    }

    @Override
    public void editUserMessage(User user) {
        JdbcTemplate jdbcTemplate = jdbcTemplate();
        String sql = "update user set nickname=?,sex=?,age=?,telephone=?,email=? where user_id = ?";
        jdbcTemplate.update(sql,user.getNickname(),user.getSex(),user.getAge(),user.getTelephone(),user.getEmail(),user.getUserId());
    }
}
