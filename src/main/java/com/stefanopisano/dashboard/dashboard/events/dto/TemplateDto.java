package com.stefanopisano.dashboard.dashboard.events.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateDto {

	private UUID id;
	private String eventName;
	private String eventDescription;
	private Date fromDate;
	private Date toDate;
}
