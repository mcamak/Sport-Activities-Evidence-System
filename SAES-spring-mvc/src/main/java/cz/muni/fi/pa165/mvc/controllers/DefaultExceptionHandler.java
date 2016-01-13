package cz.muni.fi.pa165.mvc.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    public ModelAndView dbError(DataAccessException e) {
        ModelAndView mav = new ModelAndView("/error");
        mav.addObject("eMessage", e.getMessage());
        mav.addObject("eCode", 900);
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView otherError(Exception e) {
        ModelAndView mav = new ModelAndView("/error");
        mav.addObject("eMessage", e.getMessage());
        mav.addObject("eCode", 500);
        return mav;
    }
}
