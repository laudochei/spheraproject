/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accord.charm.service;

/**
 *
 * @author laud.c.ochei
 */

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import com.accord.charm.model.Role;
import com.accord.charm.model.User;

/**
 * @author Ramesh Fadatare
 *
 */

public class AuthenticatedUser extends org.springframework.security.core.userdetails.User
{
	private static final long serialVersionUID = 1L;
	private User user;

	

	public AuthenticatedUser(User user)
	{
		super(user.getEmail(), user.getPassword(), getAuthorities(user));
		this.user = user;
	}

	

	public User getUser()
	{
		return user;
	}

	

	private static Collection<? extends GrantedAuthority> getAuthorities(User user)
	{
		Set<String> roleAndPermissions = new HashSet<>();
		List<Role> roles = user.getRoles();
		for (Role role : roles)
		{
			roleAndPermissions.add(role.getName());
		}

		String[] roleNames = new String[roleAndPermissions.size()];
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roleAndPermissions.toArray(roleNames));
		return authorities;
	}
}