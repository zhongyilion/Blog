package org.blog.DButil;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class BaseDB {
    /**
     * 获取jdbcTemplate方便操作数据库
     * @return
     */
    public JdbcTemplate jdbcTemplate(){
//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
//        JdbcTemplate jdbcTemplate = (JdbcTemplate)ctx.getBean("jdbcTemplate");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return jdbcTemplate;
    }

    /**
     * 获取数据库连接
     * @return
     */
    public Connection getConnection(){
        Connection con = null;
        try {
            con = getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }


    /**
     * 通过配置文件生成数据源
     * @return
     */
    public DataSource getDataSource(){
        DataSource dataSource = null;
        Properties properties = new Properties();
        try{
            properties.load(BaseDB.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
