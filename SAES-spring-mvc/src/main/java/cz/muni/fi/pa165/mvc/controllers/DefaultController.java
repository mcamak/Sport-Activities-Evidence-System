package cz.muni.fi.pa165.mvc.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Marian Camak
 */
@Controller
@RequestMapping({"", "/"})
public class DefaultController {

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultView(UriComponentsBuilder uriBuilder) {
        return "redirect:" + uriBuilder.path("/record/list").toUriString();
    }

    @RequestMapping(value = "error/{code}")
    public String error(@PathVariable String code, Model model) {
        model.addAttribute("code", code);
        return "error";
    }
}
