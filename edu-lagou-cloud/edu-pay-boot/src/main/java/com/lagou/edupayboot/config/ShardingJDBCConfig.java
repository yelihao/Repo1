package com.lagou.edupayboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShardingJDBCConfig {
    //定义数据源
    Map<String, DataSource> createDataSource(){
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/edu_order?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("123");
        Map<String,DataSource> map = new HashMap<>();
        map.put("ds1",ds);
        return map;
    }

    //定义主建生产策略
    private static KeyGeneratorConfiguration getKeyGeneratorConfiguration(){
        KeyGeneratorConfiguration key = new KeyGeneratorConfiguration("SNOWFLAKE","id");
        return key;
    }

    //定义表的分片策略----- user_course_order_0-1
    TableRuleConfiguration getTableRuleConfiguration(){
        TableRuleConfiguration rule = new TableRuleConfiguration
                ("user_course_order","ds1.user_course_order_$->{0..9}");
        rule.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration
                ("id","user_course_order_$->{id%2+1}"));
        rule.setKeyGeneratorConfig(getKeyGeneratorConfiguration()); //设置主健策略
        return rule;
    }

    //定义sharding_jdbc数据源
    @Bean
    public DataSource GetShardingDataSource()throws SQLException{
        ShardingRuleConfiguration config = new ShardingRuleConfiguration();
        config.getTableRuleConfigs().add(getTableRuleConfiguration());
        Properties pp = new Properties();
        pp.put("sql.show",true);
        //1.数据源 2.分片配置 3.其他配置
        return ShardingDataSourceFactory.createDataSource(createDataSource(),config,pp);
    }



}
