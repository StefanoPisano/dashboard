package com.stefanopisano.dashboard.dashboard.users.dao;

import com.stefanopisano.dashboard.dashboard.users.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

	Optional<User> findByUsername(String username);
}