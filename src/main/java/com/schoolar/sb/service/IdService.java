package com.schoolar.sb.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class IdService {

    private Long counter = 1L;

    public Long createId() {
        return counter++;
    }
}
