package com.schoolar.sb.email;

import com.schoolar.sb.persistent.DepartmentType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class EmailSendService {

    static final Map<DepartmentType, String> DEPARTMENT_EMAIL_TEMPLATES = Map.of(
            DepartmentType.TEST, "qa@example.com",
            DepartmentType.MARKETING, "marketing@example.com",
            DepartmentType.DEV, "dev@example.com"
    );

    static String EMAIL_SUBJECT = "New team member joined";
    static String EMAIL_BODY = "Dear Team {}, you have a new team member {}";


    @Async
    @SneakyThrows
    public void sendEmail( String newTeamMember, DepartmentType departmentType ) {
        Thread.sleep(3000);

        var to = DEPARTMENT_EMAIL_TEMPLATES.get( departmentType );
        var body = String.format( EMAIL_BODY, departmentType, newTeamMember );

        log.info( "Sending email to department {} with subject {} and body {}",
                to,
                EMAIL_SUBJECT,
                body
        );
    }
}
