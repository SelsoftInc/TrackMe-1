package com.selsoft.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.selsoft.user.constants.Constants;
import com.selsoft.user.dao.UserDao;
import com.selsoft.user.dto.PasswordDto;
import com.selsoft.user.email.common.MailResponse;
import com.selsoft.user.email.service.MailSenderService;
import com.selsoft.user.model.Errors;
import com.selsoft.user.model.User;
import com.selsoft.user.service.UserService;
import com.selsoft.user.utils.JWTYUtil;
import com.selsoft.user.utils.ResponseModel;
import com.selsoft.user.utils.UserType;


/**
 * This is the UserController for the User Registration, Login and Retriving
 * User Information. This Controller class has Handler methods for the User
 * operations.
 * 
 * @author Sudhansu Sekhar
 *
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class);
	private static UserType userType = new UserType();

	@Autowired
	private UserService userService;
	@Autowired(required = true)
	private MailSenderService mailSender;

	/**
	 * This handler method is for the User Registration, This will transfer the data
	 * to Service. The User Data will be Binded to the User Object which is coming
	 * from the Client.
	 * 
	 * @param user
	 *            as binding object to hold the User's Registration Data from the
	 *            Registration Form.
	 * @return the Errors Object as JSON Object, If any Validation error occurs for
	 *         the I/P data. ======= This handler method is for the User
	 *         Registration, This will transfer the data to Service. The User Data
	 *         will be Binded to the User Object which is coming from the Client.
	 * 
	 * @param user
	 *            as binding object to hold the User's Registration Data from the
	 *            Registration Form.
	 * @return the Errors Object as JSON Object, If any Validation error occurs for
	 *         the I/P data.
	 */
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		User userWithType = null;
		logger.info(user.getFirstName() + " data comes into UserController saveUser() for processing");

		if (StringUtils.equals("OWN", user.getUserType())) {
			userWithType = userType.createNewOwner(user);
		}

		else if (StringUtils.equals("MGR", user.getUserType())) {
			userWithType = userType.createNewPropertyManager(user);

		} else if (StringUtils.equals("TNT", user.getUserType())) {
			userWithType = userType.createNewTenant(user);

		}
		Object errors = userService.saveUser(userWithType);

		return new ResponseEntity<Object>(errors, HttpStatus.CREATED);
	}

	/**
	 * This method gets all the user from the user table
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public ResponseEntity<User> getUser() {

		logger.info("Data retrived from UserController getUser()");
		return new ResponseEntity<User>(new User(), HttpStatus.ACCEPTED);
	}
	
	
	public ResponseModel test() {
		return new ResponseModel(200, "API is Working with POST", Constants.SUCCESS, null, null, null);
	}

	public ResponseModel test1() {
		return new ResponseModel(200, "API is Working with POST", Constants.SUCCESS, null, null, null);
	}


	/**
	 * This method takes argument as user object,validates email and password If it
	 * is a valid user,login otherwise throws error message
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public ResponseEntity<Object> userLogIn(@RequestBody User user) {
		/*logger.info(user.getEmail() + " data comes into UserController for login Purpose");
		if (user.getEmail() == null && StringUtils.equalsIgnoreCase(user.getEmail(), ("")) && user.getPassword() == null
				&& StringUtils.equalsIgnoreCase(user.getPassword(), (""))) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
		Object response = userService.userLogin(user);
		return new ResponseEntity<Object>(response, HttpStatus.CREATED);
		*/
		//user = UserDao.userLogin(user.getEmail(), user.getFirstName());
		if (user != null) {
			String token = new JWTYUtil().createJWT(user);
			List<User> list = new ArrayList<User>();
			list.add(user);
			if (token != null) {
				return new ResponseEntity(null);
			} else {
				return new ResponseEntity(null);
			}
		}
		return new ResponseEntity(null);
		
		
		

	}

	/**
	 * If a user has not login within 20 ms.,it shows an confirmation mail to resets
	 * the password
	 * 
	 * @param request
	 * @param locale
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<MailResponse> resetPassword(HttpServletRequest request, Locale locale,
			@RequestParam("email") String email) {
		User user = userService.findUserByEmail(email);
		MailResponse response = null;
		if (user != null) {
			String token = UUID.randomUUID().toString();
			userService.createPasswordResetTokenForUser(user, token);
			response = mailSender
					.sendMail(userService.constructResetTokenEmail(getAppUrl(request), locale, token, user));
		}
		return new ResponseEntity<MailResponse>(response, HttpStatus.CREATED);

	}

	private String getAppUrl(HttpServletRequest request) {
		String uri = request.getRequestURI();
		return uri;
	}

	/**
	 * This method saves the password of valid user with encrypted password
	 * 
	 * @param email
	 * @param passwordDto
	 * @return
	 */
	@RequestMapping(value = "/savePassword", method = RequestMethod.POST)
	@ResponseBody
	public Errors savePassword(@RequestParam("email") String email, @RequestBody PasswordDto passwordDto) {

		User user = userService.findUserByEmail(email);

		Errors errors = userService.changeUserPassword(user, passwordDto);
		return errors;
	}

	/**
	 * This method takes email as a parameter ,it will check for valid user,if it's
	 * then saves the user's password
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/userLogout", method = RequestMethod.GET)
	public Errors logout(@RequestParam("email") String email) {
		Errors errors = userService.userLogout(email);
		logger.info(email + " Logged Out Successfully");

		return errors;

	}
	
	
	
	/*public ResponseModel userVerifiToken(@HeaderParam("token") String myToken) {
		if (myToken != null) {
			String userId = new JWTYUtil().parseJWT(myToken);
			if (userId != null && !userId.equals("0")) {
				return new ResponseModel(161, "Token Verified", Constants.SUCCESS, null, userId, null);
			} else {
				return new ResponseModel(161, "Token  Verification Failed", Constants.FAILURE, null, null, null);
			}
		}
		return new ResponseModel(161, "Token  Is Missing", Constants.FAILURE, null, null, null);

	}
*/

}
