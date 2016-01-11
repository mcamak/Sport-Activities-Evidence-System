package cz.muni.fi.pa165.mvc.controllers;


import cz.muni.fi.pa165.dto.BurnedCaloriesDTO;
import cz.muni.fi.pa165.dto.SportActivityDTO;
import cz.muni.fi.pa165.facade.BurnedCaloriesFacade;
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
import java.util.List;

import static cz.muni.fi.pa165.mvc.security.Roles.ADMIN;

/**
 * @author Marian Camak
 */

@Controller
@Secured(ADMIN)
@RequestMapping("/calorie")
public class BurnedCaloriesController {

    @Inject
    private BurnedCaloriesFacade caloriesFacade;

    @Inject
    private SportActivityFacade activityFacade;

    /**
     * List of burned calories.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("calories", caloriesFacade.findAll());
        return "calorie/list";
    }

    @ModelAttribute("activities")
    public List<SportActivityDTO> getActivities() {
        return activityFacade.findAll();
    }


    /**
     * Prepares an empty form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCalorie(Model model) {
        if (!model.containsAttribute("calorie") || model.asMap().get("calorie") == null) {
            model.addAttribute("calorie", new BurnedCaloriesDTO());
        }
        return "calorie/new";
    }

    /**
     * Prepares an update form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable long id, Model model) {
        model.addAttribute("calorie", caloriesFacade.findById(id));
        return newCalorie(model);
    }

    /**
     * Creates new burned calorie
     *
     * @param model data to display
     * @return JSP page
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("calorie") BurnedCaloriesDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                model.addAttribute(ge.getObjectName() + "_error", true);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "calorie/new";
        }

        if (formBean.getId() == null) {
            Long newId = caloriesFacade.create(formBean);
            redirectAttributes.addFlashAttribute("alert_info", "Burned calorie " + newId + " was created. ");
        } else {
            caloriesFacade.update(formBean);
            redirectAttributes.addFlashAttribute("alert_info", "Burned calorie " + formBean.getId() + " was updated. ");
        }
        return "redirect:" + uriBuilder.path("/calorie/list").toUriString();
    }

    /**
     * Delete burned calories with id.
     *
     * @param id of calorie to be deleted
     * @return JSP page
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String remove(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        caloriesFacade.delete(id);
        redirectAttributes.addFlashAttribute("alert_info", "Burned calorie " + id + " was deleted.");
        return "redirect:" + uriBuilder.path("/calorie/list").toUriString();
    }
}

