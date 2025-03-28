package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 备忘录信息：(MemorandumInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "MemorandumInformation")
public class MemorandumInformation implements Serializable {

    // MemorandumInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memorandum_information_id")
    private Integer memorandum_information_id;

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
    // 内容详情
    @Basic
    private String content_details;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
