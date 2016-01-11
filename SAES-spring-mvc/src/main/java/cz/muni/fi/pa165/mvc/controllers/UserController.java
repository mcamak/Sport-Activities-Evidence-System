package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.exceptions.SaesServiceException;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;
import java.security.Principal;

import static cz.muni.fi.pa165.mvc.security.Roles.ADMIN;
import static cz.muni.fi.pa165.mvc.security.Roles.USER;

/**
 * Created by Marian Camak (inQool) on 10. 1. 2016.
 */
@Controller
@Import({ServiceConfiguration.class})
@RequestMapping("/user")
public class UserController {

    @Inject
    private UserFacade userFacade;

    /**
     * List of user.
     *
     * @param model data to display
     * @return JSP page name
     */
    @Secured(ADMIN)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users", userFacade.findAll());
        return "user/list";
    }

    /**
     * Prepares an empty form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newUser(Model model) {
        if (!model.containsAttribute("user") || model.asMap().get("user") == null) {
            model.addAttribute("user", new UserDTO());
        }
        return "user/new";
    }

    /**
     * Prepares an update form.
     *
     * @param id of user to be updated
     * @param model data to be displayed
     * @return JSP page
     */
    @Secured(ADMIN)
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        model.addAttribute("user", userFacade.findById(id));
        return newUser(model);
    }

    /**
     * Prepares an update form.
     *
     * @param username of the user to be updated
     * @param model data to be displayed
     * @return JSP page
     */
    @Secured(USER)
    @RequestMapping(value = "/update/name={name}", method = RequestMethod.GET)
    public String update(@PathVariable("name") String username, Model model) {
        UserDTO user = userFacade.findByUsername(username);
        user.setPassword("this will be not concerned");
        model.addAttribute("user", user);
        return newUser(model);
    }

    /**
     * Creates new user
     *
     * @param model data to display
     * @return JSP page
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("user") UserDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, Principal p) {

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                model.addAttribute(ge.getObjectName() + "_error", true);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "user/new";
        }

        if (formBean.getId() == null) {
            try {
                userFacade.create(formBean);
                redirectAttributes.addFlashAttribute("alert_info", "User was created. ");
            } catch (SaesServiceException e) {
                model.addAttribute("username_error", true);
                bindingResult.addError(new FieldError("Username", "username", e.getMessage()));
                return "user/new";
            }
        } else {
            userFacade.update(formBean);
            redirectAttributes.addFlashAttribute("alert_info", "User " + formBean.getId() + " was updated. ");
        }
        if (p == null) {
            return "redirect:" + uriBuilder.path("/login").toUriString();
        }
        UserDTO userRequesting = userFacade.findByUsername(p.getName());
        if (userRequesting == null || !userFacade.isAdmin(userRequesting.getId())) {
            return "redirect:" + uriBuilder.path("/login").toUriString();
        } else {
            return "redirect:" + uriBuilder.path("/user/list").toUriString();
        }
    }

    /**
     * Deletes user.
     *
     * @param id of user to be deleted
     * @return JSP page
     */
    @Secured({ADMIN, USER})
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes, Principal p) {
        UserDTO userRequesting = userFacade.findByUsername(p.getName());
        if (userFacade.isAdmin(userRequesting.getId())) {
            userFacade.delete(id);
            if (userRequesting.getId().equals(new Long(id))) {
                redirectAttributes.addFlashAttribute("alert_success", "Your user account was deleted. ");
                return "redirect:" + uriBuilder.path("/login").toUriString();
            } else {
                redirectAttributes.addFlashAttribute("alert_success", "User " + id + " was deleted. ");
                return "redirect:" + uriBuilder.path("/user/list").toUriString();
            }
        } else {
            if (userRequesting.getId().equals(new Long(id))) {
                userFacade.delete(id);
                SecurityContextHolder.clearContext();
                redirectAttributes.addFlashAttribute("alert_success", "Your user account was deleted. ");
                return "redirect:" + uriBuilder.path("/login").toUriString();
            } else {
                redirectAttributes.addFlashAttribute("alert_warning", "You cannot delete another user. ");
                return "redirect:" + uriBuilder.path("/record/list").toUriString();
            }
        }
    }
}
