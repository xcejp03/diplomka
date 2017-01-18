package cz.vse.controller;

import cz.vse.entity.Defect;
import cz.vse.entity.DefectStatusEnum;
import cz.vse.entity.RoleEnum;
import cz.vse.entity.WorkList;
import cz.vse.repository.PersonRepository;
import cz.vse.repository.TCInstanceRepository;
import cz.vse.repository.TSInstanceRepository;
import cz.vse.repository.WorkListRepository;
import cz.vse.service.*;
import cz.vse.utils.SecurityUtils;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pcejka on 09.10.2016.
 */
@Controller
//@RequestMapping("/")
public class RootController {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    ProjectService projectService;

    @Autowired
    PersonService personService;

    @Autowired
    SuiteService suiteService;

    @Autowired
    private MapperFacade mapper;

    @Autowired
    private TSInstanceRepository tsInstanceRepository;

    @Autowired
    private TCInstanceRepository tcInstanceRepository;

    @Autowired
    private WorkTCService workTCService;

    @Autowired
    private WorkListService workListService;

    @Autowired
    private DefectService defectService;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private WorkListRepository workListRepository;


    @RequestMapping(value = "/thyme", method = RequestMethod.GET)
    public String thymeInclude(Model model) {
        l.info("request mapping project/create");
//        model.addAttribute("project", new ProjectDTO());
//        model.addAttribute("person", new PersonDTO());
//        model.addAttribute("listPersons", personService.findAllPersons());
        model.addAttribute("vlozit");

        return "thyme";
    }


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        l.info("request mapping root/index");
        return "index";
    }


    @RequestMapping(value = "/dashboard")
    public String dashboard(Model model) {
        Collection<? extends GrantedAuthority> loggedPersonAuthorities = securityUtils.getLoggedPersonAuthorities();
        l.warn("Moje role: " + loggedPersonAuthorities);
        if (loggedPersonAuthorities.contains(new SimpleGrantedAuthority("TESTER"))) {
            l.warn("role je tester");
            l.warn("findTestXXX()"+ personRepository.findXXX());
            l.warn("WorkList repository find XXx() "+workListRepository.findTestXXX());
            model.addAttribute("workListsToday", workListService.findAllWorkListDTOByMemberToday(securityUtils.getLoggedPersonId()));
            model.addAttribute("myAssignedOpenTC", defectService.findAllDefectDTOByAssigneeAndStatus(securityUtils.getLoggedPerson(), DefectStatusEnum.open));
            model.addAttribute("myOpenDefects", defectService.findAllDefectDTOByReporterAndStatus(securityUtils.getLoggedPerson(), DefectStatusEnum.open));
        }


        if (loggedPersonAuthorities.contains(new SimpleGrantedAuthority("ANALYTIC"))) {
            l.warn("role je analytik");
            model.addAttribute("myProjectsStatistics", projectService.getMyProjectsWithStatistics(securityUtils.getLoggedPerson()));
            model.addAttribute("myOpenDefects", model.addAttribute("myOpenDefects", defectService.findAllDefectDTOByReporterAndStatus(securityUtils.getLoggedPerson(), DefectStatusEnum.open)));
        }


        if (loggedPersonAuthorities.contains(new SimpleGrantedAuthority("MANAGER"))) {
            l.warn("role je manager");
            model.addAttribute("myOpenDefects", defectService.findAllDefectDTOByReporterAndStatus(securityUtils.getLoggedPerson(), DefectStatusEnum.open));
            model.addAttribute("myProjectsStatistics", projectService.getMyProjectsWithStatistics(securityUtils.getLoggedPerson()));
            model.addAttribute("workListsToday", workListService.findAllWorkListDTOByMemberToday(securityUtils.getLoggedPersonId()));
            model.addAttribute("workListsLastThreeDays", workListService.findAllWorkListDTOByMemberLastThreeDays(securityUtils.getLoggedPersonId()));
        }

        return "dashboard";
    }


}



