package com.stefanopisano.dashboard.dashboard.events.service;

import com.stefanopisano.dashboard.dashboard.events.dto.TemplateDto;

import java.util.Collection;
import java.util.UUID;

public interface EventsService {
	Collection<TemplateDto> findTemplatesById(UUID id);

	void saveTemplateForUserId(UUID userId, TemplateDto toSave);

	void deleteTemplateById(UUID templateID);
}
