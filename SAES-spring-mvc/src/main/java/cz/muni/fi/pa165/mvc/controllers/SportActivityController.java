package cz.muni.fi.pa165.mvc.controllers;


import cz.muni.fi.pa165.dto.SportActivityCreateDTO;
import cz.muni.fi.pa165.dto.SportActivityDTO;
import cz.muni.fi.pa165.exceptions.EntityReferenceException;
import cz.muni.fi.pa165.facade.BurnedCaloriesFacade;
import cz.muni.fi.pa165.facade.SportActivityFacade;
//import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import static cz.muni.fi.pa165.mvc.security.Roles.ADMIN;
import static cz.muni.fi.pa165.mvc.security.Roles.USER;
import cz.muni.fi.pa165.saes.entity.SportActivity;
import org.springframework.security.access.annotation.Secured;
/**
 *
 * @author Barbora B.
 */

@Controller
@Secured(ADMIN)
@RequestMapping("/sportActivity")
public class SportActivityController {
    
    @Autowired
    private SportActivityFacade sportActivityFacade;
    
    @Autowired
    private BurnedCaloriesFacade burnedCaloriesFacade;
    
    /**
     * Prepares an empty form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newSportActivity(Model model) {
        model.addAttribute("sportActivityCreate", new SportActivityCreateDTO());
        return "sportActivity/new";
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
            return "sportActivity/new";
        }
        
        Long id = sportActivityFacade.create(formBean);
        redirectAttributes.addFlashAttribute("alert_info", "Sport Activity " + id + " was created");
        return "redirect:" + uriBuilder.path("/sportActivity").toUriString();
    }
    
    
    /**
     * List of sport activities.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("sportActivities", sportActivityFacade.findAll());
        return "sportActivity/list";
    }
    
    
    /**
     * Delete sport activity with id.
     *
     * @param id             
     * @param uriBuilder
     * @param redirectAttributes
     * @return JSP page
     */
    @Secured(ADMIN)
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
    public String remove(@PathVariable long activityId, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) throws EntityReferenceException {
        sportActivityFacade.delete(activityId);
        redirectAttributes.addFlashAttribute("alert_info", "SportActivity " + activityId + " was deleted.");
        return "redirect:" + uriBuilder.path("/sportActivity").toUriString();
    }

    
}
