package cz.vse.controller;

import cz.vse.entity.ClientWithSelection;
import cz.vse.entity.ClientWithSelectionListWrapper;
import cz.vse.entity.WorkTCWrapper;
import cz.vse.repository.TCInstanceRepository;
import cz.vse.repository.TSInstanceRepository;
import cz.vse.service.PersonService;
import cz.vse.service.ProjectService;
import cz.vse.service.SuiteService;
import cz.vse.service.WorkTCService;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

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

    private ArrayList<ClientWithSelection> allClientsWithSelection = new ArrayList<ClientWithSelection>();

    public RootController() {
      /* Dummy data */
        allClientsWithSelection.add(new ClientWithSelection(false, "1", "192.168.0.10", "Client A"));
        allClientsWithSelection.add(new ClientWithSelection(false, "2", "192.168.0.11", "Client B"));
        allClientsWithSelection.add(new ClientWithSelection(false, "3", "192.168.0.12", "Client C"));
        allClientsWithSelection.add(new ClientWithSelection(false, "4", "192.168.0.13", "Client D"));
    }


    @RequestMapping("/test")
    String indsaex(Model model) {

        ClientWithSelectionListWrapper wrapper = new ClientWithSelectionListWrapper();
        WorkTCWrapper workTCWrapper = new WorkTCWrapper();
        workTCWrapper.setWorkTCList(workTCService.findWorkTCByWorkListId(1700));

        wrapper.setClientList(allClientsWithSelection);
        model.addAttribute("wrapper", wrapper);
        model.addAttribute("vrepr", workTCWrapper);
        model.addAttribute("listPerson", personService.findAllPersons());

        model.addAttribute("listWorkTC", workTCService.findWorkTCDTOByWorkListId(1700));
        return "test";
    }


    @RequestMapping(value = "/query/submitQuery", method = RequestMethod.POST)
    public String processQuery(@ModelAttribute ClientWithSelectionListWrapper wrapper, Model model) {

        System.out.println(wrapper.getClientList() != null ? wrapper.getClientList().size() : "null list");
        System.out.println("--");

        model.addAttribute("wrapper", wrapper);

        return "test";
    }

    @RequestMapping(value = "/query/submitQuery2", method = RequestMethod.POST)
    public String processQuery2(@ModelAttribute WorkTCWrapper wrapper, Model model) {
        l.info("vrepr: " + wrapper);
//        System.out.println(wrapper.getClientList() != null ? wrapper.getClientList().size() : "null list");
        System.out.println("--");

        model.addAttribute("wrapper", wrapper);

        return "test";
    }


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
//        model.addAttribute("project", new ProjectDTO());
//        model.addAttribute("person", new PersonDTO());
//        model.addAttribute("listPersons", personService.findAllPersons());

        return "index";
    }

    @RequestMapping(value = "/xxx")
    public String YourActionName(@RequestParam("memberId") String id, @RequestParam("memberPw") String pw) {
        l.error("Jóóóooooo");
        return "ExpectedReturnView";
    }

//    @RequestMapping(value = "/bs", method = RequestMethod.GET)
//    public String testujBootstrap() {
//        return "bootstrap";
//    }

//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public String bootstrapIndex() {
//        return "index";
//    }


}



