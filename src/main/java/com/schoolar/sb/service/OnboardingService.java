package com.schoolar.sb.service;

import com.schoolar.sb.persistent.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OnboardingService {

    private int attempts = 0;

    @Async
    @Retryable(
            retryFor = RuntimeException.class,
            maxAttempts = 5,
            backoff = @Backoff( delay = 3000 )
    )
    public void onboarding( Person person ) {
        attempts++;

        log.info( "Starting onboarding process for person: {} attempts: {}", person.getName(), attempts );

        if ( Math.random() > 0.0 ) {
            log.error( "Error occurred during onboarding process for person: {}", person.getName() );
            throw new RuntimeException( "Network error" );
        }

        log.info( "Onboarding process completed for person: {}", person.getName() );
    }

    @Recover
    public void recover( RuntimeException ex, Person person ) {
        log.error( "recover called for person: {} with exception: {}", person.getName(), ex.getMessage() );
    }
}
