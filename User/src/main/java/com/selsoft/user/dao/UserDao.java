package com.selsoft.user.dao;

import com.selsoft.user.model.Errors;
import com.selsoft.user.model.PasswordResetToken;
import com.selsoft.user.model.User;

/**
 * 
 * @author Sudhansu Sekhar
 *
 */
public interface UserDao {
	public void saveUser(User user);

	public void saveUserLogin(User user);

	public User checkUserLogin(User user);

	public User findUserByEmail(String email);

	public void changeUserPassword(User user, String password);

	public void saveResetPasswordToken(PasswordResetToken token);

	public void userLogout(String email);

	public Errors saveUserType(User user, String userType);
}