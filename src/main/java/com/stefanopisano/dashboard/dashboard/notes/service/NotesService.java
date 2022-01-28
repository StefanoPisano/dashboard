package com.stefanopisano.dashboard.dashboard.notes.service;

import com.stefanopisano.dashboard.dashboard.notes.dto.NotesDto;

import java.util.Collection;
import java.util.UUID;

public interface NotesService {
	Collection<NotesDto> getAllNotesForCurrentUser(UUID userId);

	UUID saveNote(UUID userId, NotesDto toSave);

	void deleteNoteById(UUID noteId);
}
