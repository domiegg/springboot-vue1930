package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 * 分类管理：(ClassificationManagement)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ClassificationManagement")
public class ClassificationManagement implements Serializable {

    // ClassificationManagement编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classification_management_id")
    private Integer classification_management_id;

    // 日记分类
    @Basic
    private String journal_classification;














    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
