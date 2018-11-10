package com.de.dao.oauth;



import java.util.ArrayList;
import java.util.List;
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

import com.de.model.entity.UserProfile;
import com.oauth.data.User;
import com.oauth.data.UserRole;
import com.oauth.exception.BusinessException;
import com.oauth.interfaces.RESTUserDetail;


// TODO: Auto-generated Javadoc

/**
 * The Class UserProfileDAOImpl.
 */
@Repository
public class UserDetailDAOImpl implements RESTUserDetail {

    /**
     * Logger
     */
    private final static Logger logger = LoggerFactory.getLogger(UserDetailDAOImpl.class);

    /**
     * The message source.
     */
    @Autowired
    @Qualifier(value = "messageSource")
    MessageSource messageSource;

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

    /* (non-Javadoc)
     * @see com.raastech.mobile.rest.dao.UserDetailDAO#fetchUser(com.raastech.mobile.rest.entity.Users)
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public User fetchUser(String username) {
        logger.info("Entering in UserProfileDAOImpl fetchUser method");
        Criteria criteria = getCurrentSession().createCriteria(UserProfile.class);
        criteria.add(Restrictions.eq("userMail", username));
        List<UserProfile> usersList = (ArrayList<UserProfile>) criteria.list();
        if (usersList != null && !usersList.isEmpty()) {
            logger.info("Exiting in UserProfileDAOImpl fetchUser method user is" + usersList.get(0));
            UserProfile userProfile = usersList.get(0);
            com.de.model.entity.UserRole userRole = userProfile.getUserRole();
            UserRole userRoleDTO = new UserRole(userRole.getRoleId(), userRole.getUserRole());
            return new User(userProfile.getId(), userProfile.getUserMail(), userProfile.getFirstName(), userProfile.getLastName(), userProfile.getPassword(), userRoleDTO);
        }
        logger.info("Exiting in UserProfileDAOImpl getLogin method");
        return null;
    }
}
