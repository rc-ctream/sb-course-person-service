package com.schoolar.sb.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schoolar.sb.api.mapper.PersonMapper;
import com.schoolar.sb.persistent.DepartmentType;
import com.schoolar.sb.persistent.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class PersonRestControllerTest {

    @Autowired
    private MockMvc restTestClient;
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    PersonRepository personRepository;
    @MockitoBean
    PersonMapper personMapper;

    @Test
    void createPerson_shouldSuccess() throws Exception {
        var person = new PersonRequestDto();
        person.setName( "Jonas" );
        person.setDepartment( DepartmentType.DEV );

        var mapper = new ObjectMapper();
        var requestBodyAsJson = mapper.writeValueAsString( person );

        mockMvc.perform( post("/persons").content( requestBodyAsJson ).contentType( MediaType.APPLICATION_JSON ) )
                .andDo( print() )
                .andExpect( status().isOk() );
    }

}