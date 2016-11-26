package cz.vse.controller;

import cz.vse.dto.TSMusterDTO;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
import cz.vse.service.TCMusterService;
import cz.vse.service.TSMusterService;
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
@RequestMapping("/ts")
public class TSController {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    ProjectService projectService;

    @Autowired
    PersonService personService;

    @Autowired
    TSMusterService tsMusterService;

    @Autowired
    TCMusterService tcMusterService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createTS(Model model) {
        l.info("request mapping ts/create");
        model.addAttribute("ts", new TSMusterDTO());
        model.addAttribute("listTSMusters", tsMusterService.findAllTestStepMustersDTO());
        model.addAttribute("listPersons", personService.findAllPersons());
        model.addAttribute("listTCMusters", tcMusterService.findAllTestCaseMusters());
        return "tsCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createTSPost(@ModelAttribute("project") TSMusterDTO TSMusterDTO) {
        if (TSMusterDTO.getId() == null) {
            tsMusterService.createTestStepMuster(TSMusterDTO);
        } else {
            tsMusterService.updateTestStepMuster(TSMusterDTO);
        }
        return "redirect:create";
    }

    @RequestMapping("/edit/{id}")
    public String editTSMuster(@PathVariable("id") long id, Model model) {
        l.info("/edit/{id}" + id);
        model.addAttribute("ts", tsMusterService.findTestStepMusterDTOById(id));
        model.addAttribute("listPersons", personService.findAllPersons());
        model.addAttribute("listTCMusters", tcMusterService.findAllTestCaseMusters());
        return "tsCreate";
    }

    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") long id) {
        tsMusterService.deleteTestStepMuster(id);
        return "redirect:/ts/create";
    }
}