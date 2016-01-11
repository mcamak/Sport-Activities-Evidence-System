package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.UserLogInDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 * @author Marian Camak
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("userLogin", new UserLogInDTO());
        return "/login";
    }

    /**
     * Creates new sport activity
     *
     * @param model data to display
     * @return JSP page
     */
    @RequestMapping(method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute("userLogin") UserLogInDTO formBean, BindingResult bindingResult,
                        Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                model.addAttribute(ge.getObjectName() + "_error", true);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "login";
        }
        redirectAttributes.addAttribute("alert_success", "You have been successfully logged in. ");
        return "redirect:" + uriBuilder.path("/record/list").toUriString();
    }
}
