package com.selsoft.trackme.service;

import java.util.Locale;

import org.springframework.mail.SimpleMailMessage;

import com.selsoft.trackme.dto.PasswordDto;
import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.User;

/**
 * 
 * @author Sudhansu Sekhar
 *
 */
public interface UserService {

	public Object saveUser(User user);

	public Object saveUserLogin(User user);

	public void createPasswordResetTokenForUser(User user, String token);

	public User findUserByEmail(String userEmail);

	public Errors changeUserPassword(User user, PasswordDto password);

	public SimpleMailMessage constructResetTokenEmail(String contextPath, Locale locale, String token, User user);

	public Errors userLogout(String email);

	//public void signInAsGuest(User user);

}
