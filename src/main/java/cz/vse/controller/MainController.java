//package cz.vse.controller;
//
///**
// * Created by pcejk on 24.12.2016.
// */
//
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//public class MainController {
//
//    //        @RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
//    @RequestMapping(value = {"/welcome**"}, method = RequestMethod.GET)
//    public ModelAndView defaultPage() {
//
//        ModelAndView model = new ModelAndView();
//        model.addObject("title", "Spring Security Login Form - Database Authentication");
//        model.addObject("message", "This is default page!");
//        model.setViewName("index");
//        return model;
//
//    }
//
//    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
//    public ModelAndView adminPage() {
//
//        ModelAndView model = new ModelAndView();
//        model.addObject("title", "Spring Security Login Form - Database Authentication");
//        model.addObject("message", "This page is for ROLE_ADMIN only!");
//        model.setViewName("admin");
//        return model;
//
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login() {
//
//        ModelAndView model = new ModelAndView();
////            if (error != null) {
//////                model.addObject("error", "Invalid username and password!");
////            }
////
////            if (logout != null) {
//////                model.addObject("msg", "You've been logged out successfully.");
////            }
//
//        return "login";
//
//    }
//
//    //for 403 access denied page
//    @RequestMapping(value = "/403", method = RequestMethod.GET)
//    public ModelAndView accesssDenied() {
//
//        ModelAndView model = new ModelAndView();
//
//        //check if user is login
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (!(auth instanceof AnonymousAuthenticationToken)) {
//            UserDetails userDetail = (UserDetails) auth.getPrincipal();
//            model.addObject("403", userDetail.getUsername());
//        }
//
//        model.setViewName("403");
//        return model;
//
//    }
//
//}
