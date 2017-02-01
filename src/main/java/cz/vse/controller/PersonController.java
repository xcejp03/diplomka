package cz.vse.controller;

import cz.vse.dto.PersonDTO;
import cz.vse.entity.RoleEnum;
import cz.vse.service.PersonService;
import cz.vse.service.RoleService;
import cz.vse.utils.HelpService;
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

    @Autowired
    RoleService roleService;

    @Autowired
    HelpService helpService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createProjectForm(Model model) {
        l.info("request mapping person/create");
        model.addAttribute("personDTO", new PersonDTO());
//        model.addAttribute("listPersons", personService.findAllPersons());
        return "registration";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createProject(@ModelAttribute("person") PersonDTO personDTO) {
        if (personDTO.getId() == null) {
            personService.createPerson(personDTO);
        } else {
            personService.updatePerson(personDTO);
        }
        return "redirect:edit";
    }


    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model) {
        l.info("/edit/" + id);
        model.addAttribute("personDTO", personService.findPersonById(id));
//        model.addAttribute("personDTOList", personService.findAllPersonsDTO());
//        model.addAttribute("enumRoles", RoleEnum.values());
//        model.addAttribute("userEnumRoleList", roleService.findUsersRoleEnum(id));
//        model.addAttribute("listUserRoles", personService.findPersonById(id).getUserRole());
        return "registration";
    }

    @RequestMapping("/edit")
    public String editAllPerson(Model model) {
        model.addAttribute("personDTOList", personService.findAllPersonsDTO());
//        model.addAttribute("personDTO", new PersonDTO());
//        model.addAttribute("listPersons", personService.findAllPersons());
        return "registration";
    }

    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id) {
        personService.deletePerson(id);
        return "redirect:/person/create";
    }

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public String editPersonRoleGET(Model model) {
        l.info("person/role");
        model.addAttribute("personDTOList", personService.findAllPersonsDTO());
        model.addAttribute("enumRoles", RoleEnum.values());
        return "userRole";
    }
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public String editPersonRoleGETWithId(@PathVariable("id") int id, Model model) {
        l.info("person/role");
        model.addAttribute("personDTO", personService.findPersonById(id));
//        model.addAttribute("personDTOList", personService.findAllPersonsDTO());
        model.addAttribute("enumRoles", RoleEnum.values());
        model.addAttribute("userEnumRoleList", roleService.findUsersRoleEnum(id));
        model.addAttribute("listUserRoles", personService.findPersonById(id).getUserRole());
        return "userRole";
    }

    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public String editPersonRolePOST(Model model, PersonDTO personDTO) {
        l.info("person/role saving");
//        model.addAttribute("personDTO", new PersonDTO());
//        model.addAttribute("personDTOList", personService.findAllPersonsDTO());
//        model.addAttribute("enumRoles", RoleEnum.values());
        roleService.updateRoleForUser(personDTO);
        return "redirect:role";
    }

    @RequestMapping(value = "/prihlasit", method = RequestMethod.GET)
    public String prihlasitSe() {
        l.info("prihlasovani");

        helpService.prihlasit();
        return "redirect:/index";
    }

}

