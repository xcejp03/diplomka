package cz.vse.controller;

import cz.vse.dto.DefectDTO;
import cz.vse.service.DefectService;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
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
@RequestMapping("/defect")
public class DefectController {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    ProjectService projectService;

    @Autowired
    PersonService personService;

    @Autowired
    DefectService defectService;

    @RequestMapping( method = RequestMethod.GET)
    public String defectDefault(Model model) {
        l.info("request mapping defect/create");
        model.addAttribute("defect", new DefectDTO());
        model.addAttribute("defectList", defectService.findAllDefects());
        model.addAttribute("listPersons", personService.findAllPersons());
        model.addAttribute("listProjects", projectService.findAllTestProjectsDTO());

        return "tabulkaDefaultVypis";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createDefect(Model model) {
        l.info("request mapping defect/create");
        model.addAttribute("defect", new DefectDTO());
        model.addAttribute("defectList", defectService.findAllDefects());
        model.addAttribute("listPersons", personService.findAllPersons());
        model.addAttribute("listProjects", projectService.findAllTestProjectsDTO());

        return "defectCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createDefectPost(@ModelAttribute("person") DefectDTO defectDTO) {
        if (defectDTO.getId() == null) {
            defectService.createDefect(defectDTO);
        } else {
            defectService.updateDefect(defectDTO);
        }
        return "redirect:create";
    }

    @RequestMapping("/edit/{id}")
    public String editDefect(@PathVariable("id") long id, Model model) {
        l.info("/edit/{id}" + id);
        model.addAttribute("defect", defectService.findDefectDTOById(id));
        model.addAttribute("person", personService.findPersonById(id));

        return "defectCreate";
    }

    @RequestMapping("/remove/{id}")
    public String removeDefect(@PathVariable("id") long id, Model model) {
        defectService.deleteDefect(id);
        model.addAttribute("defect", new DefectDTO());
        return "redirect:/defect/create";

    }

}

