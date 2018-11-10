package com.de.dao.oauth;


import java.util.ArrayList;
import java.util.Locale;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.de.model.entity.UserSession;
import com.oauth.data.AuthenticationToken;
import com.oauth.data.User;
import com.oauth.exception.BusinessException;
import com.oauth.interfaces.RESTAuthenticationToken;

@Repository
@Transactional
public class UserSessionDAOImpl implements RESTAuthenticationToken {


    /**
     * Logger
     */
    private final static Logger logger = LoggerFactory.getLogger(UserSessionDAOImpl.class);

    /**
     * The message source.
     */
    @Autowired
    @Qualifier(value = "messageSource")
    MessageSource messageSource;


    @Autowired
    private UserDAO userProfileDAO;

    /**
     * The locale.
     */
    Locale locale = LocaleContextHolder.getLocale();

    /**
     * The session factory.
     */
    @Autowired
    private SessionFactory sessionFactory;


    /**
     * Gets the current session.
     *
     * @return the current session
     * @throws BusinessException the business exception
     */
    private Session getCurrentSession() throws BusinessException {

        if (sessionFactory != null && sessionFactory.getCurrentSession() != null) {
            return sessionFactory.getCurrentSession();
        }
        return null;

    }

    //TODO:Move to spring security
    @SuppressWarnings("unchecked")
    @Override
    public AuthenticationToken find(String token) {
        logger.info("Entering in UserSessionIDAOImpl findByAccessToken method");
        Criteria criteria = getCurrentSession().createCriteria(UserSession.class);
        criteria.add(Restrictions.eq("token", token));
        ArrayList<UserSession> userSessionList = (ArrayList<UserSession>) criteria.list();
        if (userSessionList != null && !userSessionList.isEmpty()) {
            UserSession userSession = userSessionList.get(0);
            logger.info("UserSesion Exiting ");
            return new AuthenticationToken(userSession.getToken_id(), userSession.getToken(), userSession.getExpiryDateTime(), userSession.getUserId().getUserMail());
        }
        return null;
    }

    @Override
    public void save(String username, AuthenticationToken authenticationToken) {
        logger.info("Entering createSession() of UserProfileServiceImpl ");
        User user = userProfileDAO.fetchAuthUser(username);
        if (user != null) {
            UserSession session = new UserSession();
            session.setUserId(userProfileDAO.fetchUserProfile(username));
            session.setExpiryDateTime(authenticationToken.getExpiryDateTime());
            session.setToken(authenticationToken.getToken());
            getCurrentSession().save(session);
        }
        logger.info("Exiting createSession() of UserProfileServiceImpl ");
    }
}
