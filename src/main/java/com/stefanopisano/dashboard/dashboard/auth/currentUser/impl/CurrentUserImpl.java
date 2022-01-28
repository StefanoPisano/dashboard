package com.stefanopisano.dashboard.dashboard.auth.currentUser.impl;

import com.stefanopisano.dashboard.dashboard.auth.MyUserDetails;
import com.stefanopisano.dashboard.dashboard.auth.currentUser.CurrentUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CurrentUserImpl implements CurrentUser {
	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public UUID getId() {
		return this.getPrincipal().getId();
	}

	@Override
	public String getUsername() {
		return this.getPrincipal().getUsername();
	}

	@Override
	public String getEmail() {
		return this.getPrincipal().getEmail();
	}

	private MyUserDetails getPrincipal() {
		return (MyUserDetails) this.getAuthentication().getPrincipal();
	}
}
