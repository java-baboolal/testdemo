package com.de.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.de.model.entity.UserProfile;
import com.de.model.entity.UserSession;
import com.de.service.oauth.UserProfileService;
import com.oauth.exception.ErrorCodes;
import com.oauth.exception.RequiredFieldMissingException;
import com.oauth.json.AuthenticationDetail;
import com.oauth.service.RESTSpringSecurityService;


/**
 * The Class UserProfileController.
 */
@RestController
@RequestMapping("/api")
public class UserProfileController {

    public static final String REQUIRED_FIELD_MISSING = "Please provide all required fields";

    /**
     * Logger
     */
    private final static Logger logger = LoggerFactory.getLogger(UserProfileController.class);


    /**
     * The user profile service.
     */
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private RESTSpringSecurityService restSpringSecurityService;


    /**
     * This API is used to do login and save token for the user using Users and UserSession class. The token expiration  time is 6 hours
     * This API also takes device id for the consuming parties where they will provide the unique device id with username and password.
     * The same token will be returned id the provided device already exist in the database and if not they we will save the token for the User.
     *
     * @param request  the request
     * @param response the response
     * @param users    the users
     * @param deviceId the device id
     * @return the response entity
     */

    @RequestMapping(value = "/session/{deviceId}", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationDetail login(HttpServletRequest request, HttpServletResponse response, @RequestBody UserProfile users, @PathVariable String deviceId) {
        logger.info("Entering in UserProfileController login method");

        if (users.getUserMail() == null || users.getUserMail().trim().equals("") || users.getPassword() == null || users.getPassword().trim().equals("") || deviceId.trim().equals("")) {
            throw new RequiredFieldMissingException(REQUIRED_FIELD_MISSING, ErrorCodes.MISSING_ARGUMENT);
        }
        logger.info("Exiting in UserProfileController login method");

        UserSession session = userProfileService.doLogin(users, deviceId);
        AuthenticationDetail token = new AuthenticationDetail(users.getUserMail(), session.getToken(), null);

//		populateAccessTokenCookie(true, response,token);

//		System.out.println("Token : " + token);
        return token;
    }


    /**
     * Logout.
     *
     * @param request  the request
     * @param response the response
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("hiu");
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void user(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("user");
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void admin(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("admin");
    }

    @RequestMapping(value = "/open", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void open(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(restSpringSecurityService.encodePassword("pass123"));
        System.out.println("open");
    }

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void all(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("all");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Map<String,Object> register(@RequestBody final HashMap<String,Object> request) {
        System.out.println("inside registration");
        return userProfileService.register(String.valueOf(request.get("username")), String.valueOf(request.get("password")));
    }
}