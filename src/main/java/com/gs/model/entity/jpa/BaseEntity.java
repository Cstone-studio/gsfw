package com.gs.model.entity.jpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 30)
    private Long id;

    /**
     * 创建人
     */
    @Column(name = "create_user", length = 50)
    @CreatedBy
    private String createUser;

    @Column(name = "update_user", length = 50)
    @LastModifiedBy
    private String  updateUser;
    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "create_time")
    private Timestamp createTime;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "deleted")
    private Boolean deleted = false;
}