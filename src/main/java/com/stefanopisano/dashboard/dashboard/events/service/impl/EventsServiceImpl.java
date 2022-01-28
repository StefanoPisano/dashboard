package com.stefanopisano.dashboard.dashboard.events.service.impl;

import com.stefanopisano.dashboard.dashboard.events.dao.EventsTemplateRepository;
import com.stefanopisano.dashboard.dashboard.events.dto.TemplateDto;
import com.stefanopisano.dashboard.dashboard.events.model.Template;
import com.stefanopisano.dashboard.dashboard.events.service.EventsService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventsServiceImpl implements EventsService {

	@Setter(onMethod = @__({@Autowired}))
	private EventsTemplateRepository eventsTemplateRepository;

	@Override
	public Collection<TemplateDto> findTemplatesById(UUID id) {
		return eventsTemplateRepository.findAllByUser(id).stream()
									   .map(v -> TemplateDto.builder()
															.id(v.getId())
															.eventName(v.getEventName())
															.eventDescription(v.getEventDescription())
															.fromDate(v.getFromDate())
															.toDate(v.getToDate())
															.build())
									   .collect(Collectors.toList());
	}

	@Override
	public void saveTemplateForUserId(UUID userId, TemplateDto toSave) {
		Assert.hasLength(toSave.getEventName(), "Please provide a non-empty event name");
		Assert.notNull(userId, "User ID must be not null");

		eventsTemplateRepository.save(Template.builder()
											  .user(userId)
											  .eventName(toSave.getEventName())
											  .eventDescription(toSave.getEventDescription())
											  .fromDate(toSave.getFromDate())
											  .toDate(toSave.getFromDate())
											  .build());
	}

	@Override
	public void deleteTemplateById(UUID templateID) {
		Assert.notNull(templateID, "Template ID must be not null");

		eventsTemplateRepository.deleteById(templateID);

	}
}
