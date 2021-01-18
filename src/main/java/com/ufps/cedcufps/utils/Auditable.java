package com.ufps.cedcufps.utils;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T> {

	
    @CreatedBy
    @Column(name="created_by")
    protected T createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at")
    @CreatedDate
    protected Date createdDate;

    @Column(name="updated_by")
    @LastModifiedBy
    protected T updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name="updated_at")
    protected Date updatedAt;

    public T getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(T createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

	public T getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(T updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

    
}
