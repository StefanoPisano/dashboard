package com.stefanopisano.dashboard.dashboard.events.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="templates")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Template {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private UUID user;
	private Date fromDate;
	private Date toDate;
	private String eventName;
	private String eventDescription;
}
