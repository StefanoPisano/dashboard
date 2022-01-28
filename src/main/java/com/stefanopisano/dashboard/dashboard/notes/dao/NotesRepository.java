package com.stefanopisano.dashboard.dashboard.notes.dao;

import com.stefanopisano.dashboard.dashboard.notes.model.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface NotesRepository extends CrudRepository<Note, UUID> {

	Collection<Note> findNotesByUser(UUID user);
}
