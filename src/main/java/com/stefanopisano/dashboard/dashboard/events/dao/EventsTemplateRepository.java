package com.stefanopisano.dashboard.dashboard.events.dao;

import com.stefanopisano.dashboard.dashboard.events.model.Template;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventsTemplateRepository extends CrudRepository<Template, UUID> {

	Collection<Template> findAllByUser(UUID user);
}
