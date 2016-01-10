package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.UserLogInDTO;
import cz.muni.fi.pa165.facade.UserFacade;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * @author Marian Camak
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Inject
    private UserFacade userFacade;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("logInUser", new UserLogInDTO());
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String logInSuccess(@Valid @ModelAttribute("logInUser") UserLogInDTO formBean) {
        userFacade.logIn(formBean);
        return "/record/list/" + formBean.getId();
    }
}
