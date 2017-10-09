package com.selsoft.trackme.controller;

import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.selsoft.trackme.dto.PasswordDto;
import com.selsoft.trackme.email.common.MailResponse;
import com.selsoft.trackme.email.service.MailSenderService;
import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.GenericResponse;
import com.selsoft.trackme.model.Owner;
import com.selsoft.trackme.model.User;
import com.selsoft.trackme.service.UserService;

/**
 * This is the UserController for the User Registration, Login and Retriving
 * User Information. This Controller class has Handler methods for the User
 * operations.
 * 
 * @author Sudhansu Sekhar
 *
 */

@RestController
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	@Autowired(required = true)
	private MailSenderService mailSender;

	/**
	 * <<<<<<< HEAD This handler method is for the User Registration, This will
	 * transfer the data to Service. The User Data will be Binded to the User
	 * Object which is coming from the Client.
	 * 
	 * @param user
	 *            as binding object to hold the User's Registration Data from
	 *            the Registration Form.
	 * @return the Errors Object as JSON Object, If any Validation error occurs
	 *         for the I/P data. ======= This handler method is for the User
	 *         Registration, This will transfer the data to Service. The User
	 *         Data will be Binded to the User Object which is coming from the
	 *         Client.
	 * 
	 * @param user
	 *            as binding object to hold the User's Registration Data from
	 *            the Registration Form.
	 * @return the Errors Object as JSON Object, If any Validation error occurs
	 *         for the I/P data. >>>>>>> refs/remotes/origin/master
	 */
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ResponseEntity<Errors> saveUser(@RequestBody User user) {
		logger.info(user.getFirstName() + " data comes into UserController saveUser() for processing");
		Errors errors = userService.saveUser(user);
		return new ResponseEntity<Errors>(errors, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public ResponseEntity<User> getUser() {
		logger.info("Data retrived from UserController getUser()");
		return new ResponseEntity<User>(new User(), HttpStatus.ACCEPTED);
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public ResponseEntity<Errors> userLogIn(@RequestBody User user) {
		logger.info(user.getEmail() + " data comes into UserController for login Purpose");
		if (user.getEmail() == null && user.getEmail().equalsIgnoreCase("") && user.getPassword() == null
				&& user.getPassword().equalsIgnoreCase("")) {
			return new ResponseEntity<Errors>(HttpStatus.BAD_REQUEST);
		}
		Errors errors = userService.saveUserLogin(user);
		return new ResponseEntity<Errors>(errors, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public GenericResponse resetPassword(HttpServletRequest request, Locale locale,
			@RequestParam("email") String userEmail) {
		User user = userService.findUserByEmail(userEmail);
		MailResponse response = null;
		if (user != null) {
			String token = UUID.randomUUID().toString();
			userService.createPasswordResetTokenForUser(user, token);
			response = mailSender
					.sendMail(userService.constructResetTokenEmail(getAppUrl(request), locale, token, user));
		}
		return new GenericResponse(response.getStatus());

	}

	private String getAppUrl(HttpServletRequest request) {
		String uri = request.getRequestURI();
		return uri;
	}

	@RequestMapping(value = "/savePassword", method = RequestMethod.POST)
	@ResponseBody
	public GenericResponse savePassword(Locale locale, PasswordDto passwordDto) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		userService.changeUserPassword(user, passwordDto.getPassword());
		return new GenericResponse("Password Changed Successfully");

	}

	/**
	 * 
	 */

	@RequestMapping(value = "/getRecords", method = RequestMethod.GET)
	public ResponseEntity<Owner> getAllRecords() {
		logger.info("Data retrived from OwnerController getAllRecods()");
		return new ResponseEntity<Owner>(new Owner(), HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/userLogout", method = RequestMethod.GET)
	public ResponseEntity<User> logout(@RequestParam("email") String email) {
		userService.userLogout(email);
		logger.info("Logout Successfully");

		return new ResponseEntity<User>(new User(), HttpStatus.ACCEPTED);
	}

}
