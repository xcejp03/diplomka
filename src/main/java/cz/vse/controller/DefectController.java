package cz.vse.controller;

import cz.vse.dto.DefectCommentDTO;
import cz.vse.dto.DefectDTO;
import cz.vse.entity.DefectComment;
import cz.vse.entity.DefectStatusEnum;
import cz.vse.entity.Person;
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

    @RequestMapping(method = RequestMethod.GET)
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
        model.addAttribute("defectDTO", new DefectDTO());
        model.addAttribute("defectList", defectService.findAllDefects());
        model.addAttribute("listPersons", personService.findAllPersons());
        model.addAttribute("listProjects", projectService.findAllTestProjectsDTO());

        return "defectCreate";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createDefectPost(@ModelAttribute("person") DefectDTO defectDTO, HttpServletRequest request) {
        if (defectDTO.getId() == null) {
            defectDTO.setReporter_id(securityUtils.getLoggedPersonId());
            defectService.createDefect(defectDTO);
        } else {
            defectService.updateDefect(defectDTO);
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping("/edit/{id}")
    public String editDefect(@PathVariable("id") long id, Model model) {
        l.info("/edit/{id}" + id);
        model.addAttribute("defectDTO", defectService.findDefectDTOById(id));
        model.addAttribute("person", personService.findPersonById(id));

        return "defectCreate";
    }

    @RequestMapping("/remove/{id}")
    public String removeDefect(@PathVariable("id") long id, Model model) {
        defectService.deleteDefect(id);
        model.addAttribute("defect", new DefectDTO());
        return "redirect:/defect/create";

    }

    @RequestMapping(value = "/defects", method = RequestMethod.GET)
    public String defectShowByUser(Model model, @RequestParam(required = false, defaultValue = "open", value = "filter") String filter) {
        l.info("request mapping defect/defects");
        DefectStatusEnum filterStatus = DefectStatusEnum.valueOf(filter);

        model.addAttribute("defect", new DefectDTO());
        model.addAttribute("listDefectByReporterDTO", defectService.findAllDefectDTOByReporterAndStatus(securityUtils.getLoggedPerson(), filterStatus));
        model.addAttribute("listDefectByAssigneeDTO", defectService.findAllDefectDTOByAssigneeAndStatus(securityUtils.getLoggedPerson(), filterStatus));
        model.addAttribute("listPersons", personService.findAllPersons());
        model.addAttribute("listProjectsDTO", projectService.findAllTestProjectsDTO());

        return "defects";
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String defectShowById(@PathVariable("id") long id, Model model) {
        l.info("request mapping defect/" + id);
        model.addAttribute("defectDTO", defectService.findDefectDTOById(id));

        model.addAttribute("listDefectByReporterDTO", defectService.findAllDefectDTOByReporterAndStatus(securityUtils.getLoggedPerson(), DefectStatusEnum.open));
        model.addAttribute("listDefectByAssigneeDTO", defectService.findAllDefectDTOByAssigneeAndStatus(securityUtils.getLoggedPerson(), DefectStatusEnum.open));
        model.addAttribute("listPersons", personService.findAllPersons());
        model.addAttribute("listProjectsDTO", projectService.findAllTestProjectsDTO());
        model.addAttribute("listDefectCommentByDefectDTO", defectCommentService.findAllDefectCommentDTOByDefectId(id));
        model.addAttribute("commentDTO", new DefectCommentDTO());
        model.addAttribute("loggedPersonDTO", securityUtils.getLoggedPersonDTO());
        return "defect";
    }

    @RequestMapping(value = "/writeAssigneChange", method = RequestMethod.POST)
    public String writeAssigneChange(DefectDTO defectDTO) {
        String redirectSite;
        defectService.changeDefectAssignee(defectDTO, securityUtils.getLoggedPerson());
//        defectCommentService.writeDefectAssigneeChange(defectDTO,  securityUtils.getLoggedPerson());
        redirectSite = "redirect:/defect/" + defectDTO.getId();

        return redirectSite;
    }

    @RequestMapping(value = "/writeStatusChange", method = RequestMethod.POST)
    public String writeStatusChange(DefectDTO defectDTO) {
        String redirectSite;
        defectService.changeDefectStatus(defectDTO, securityUtils.getLoggedPerson());
//        defectCommentService.writeDefectStatusChange(defectDTO,  securityUtils.getLoggedPerson());
        redirectSite = "redirect:/defect/" + defectDTO.getId();

        return redirectSite;
    }

    @RequestMapping(value = "/param", method = RequestMethod.GET)
    public String createDedfect(@RequestParam(required = false, defaultValue = "open", value = "filter") String foo) {
        l.info("request mapping defect/create");
        l.info("foo: " + foo);
        return "login";
    }


}
