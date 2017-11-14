package com.selsoft.user.utils;

import com.selsoft.user.model.User;

public class UserType {

	public User createNewOwner(User user) {

		user.setUserType(UserConstants.OWNER);

		return user;

	}

	public User createNewPropertyManager(User user) {
		user.setUserType(UserConstants.MANAGER);

		return user;
	}

	public User createNewTenant(User user) {
		user.setUserType(UserConstants.TENANT);

		return user;
	}

}
