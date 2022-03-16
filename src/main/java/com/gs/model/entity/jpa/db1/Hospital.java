package com.gs.model.entity.jpa.db1;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gs.model.entity.jpa.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name="hospital")
public class Hospital extends BaseEntity {

    /**
     * 用户
     */
    @OneToOne(targetEntity = User.class, cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 医院名称
     */
    @Column(name = "name")
    private String name;
}
