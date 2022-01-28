package com.stefanopisano.dashboard.dashboard.events;

import com.stefanopisano.dashboard.dashboard.auth.currentUser.CurrentUser;
import com.stefanopisano.dashboard.dashboard.events.dto.TemplateDto;
import com.stefanopisano.dashboard.dashboard.events.service.EventsService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventsApiController {

	@Setter(onMethod = @__({@Autowired}))
	private CurrentUser currentUser;

	@Setter(onMethod = @__({@Autowired}))
	private EventsService eventsService;

	@GetMapping("/templates")
	public Collection<TemplateDto> getTemplatesForCurrentUser() {
		return eventsService.findTemplatesById(currentUser.getId());
	}

	@PostMapping("/templates")
	public void saveNewTemplateForCurrentUser(@RequestBody TemplateDto toSave) {
		eventsService.saveTemplateForUserId(currentUser.getId(), toSave);
	}

	@DeleteMapping("/templates/{templateId}")
	public void deleteTemplateById(@PathVariable UUID templateId) {
		eventsService.deleteTemplateById(templateId);
	}
}
