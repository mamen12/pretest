package com.general.apisubsaccount.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="mt_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable { 
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    private String id; 
    private String name; 
    private String email; 
    private String password;
    
    @Column(name = "created_at")
    private Date createdAt;
    
    @Column(name = "created_by")
    private String createdby;
    
    @Column(name = "updated_at")
    private Date updatedAt;
    
    @Column(name = "updated_by")
    private String updatedBy;
    
    @Column(name = "deleted_at")
    private Date deletedAt;
    
    @Column(name = "deleted_by")
    private String deletedBy;

    @Column(name = "last_login")
    private Date lastLogin;
}
