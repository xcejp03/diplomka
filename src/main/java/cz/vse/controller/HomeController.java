package cz.vse.controller;

import cz.vse.DBPopulator;
import cz.vse.dao.PersonDao;
import cz.vse.dto.DefectDTO;
import cz.vse.dto.TestProjectDTO;
import cz.vse.entity.Defect;
import cz.vse.entity.Person;
import cz.vse.service.DefectService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by pcejka on 08.10.2016.
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    private final Logger l = Logger.getLogger(this.getClass());
    @Autowired
    PersonDao personDao;

    @Autowired
    DBPopulator dbPopulator;

    @Autowired
    DefectService defectService;

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home(ModelMap model) {
        l.error("home");
        Person person = new Person();
        person.setName("XXX");
        l.error("im going to save person " + person);
        personDao.savePerson(person);
        l.error("After saving person");
        dbPopulator.createAllDatasWithConstraints();
        l.error("dbPopulator.createAllDatasWithConstraints(); hotovo");

        return "home";
    }

    @RequestMapping(value = "defect", method = RequestMethod.GET)
    public String defectTest(ModelMap model) {
        l.error("defect");
//        Defect defect = new Defect();
//        defect.setDefectStatusEnum(DefectStatusEnum.open);
//        defect.setPriorityEnum(PriorityEnum.blocker);
//        defect.setAffectsVersion("betaverze");
//        defect.setDescription("Popis chyby pro testování");
//        defectService.createDefect(defect);
//        defectService.findAllDefects();
//        defect.setDescription("Updatovaný defect přes service");
//        defectService.updateDefect(defect);
//        defectService.findAllDefects();
//        defectService.deleteDefect(defect);
        model.addAttribute("defect", new DefectDTO());
        model.addAttribute("defectList", defectService.findAllDefects());
        l.debug("nic");
        return "defect";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home2(ModelMap model) {
        l.error("home2");
        Person person = new Person();
        person.setName("XXX");
        personDao.savePerson(person);

        model.addAttribute("largestQuadruplet");
        return "home";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home3(ModelMap model) {
        l.error("/");
        Person person = new Person();
        person.setName("XXX");
        personDao.savePerson(person);

        model.addAttribute("largestQuadruplet");
        return "home";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home4(ModelMap model) {
        l.error(" - ; base");
        Person person = new Person();
        person.setName("XXX2");
        personDao.savePerson(person);

        model.addAttribute("largestQuadruplet");
        return "home";
    }

    @RequestMapping(value = "defect", method = RequestMethod.POST)
    public String createDefect(@ModelAttribute("testProject") DefectDTO defectDTO) {
        l.error("/defect");

        defectService.createDefect(defectDTO);
        return "redirect:defect";
    }

}


