package com.schoolar.sb.api.controller;

import com.schoolar.sb.api.dto.PersonRequestDto;
import com.schoolar.sb.api.validation.ValidPerson;
import com.schoolar.sb.persistent.entity.DepartmentType;
import com.schoolar.sb.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PersonService personService;

    @GetMapping( "/index" )
    public String index( Model model ) {
        model.addAttribute( "person", new PersonRequestDto() );
        model.addAttribute( "departments", DepartmentType.values() );
        model.addAttribute( "persons", personService.getAllPersons() );
        return "index";
    }

    @PostMapping( "/submit" )
    public String submit( @Valid @ModelAttribute( "person" ) @ValidPerson PersonRequestDto person,
                          BindingResult bindingResult, Model model ) {


        if ( bindingResult.hasErrors() ) {
            model.addAttribute( "departments", DepartmentType.values() );
            model.addAttribute( "persons", personService.getAllPersons() );
            return "index";
        }

        personService.createPerson( person );
        return "redirect:/index";
    }
}
