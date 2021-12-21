package com.lagou.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * user_course_order
 * @author 
 */
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 课程id，根据订单中的课程类型来选择
     */
    private Integer courseId;

    /**
     * 活动课程id
     */
    private Integer activityCourseId;

    /**
     * 订单来源类型: 1 用户下单购买 2 后台添加专栏
     */
    private Byte sourceType;

    /**
     * 当前状态: 0已创建 10未支付 20已支付 30已取消 40已过期 
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Byte isDel;

    private static final long serialVersionUID = 1L;
}