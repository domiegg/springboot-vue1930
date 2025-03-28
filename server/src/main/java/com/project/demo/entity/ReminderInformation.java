package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 提醒信息：(ReminderInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ReminderInformation")
public class ReminderInformation implements Serializable {

    // ReminderInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reminder_information_id")
    private Integer reminder_information_id;

    // 标题名称
    @Basic
    private String title_name;
    // 用户信息
    @Basic
    private Integer user_information;
    // 用户姓名
    @Basic
    private String user_name;
    // 用户性别
    @Basic
    private String user_gender;
    // 用户年龄
    @Basic
    private String user_age;
    // 提醒内容
    @Basic
    private String reminder_content;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
