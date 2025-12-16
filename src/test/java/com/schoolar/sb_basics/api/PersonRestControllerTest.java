package com.schoolar.sb_basics.api;

import com.schoolar.sb_basics.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest( PersonRestController.class )
@AutoConfigureMockMvc
@Import( GlobalExceptionHandler.class )
class PersonRestControllerTest {

    @Autowired
    private MockMvc mockMvc;



    @Test
    void createPerson_shouldSuccess() throws Exception {
        var person = new PersonRequestDto();
        person.setName( "Jonas" );
        person.setDepartment( "DEV" );

        var mapper = new ObjectMapper();
        var requestBodyAsJson = mapper.writeValueAsString( person );

        mockMvc.perform(post("/persons")
                        .contentType( MediaType.APPLICATION_JSON)
                        .content(requestBodyAsJson))
                .andExpect(status().isOk());
    }

}