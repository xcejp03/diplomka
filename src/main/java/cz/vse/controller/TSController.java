package cz.vse.controller;

import cz.vse.dto.TSInstanceRunDTO;
import cz.vse.dto.TSMusterDTO;
import cz.vse.service.*;
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

    @Autowired
    TSInstanceService tsInstanceService;

    @Autowired
    TCInstanceService tcInstanceService;

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
    public String createTSPost(@ModelAttribute("ts") TSMusterDTO tsMusterDTO) {
        if (tsMusterDTO.getId() == null) {
            tsMusterService.createTestStepMuster(tsMusterDTO);
        } else {
            tsMusterService.updateTestStepMuster(tsMusterDTO);
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

    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public String runTSInstance(@ModelAttribute("ts") TSInstanceRunDTO tsInstanceRunDTO) {
        Long tcInstanceId = tsInstanceRunDTO.getTcInstance_id();
        l.info("/run/ - post");
        tsInstanceService.updateTestStepInstance(tsInstanceRunDTO);
        return "redirect:/tc/show/" + tcInstanceId;
    }

    @RequestMapping(value = "/run/{id}", method = RequestMethod.GET)
    public String runTSInstancePost(@PathVariable("id") long id, Model model) {
        l.info("/run/{id}" + id);
        model.addAttribute("ts", tsInstanceService.findTestStepInstanceRunDTOById(id));
        return "tsRun";
    }

    @RequestMapping("/remove/{id}")
    public String removeTSMuster(@PathVariable("id") long id) {
        tsMusterService.deleteTestStepMuster(id);
        return "redirect:/ts/create";
    }

    @RequestMapping("/instance/remove/{id}")
    public String removeTSInstance(@PathVariable("id") long id) {
        Long tcInstanceId = tsInstanceService.findTestStepInstanceById(id).gettCInstance().getId();
        tsInstanceService.deleteTestStepInstanceById(id);
        return "redirect:/tc/show/"+tcInstanceId;
    }
}