package cz.vse.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by pcejk on 02.02.2017.
 */
@Controller
public class CustomErrorController implements ErrorController {
    private final Logger l = Logger.getLogger(this.getClass());
    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = PATH)
    String error(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("status", response.getStatus());
        model.addAttribute("atributy", getErrorAttributes(request, false));
        model.addAttribute("error", getErrorAttributes(request, false).get("error"));
        model.addAttribute("msg", getErrorAttributes(request, false).get("message"));
        model.addAttribute("stackTrace", getErrorAttributes(request, true));

        l.info("status: " + response.getStatus());
        l.info("error: " + getErrorAttributes(request, false).get("error"));
        l.info("msg: " + getErrorAttributes(request, false).get("message"));
        l.info("atributy:" + getErrorAttributes(request, false));

        if (response.getStatus() == 404) {
            l.warn("return page 404");
            return "404";
        }

        if (response.getStatus() == 400) {
            l.warn("return page 400");
            return "400";
        }

        if (response.getStatus() == 500) {
            l.warn("return page 500");
            return "500";
        }
        l.warn("vracím obecnou error stránku ");
        return "error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }


}


