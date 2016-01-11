package cz.muni.fi.pa165.mvc.controllers;


import cz.muni.fi.pa165.dto.SportActivityDTO;
import cz.muni.fi.pa165.exceptions.EntityReferenceException;
import cz.muni.fi.pa165.facade.SportActivityFacade;
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

import static cz.muni.fi.pa165.mvc.security.Roles.ADMIN;

/**
 * @author Marian Camak
 */

@Controller
@Secured(ADMIN)
@RequestMapping("/activity")
public class SportActivityController {

    @Inject
    private SportActivityFacade activityFacade;

    /**
     * List of sport activities.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("activities", activityFacade.findAll());
        return "activity/list";
    }

    /**
     * Prepares an empty form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newActivity(Model model) {
        if (!model.containsAttribute("activity") || model.asMap().get("activity") == null) {
            model.addAttribute("activity", new SportActivityDTO());
        }
        return "activity/new";
    }

    /**
     * Prepares an update form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        model.addAttribute("activity", activityFacade.findById(id));
        return newActivity(model);
    }

    /**
     * Creates new sport activity
     *
     * @param model data to display
     * @return JSP page
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("activity") SportActivityDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                model.addAttribute(ge.getObjectName() + "_error", true);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "activity/new";
        }

        if (formBean.getId() == null) {
            Long newId = activityFacade.create(formBean);
            redirectAttributes.addFlashAttribute("alert_info", "Sport Activity " + newId + " was created. ");
        } else {
            activityFacade.update(formBean);
            redirectAttributes.addFlashAttribute("alert_info", "Sport Activity " + formBean.getId() + " was updated. ");
        }
        return "redirect:" + uriBuilder.path("/activity/list").toUriString();
    }

    /**
     * Delete sport activity.
     *
     * @param id of activity to be deleted
     * @return JSP page
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        try {
            activityFacade.delete(id);
        } catch (EntityReferenceException e) {
            redirectAttributes.addFlashAttribute("alert_danger", "There is record(s) assigned to this sport activity. You must delete them first. ");
            return "redirect:" + uriBuilder.path("/activity/list").toUriString();
        }
        redirectAttributes.addFlashAttribute("alert_info", "Sport activity " + id + " was deleted.");
        return "redirect:" + uriBuilder.path("/activity/list").toUriString();
    }
}
