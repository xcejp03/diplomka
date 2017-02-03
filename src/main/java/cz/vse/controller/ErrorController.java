//package cz.vse.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.annotation.AnnotationUtils;
//import org.springframework.http.HttpStatus;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
///**
// * Created by pcejk on 20.12.2016.
// */
//
//@ControllerAdvice
//public class ErrorController {
//    private final org.apache.log4j.Logger l = org.apache.log4j.Logger.getLogger(this.getClass());
//    private static Logger logger = LoggerFactory.getLogger(ErrorController.class);
//
//    @ExceptionHandler(Throwable.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String exception(final Throwable throwable, final Model model, HttpServletRequest request, HttpServletResponse response) {
//        l.warn("CHYBÁÁÁÁÁÁ- internal server error");
//        l.warn("throwable: "+throwable);
//        l.warn("model: "+model.toString());
//        l.warn("request: "+request.toString());
//        l.warn("response: "+response.toString());
//        logger.error("Exception during execution of SpringSecurity application", throwable);
//        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
//        model.addAttribute("errorMessage", errorMessage);
//        return "error";
//    }
//
//
////    @ExceptionHandler(Throwable.class)
////    @ResponseStatus(HttpStatus.NOT_FOUND)
////    public String exceptiogn(final Throwable throwable, final Model model) {
////        l.warn("CHYBÁÁÁÁÁÁ - not found");
////        logger.error("Exception during execution of SpringSecurity application", throwable);
////        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
////        model.addAttribute("errorMessage", errorMessage);
////        return "error";
////    }
//
////    @ControllerAdvice
////    class GlobalDefaultExceptionHandler {
////        public static final String DEFAULT_ERROR_VIEW = "error";
////
////        @ExceptionHandler(value = Exception.class)
////        public ModelAndView
////        defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
////            l.warn("chybááá 2");
////            // If the exception is annotated with @ResponseStatus rethrow it and let
////            // the framework handle it - like the OrderNotFoundException example
////            // at the start of this post.
////            // AnnotationUtils is a Spring Framework utility class.
////            if (AnnotationUtils.findAnnotation
////                    (e.getClass(), ResponseStatus.class) != null)
////                throw e;
////
////            // Otherwise setup and send the user to a default error-view.
////            ModelAndView mav = new ModelAndView();
////            mav.addObject("exception", e);
////            mav.addObject("url", req.getRequestURL());
////            mav.setViewName(DEFAULT_ERROR_VIEW);
////            return mav;
////        }
////    }
//
//}