package com.chusu.platform.core.initializing.impl;

import org.springframework.stereotype.Service;

import com.chusu.platform.core.initializing.InitializingService;

@Service
public class InitializingServiceImpl implements InitializingService {

    public void afterPropertiesSet() throws Exception {
        //		codeListService.init();
    }
}
