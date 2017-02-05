package cz.vse.controller;

import cz.vse.dto.DefectCommentDTO;
import cz.vse.dto.DefectDTO;
import cz.vse.dto.DefectForm;
import cz.vse.entity.DefectStatusEnum;
import cz.vse.service.DefectCommentService;
import cz.vse.service.impl.DefectServiceImpl;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
import cz.vse.utils.SecurityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    DefectServiceImpl defectService;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private DefectCommentService defectCommentService;

//    @RequestMapping(method = RequestMethod.GET)
//    public String defectDefault(Model model) {
//        l.info("request mapping defect/create");
//        model.addAttribute("defect", new DefectDTO());
//        model.addAttribute("defectList", defectService.findAllDefects());
//        model.addAttribute("listPersons", personService.findAllPersons());
//        model.addAttribute("listProjects", projectService.findAllTestProjectsDTO());
//
//        return "tabulkaDefaultVypis";
//    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createDefect(Model model) {
        l.info("request mapping defect/create");
        model.addAttribute("defectForm", new DefectForm());
//        model.addAttribute("defectList", defectService.findAllDefects());
        model.addAttribute("persons", personService.findAllPersonNames());
//        model.addAttribute("listProjects", projectService.findAllTestProjectsDTO());

        return "defectCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createDefect(@ModelAttribute("person") DefectForm defectForm, HttpServletRequest request) {
        if (defectForm.getId() == null) {
            defectForm.setReporter_id(securityUtils.getLoggedPersonId());
            defectService.createDefect(defectForm);
        } else {
            defectService.updateDefect(defectForm);
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping("/edit/{id}")
    public String editDefect(@PathVariable("id") long id, Model model) {
        l.info("/edit/{id}" + id);
        model.addAttribute("defectForm", defectService.findDefectForm(id));
//        model.addAttribute("person", personService.findPersonById(id));
        model.addAttribute("personNames", personService.findAllPersonNames());
        return "defectCreate";
    }

    @RequestMapping("/remove/{id}")
    public String removeDefect(@PathVariable("id") long id, Model model) {
        defectService.deleteDefect(id);
//        model.addAttribute("defect", new DefectDTO());
        return "redirect:/defect/create";

    }

    @RequestMapping(value = "/defects", method = RequestMethod.GET)
    public String defectShowByUser(Model model, @RequestParam(required = false, defaultValue = "open", value = "filter") String filter) {
        l.info("request mapping defect/defects");
        DefectStatusEnum filterStatus = DefectStatusEnum.valueOf(filter);

//        model.addAttribute("defect", new DefectDTO());
        model.addAttribute("defectListByReporter", defectService.findAllDefectListsByReporterAndStatus(securityUtils.getLoggedPerson(), filterStatus));
        model.addAttribute("defectListByAssignee", defectService.findAllDefectListsByAssigneeAndStatus(securityUtils.getLoggedPerson(), filterStatus));
//        model.addAttribute("listProjectsDTO", projectService.findAllTestProjectsDTO());

        return "defects";
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String defectShowById(@PathVariable("id") long id, Model model) {
        l.info("request mapping defect/" + id);
        model.addAttribute("defectDTO", defectService.findDefectDTO(id));

//        model.addAttribute("listDefectByReporterDTO", defectService.findAllDefectsByReporterAndStatus(securityUtils.getLoggedPerson(), DefectStatusEnum.open));
//        model.addAttribute("listDefectByAssigneeDTO", defectService.findAllDefectsByAssigneeAndStatus(securityUtils.getLoggedPerson(), DefectStatusEnum.open));
        model.addAttribute("personNames", personService.findAllPersonNames());
//        model.addAttribute("listProjectsDTO", projectService.findAllTestProjectsDTO());
        model.addAttribute("listDefectCommentByDefectDTO", defectCommentService.findAllDefectCommentDTOByDefectId(id));
        model.addAttribute("defectCommentsByDefect", defectCommentService.findAllDefectCommentDTOByDefectId(id));
        model.addAttribute("commentDTO", new DefectCommentDTO());
        model.addAttribute("loggedPersonName", securityUtils.getLoggedPersonName());
        return "defect";
    }

    @RequestMapping(value = "/writeAssigneeChange", method = RequestMethod.POST)
    public String writeAssigneeChange(DefectDTO defectDTO) {
        String redirectSite;
        defectService.changeDefectAssignee(defectDTO, securityUtils.getLoggedPerson());
//        defectCommentService.writeDefectAssigneeChange(defectDTO,  securityUtils.getLoggedPerson());
        redirectSite = "redirect:/defect/" + defectDTO.getId();
        return redirectSite;
    }

    @RequestMapping(value = "/writeStatusChange", method = RequestMethod.POST)
    public String writeStatusChange(DefectDTO defectDTO) {
        l.info("/writeStatusChange");
        String redirectSite;
        defectService.changeDefectStatus(defectDTO, securityUtils.getLoggedPerson());
//        defectCommentService.writeDefectStatusChange(defectDTO,  securityUtils.getLoggedPerson());
        redirectSite = "redirect:/defect/" + defectDTO.getId();

        return redirectSite;
    }

//    @RequestMapping(value = "/param", method = RequestMethod.GET)
//    public String createDedfect(@RequestParam(required = false, defaultValue = "open", value = "filter") String foo) {
//        l.info("request mapping defect/create");
//        l.info("foo: " + foo);
//        return "login";
//    }


}
