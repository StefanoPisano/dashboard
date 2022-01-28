package com.stefanopisano.dashboard.dashboard.notes.controller;

import com.stefanopisano.dashboard.dashboard.auth.currentUser.CurrentUser;
import com.stefanopisano.dashboard.dashboard.notes.dto.NotesDto;
import com.stefanopisano.dashboard.dashboard.notes.service.NotesService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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
@RequestMapping("/notes")
public class NotesController {

	@Setter(onMethod=@__({@Autowired}))
	private NotesService notesServices;

	@Setter(onMethod=@__({@Autowired}))
	private CurrentUser currentUser;

	@GetMapping("/all")
	public Collection<NotesDto> getAllNotesForCurrentUser() {
		return notesServices.getAllNotesForCurrentUser(currentUser.getId());
	}

	@PostMapping
	public UUID saveNote(@RequestBody NotesDto toSave) {
		return notesServices.saveNote(currentUser.getId(), toSave);
	}

	@DeleteMapping("/{noteId}")
	public void deleteNoteById(@PathVariable UUID noteId) {
		notesServices.deleteNoteById(noteId);
	}
}
