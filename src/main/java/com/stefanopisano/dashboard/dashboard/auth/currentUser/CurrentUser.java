package com.stefanopisano.dashboard.dashboard.auth.currentUser;

import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface CurrentUser {

	Authentication getAuthentication();
	UUID getId();
	String getUsername();
	String getEmail();
}
