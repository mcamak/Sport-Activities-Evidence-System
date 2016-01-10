package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.facade.UserFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * @author Marian Camak
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Inject
    private UserFacade userFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }
}
