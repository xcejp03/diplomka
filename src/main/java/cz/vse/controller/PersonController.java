package cz.vse.controller;

import cz.vse.dto.PersonDTO;
import cz.vse.service.PersonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by pcejka on 09.10.2016.
 */
@Controller
@RequestMapping("/person")
public class PersonController {
    private final Logger l = Logger.getLogger(this.getClass());

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createProjectForm(Model model) {
        l.info("request mapping person/create");
        model.addAttribute("person", new PersonDTO());
        model.addAttribute("listPersons", personService.findAllPersons());

        return "personCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createProject(@ModelAttribute("person") PersonDTO personDTO) {
        if (personDTO.getId() == null) {
            //new person, add it
            personService.createPerson(personDTO);
        } else {
            //existing person, call update
            this.personService.updatePerson(personDTO);
        }
        return "redirect:create";
    }


    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model) {
        l.info("/edit/{id}" + id);
        model.addAttribute("person", personService.findPersonById(id));
        return "personCreate";
    }

    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id) {
        personService.deletePerson(id);
        return "redirect:/person/create";
    }

}

