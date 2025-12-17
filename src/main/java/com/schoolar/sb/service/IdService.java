package com.schoolar.sb.service;

import org.springframework.stereotype.Service;

@Service
public class IdService {

    private Integer counter = 0;

    public Integer createPersonId() {
        return counter++;
    }
}
