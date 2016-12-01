package cz.vse.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by pcejka on 09.10.2016.
 */
@Controller
@RequestMapping("/static")
public class StaticController {
    private final Logger l = Logger.getLogger(this.getClass());
//
    @RequestMapping(value={"/", "index"}, method=RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping(value={"/3"}, method=RequestMethod.GET)
    public ModelAndView index2() {
        return new ModelAndView("index");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String tcDefault(Model model) {
        l.info("request mapping /tc");

        return "staticweb";
    }

    @RequestMapping(value = "/2", method = RequestMethod.GET)
    public String tcDefault2(Model model) {
        l.info("request mapping /tc  já");

        return "staticweb";
    }


}