package com.schoolar.sb.api;

import com.schoolar.sb.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PersonService personService;

    @GetMapping( "/index" )
    public String index( Model model ) {
        model.addAttribute( "person", new PersonRequestDto() );
        model.addAttribute( "departments", List.of( "DEV", "MARKETING", "TEST" ) );
        model.addAttribute( "persons", personService.getAllPersons() );
        return "index";
    }

    @PostMapping( "/submit" )
    public String submit( @Valid @ModelAttribute PersonRequestDto person, BindingResult bindingResult,
                          Model model ) {

        personService.createPerson( person );
        return "redirect:/index";
    }
}
