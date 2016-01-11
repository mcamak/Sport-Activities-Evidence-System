package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.UserCreateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.exceptions.SaesServiceException;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.annotation.Secured;
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

    final static Logger logger = LoggerFactory.getLogger(ActivityRecordController.class);

    @Inject
    private UserFacade userFacade;

    /**
     * Prepares an update form for regular user.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @Secured(USER)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String view(Model model, Principal principal) {
        UserDTO userDTO = userFacade.findByUsername(principal.getName());
        model.addAttribute("id", userDTO.getId());
        model.addAttribute("user", userDTO);
        return newUser(model);
    }

    /**
     * Shows a list of users with the ability to add, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @Secured(ADMIN)
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
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
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new UserCreateDTO());
        }
        return "user/new";
    }

    /**
     * Prepares an update form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @Secured(ADMIN)
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        UserDTO userDTO = userFacade.findById(id);
        model.addAttribute("id", userDTO.getId());
        model.addAttribute("user", userDTO);
        return newUser(model);
    }

    /**
     * Creates new user
     *
     * @param model data to display
     * @return JSP page
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("user") UserCreateDTO formBean, BindingResult bindingResult,
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

        try {
            userFacade.create(formBean);
        } catch (SaesServiceException e) {
            model.addAttribute("username_error", true);
            bindingResult.addError(new FieldError("Username", "username", e.getMessage()));
            return "user/new";
        }
        redirectAttributes.addFlashAttribute("alert_success", "User was created. ");
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
     * Updates an user
     *
     * @param model data to display
     * @return JSP page
     */
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    public String update(@PathVariable long id, @Valid @ModelAttribute("user") UserCreateDTO formBean, BindingResult bindingResult,
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
        UserDTO updateDTO = (UserDTO) formBean;
        updateDTO.setId(id);
        try {
            userFacade.update(updateDTO);
        } catch (SaesServiceException e) {
            model.addAttribute("username_error", true);
            bindingResult.addError(new FieldError("Username", "username", e.getMessage()));
            return "user/new";
        }
        redirectAttributes.addFlashAttribute("alert_success", "User was created. ");

        UserDTO userRequesting = userFacade.findByUsername(p.getName());
        if (userRequesting == null || !userFacade.isAdmin(userRequesting.getId())) {
            return "redirect:" + uriBuilder.path("/login").toUriString();
        } else {
            return "redirect:" + uriBuilder.path("/user/list").toUriString();
        }
    }

    /**
     * Deletes User.
     *
     * @param id                 of record to be deleted
     * @param uriBuilder
     * @param redirectAttributes
     * @return JSP page
     */
    @Secured({ADMIN, USER})
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes, Model model, Principal p) {
        UserDTO userRequesting = userFacade.findByUsername(p.getName());
        if (userFacade.isAdmin(userRequesting.getId())) {
            redirectAttributes.addFlashAttribute("alert_success", "User " + id + " was deleted. ");
            userFacade.delete(id);
            return "redirect:" + uriBuilder.path("/user/list").toUriString();
        } else {
            if (userRequesting.getId().equals(new Long(id))) {
                userFacade.delete(id);
                redirectAttributes.addFlashAttribute("alert_success", "User " + id + " was deleted. ");
                return "redirect:" + uriBuilder.path("/login").toUriString();
            } else {
                redirectAttributes.addFlashAttribute("alert_danger", "You cannot delete another user. ");
                return "redirect:" + uriBuilder.path("/record/list").toUriString();
            }
        }
    }
}
