package com.lagou.bootmybatis.mapper;

import com.lagou.bootmybatis.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 用户表(User)表数据库访问层
 *
 * @author Ye
 * @since 2022-01-03 23:03:23
 */
public interface UserMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

}

