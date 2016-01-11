package cz.muni.fi.pa165.mvc.controllers;


import cz.muni.fi.pa165.dto.SportActivityCreateDTO;
import cz.muni.fi.pa165.exceptions.EntityReferenceException;
import cz.muni.fi.pa165.facade.BurnedCaloriesFacade;
import cz.muni.fi.pa165.facade.SportActivityFacade;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.validation.Valid;

import static cz.muni.fi.pa165.mvc.security.Roles.ADMIN;

/**
 *
 * @author Marian Camak
 */

@Controller
@Secured(ADMIN)
@RequestMapping("/activity")
public class SportActivityController {
    
    @Autowired
    private SportActivityFacade activityFacade;
    
    @Autowired
    private BurnedCaloriesFacade caloriesFacade;
    
    /**
     * Prepares an empty form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newActivity(Model model) {
        if (!model.containsAttribute("activity")) {
            model.addAttribute("activity", new SportActivityCreateDTO());
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
        model.addAttribute("id", id);
        model.addAttribute("activity", activityFacade.findById(id));
        return newActivity(model);
    }
    
     /**
     * Creates new sport activity
     * @param model data to display
     * @return JSP page
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("sportActivityCreate") SportActivityCreateDTO formBean, BindingResult bindingResult,
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

        Long id = activityFacade.create(formBean);
        redirectAttributes.addFlashAttribute("alert_info", "Sport Activity " + id + " was created");
        return "redirect:" + uriBuilder.path("/activity/list").toUriString();
    }
    
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
     * Delete sport activity with id.
     *
     * @param id
     * @param uriBuilder
     * @param redirectAttributes
     * @return JSP page
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String remove(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) throws EntityReferenceException {
        activityFacade.delete(id);
        redirectAttributes.addFlashAttribute("alert_info", "Sport activity " + id + " was deleted.");
        return "redirect:" + uriBuilder.path("/activity/list").toUriString();
    }

    
}
