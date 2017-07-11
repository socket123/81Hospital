package com.chusu.apps.service.business;

import com.chusu.apps.model.business.User;
import org.apache.log4j.Logger;
import com.chusu.apps.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * Created by LXX on 2017-06-17.
 */
@Service
public class UserService extends BaseService<User> {

    private final Logger logger = Logger.getLogger(UserService.class);

}
