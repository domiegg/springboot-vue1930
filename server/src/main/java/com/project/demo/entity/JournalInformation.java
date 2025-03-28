package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 日记信息：(JournalInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "JournalInformation")
public class JournalInformation implements Serializable {

    // JournalInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "journal_information_id")
    private Integer journal_information_id;

    // 日记编号
    @Basic
    private String journal_number;
    // 日记名称
    @Basic
    private String journal_name;
    // 日记标签
    @Basic
    private String journal_label;
    // 日记分类
    @Basic
    private String journal_classification;
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
    // 日记数量
    @Basic
    private String number_of_journals;
    // 日记日期
    @Basic
    private Timestamp journal_date;
    // 日记图片
    @Basic
    private String journal_picture;
    // 日记音频
    @Basic
    private String journal_audio;
    // 日记视频
    @Basic
    private String journal_video;
    // 日记内容
    @Basic
    private String journal_content;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
