package com.stefanopisano.dashboard.dashboard.notes.service.impl;

import com.stefanopisano.dashboard.dashboard.notes.dao.NotesRepository;
import com.stefanopisano.dashboard.dashboard.notes.dto.NotesDto;
import com.stefanopisano.dashboard.dashboard.notes.model.Note;
import com.stefanopisano.dashboard.dashboard.notes.service.NotesService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NotesServiceImpl implements NotesService {

	@Setter(onMethod = @__({@Autowired}))
	private NotesRepository notesRepository;

	@Override
	public Collection<NotesDto> getAllNotesForCurrentUser(UUID userId) {
		return notesRepository.findNotesByUser(userId).stream()
							  .map(v -> NotesDto.builder()
												.id(v.getId())
												.note(v.getNote())
												.build())
							  .collect(Collectors.toList());
	}

	@Override
	public UUID saveNote(UUID userId, NotesDto toSave) {
		Note saved = notesRepository.save(Note.builder()
											 .user(userId)
											 .note(toSave.getNote())
											 .build());

		return saved.getId();
	}

	@Override
	public void deleteNoteById(UUID noteId) {
		notesRepository.deleteById(noteId);
	}
}
