package com.nhnacademy.security.config;

import com.nhnacademy.security.Base;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = Base.class,
    excludeFilters = @ComponentScan.Filter(Controller.class))
public class RootConfig {

  @Bean
  public DataSource dataSource() { //db connection pooling하기위함
    BasicDataSource dataSource = new BasicDataSource();

    dataSource.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
    dataSource.setUrl("jdbc:mysql://133.186.151.141:3306/nhn_academy_46");
    dataSource.setUsername("nhn_academy_46");
    dataSource.setPassword("s193!UhKNBc3X9(8");

    dataSource.setInitialSize(10);
    dataSource.setMaxTotal(10);
    dataSource.setMinIdle(10);
    dataSource.setMaxIdle(10);

    dataSource.setMaxWaitMillis(1000);

    dataSource.setTestOnBorrow(true);
    dataSource.setTestOnReturn(true);
    dataSource.setTestWhileIdle(true);

    return dataSource;
  }

//  @Bean
//  public UserRepository userRepository(){
//    UserRepositoryCustom userRepositoryCustom = new UserRepositoryImpl();
//    userRepositoryCustom.addUser(1L, "user", "1234");
//  }

}
