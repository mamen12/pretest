package com.general.apisubsaccount.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_payload_req_res")
public class PayloadProperties {
	
	@Id
	@Column(name = "id_payload")
	private String idPayload;
	
	@Column(name = "id_request")
	private String idRequest;
	
	private String url;
	@Lob
	@Column(length = 10000, name = "payload_request")
	private String payloadRequest;
	@Lob
	@Column(length = 10000, name = "payload_response")
	private String payloadResponse;
	
	@Column(name = "time_created")
	private Date timeCreated;
	
	private String username;
}