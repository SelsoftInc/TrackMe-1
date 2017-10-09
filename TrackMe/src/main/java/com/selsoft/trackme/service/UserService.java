package com.selsoft.trackme.service;

import java.util.Locale;

import org.springframework.mail.SimpleMailMessage;

import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.Tenant;
import com.selsoft.trackme.model.User;

/**
 * 
 * @author Sudhansu Sekhar
 *
 */
public interface UserService {

	public Errors saveUser(User user);

	public Errors saveUserLogin(User user);

	public void createPasswordResetTokenForUser(User user, String token);

	public User findUserByEmail(String userEmail);

	public void changeUserPassword(User user, String password);

	public SimpleMailMessage constructResetTokenEmail(String contextPath, Locale locale, String token, User user);

	public void userLogout(String email);

}
