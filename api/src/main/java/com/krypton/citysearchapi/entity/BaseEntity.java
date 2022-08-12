package com.krypton.citysearchapi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Date;

/*
Apply BaseEntity for the Audit Purpose Columns
 */
@MappedSuperclass
public class BaseEntity {
    @Column(name = "created_by")
    protected String createdBy;

    @Column(name = "created_date")
    protected Date createdDate;

    @Column(name = "updated_by")
    protected String updatedBy;

    @Column(name = "updated_date")
    protected Date updatedDate;

}
