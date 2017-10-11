package com.selsoft.trackme.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.selsoft.trackme.dao.UserDao;
import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.GenericResponse;
import com.selsoft.trackme.model.PasswordResetToken;
import com.selsoft.trackme.model.User;
import com.selsoft.trackme.model.ValidError;
import com.selsoft.trackme.utils.Utils;
import com.selsoft.trackme.validation.UserValidation;

/**
 * 
 * @author Sudhansu Sekhar
 * 
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	private static final Logger logger = Logger.getLogger(UserService.class);
	private UserValidation validation = new UserValidation();

	public Errors saveUser(User user) {
		if (isValid(user)) {
			logger.info("User data is Valid and processing to Dao");

			String encryptPass = Utils.encryptPassword(user.getPassword());

			user.setPassword(encryptPass);
			userDao.saveUser(user);

			/*
			 * if(usertype.equals("MGR")){
			 * 
			 * }
			 */

		} else {
			logger.info("User data is not Valid returning Error Data");
			return getError(user);
		}
		return null;
	}

	public Errors saveUserLogin(User user) {
		if (isValidUser(user)) {
			logger.info("User data is Valid and processing to Dao");

			String password = user.getPassword();
			try {
				// encrypting the password seeing to User object
				// setting last accessed time & userlogin boolean flag.
				String encryptPwd = Utils.encryptPassword(password);
				user.setPassword(encryptPwd);
				user.setLoggedOn(true);
				Calendar time = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
				Date date = time.getTime();
				user.setLastAccessed(date.toString());
				userDao.saveUserLogin(user);
			} catch (Exception e) {
				// throw custom seception
			}

		} else {
			logger.info("Email Id or Password are not valid returning Error Data");
			return getError(user);
		}
		return null;
	}

	public User findUserByEmail(String email) {
		User user = userDao.findUserByEmail(email);

		return user;
	}

	public void createPasswordResetTokenForUser(User user, String token) {
		Calendar time = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		Date date = time.getTime();
		PasswordResetToken myToken = new PasswordResetToken(token, user, date);
		userDao.saveResetPasswordToken(myToken);
	}

	public boolean isValid(User user) {

		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String email = user.getEmail();
		String password = user.getPassword();

		ValidError nameErrors = validation.nameValidation(firstName, lastName);
		ValidError emailErrors = validation.emailValidation(email);
		ValidError passwordErrors = validation.passwordValidation(password);

		if ("Success".equals(nameErrors.getErrorCode()) && "Success".equals(emailErrors.getErrorCode())
				&& "Success".equals(passwordErrors.getErrorCode())) {
			return true;
		} else {
			return false;
		}

	}

	public Errors getError(User user) {
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String email = user.getEmail();
		String password = user.getPassword();

		ValidError nameErrors = validation.nameValidation(firstName, lastName);
		ValidError emailErrors = validation.emailValidation(email);
		ValidError passwordErrors = validation.passwordValidation(password);

		List<ValidError> list = new ArrayList<ValidError>();
		list.add(nameErrors);
		list.add(emailErrors);
		list.add(passwordErrors);

		Errors errors = new Errors();
		errors.setError(list);
		return errors;

	}

	public boolean isValidUser(User user) {

		String email = user.getEmail();
		String password = user.getPassword();

		ValidError emailErrors = validation.emailValidation(email);
		ValidError passwordErrors = validation.passwordValidation(password);

		if ("Success".equals(emailErrors.getErrorCode()) && "Success".equals(passwordErrors.getErrorCode())) {
			return true;
		} else {
			return false;
		}
	}

	public SimpleMailMessage constructResetTokenEmail(String contextPath, Locale locale, String token, User user) {
		String url = contextPath + "/user/changePassword?id=" + user.getEmail() + "&token=" + token;
		String message = generateContent(user, token, contextPath);
		return constructEmail("Reset Password", message + " \r\n" + url, user);
	}

	public String generateContent(User user, String token, String appUrl) {

		StringBuilder builder = new StringBuilder();
		builder.append("Hi " + user.getFirstName() + "<br> Please use below link to Reset your password.<br><a href='"
				+ appUrl + "?token=" + token + "'>Click here to Reset Password</a><br>" + "Thanks,<br> TrackMe Inc. ");
		return builder.toString();
	}

	private SimpleMailMessage constructEmail(String subject, String body, User user) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(subject);
		email.setText(body);
		email.setTo(user.getEmail());
		return email;
	}

	@Override
	public void changeUserPassword(User user, String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public void userLogout(String email) {

		User user = userDao.userLogout(email);

	}



}
