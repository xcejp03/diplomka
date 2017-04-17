package cz.vse.controller;

import cz.vse.dto.PersonForm;
import cz.vse.entity.RoleEnum;
import cz.vse.service.PersonService;
import cz.vse.service.RoleService;
import cz.vse.utils.HelpService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
    public String createProjectForm(Model model, PersonForm personForm) {
        l.info("request mapping person/create");
        return "registration";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createProject(Model model, @Valid PersonForm personForm, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            l.error("form has errors");
            return "registration";
        }

        if (personForm.getId() == null) {
            personService.createPerson(personForm);
        } else {
            personService.updatePerson(personForm);
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }


    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model) {
        l.info("/person/edit/" + id);
        model.addAttribute("personForm", personService.findPersonFormById(id));
        return "registration";
    }

    @RequestMapping("/edit")
    public String editAllPerson(Model model) {
        l.info("/person/edit");
        model.addAttribute("persons", personService.findAllPersonForms());
        return "registration";
    }

    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id) {
        l.info("/person/remove/" + id);
        personService.deletePerson(id);
        return "redirect:/person/create";
    }

    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public String editPersonRoleGET(Model model, PersonForm personForm) {
        l.info("person/role");
        model.addAttribute("persons", personService.findAllPersonForms());
        model.addAttribute("enumRoles", RoleEnum.values());
        return "userRole";
    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public String editPersonRoleGETWithId(@PathVariable("id") int id, Model model) {
        l.info("person/role/" + id);
        model.addAttribute("personForm", personService.findPersonFormById(id));
        model.addAttribute("enumRoles", RoleEnum.values());
        model.addAttribute("userEnumRoles", roleService.findUsersRoleEnum(id));
        model.addAttribute("userRoles", personService.findPersonById(id).getUserRole());
        return "userRole";
    }

    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public String editPersonRolePOST(PersonForm personForm) {
        l.info("person/role saving: " + personForm);
        roleService.updateRoleForUser(personForm);
        return "redirect:/person/edit";
    }

    @RequestMapping(value = "/prihlasit", method = RequestMethod.GET)
    public String prihlasitSe() {
        l.info("prihlasovani");

        helpService.prihlasit();
        return "redirect:/index";
    }

}

