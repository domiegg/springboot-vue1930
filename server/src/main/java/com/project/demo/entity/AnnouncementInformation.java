package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 公告信息：(AnnouncementInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "AnnouncementInformation")
public class AnnouncementInformation implements Serializable {

    // AnnouncementInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_information_id")
    private Integer announcement_information_id;

    // 公告编号
    @Basic
    private String announcement_no;
    // 公告标题
    @Basic
    private String announcement_title;
    // 发布人
    @Basic
    private String publisher;
    // 发布日期
    @Basic
    private Timestamp release_date;
    // 公告内容
    @Basic
    private String announcement_content;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
