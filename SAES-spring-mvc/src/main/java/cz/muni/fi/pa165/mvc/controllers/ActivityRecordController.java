package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.ActivityRecordCreateDTO;
import cz.muni.fi.pa165.dto.ActivityRecordDTO;
import cz.muni.fi.pa165.facade.ActivityRecordFacade;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
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
 * Created by Marian Camak (inQool) on 7. 12. 2015.
 */
@Controller
@Import({ServiceConfiguration.class})
@Secured({ADMIN, USER})
@RequestMapping("/record")
public class ActivityRecordController {

    @Inject
    private ActivityRecordFacade recordFacade;

    @Inject
    private UserFacade userFacade;

    /**
     * Shows a list of activity records with the ability to add, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = {"", "/", "/list"}, method = RequestMethod.GET)
    public String list(Model model, Principal principal) {
        principal.getName();
        model.addAttribute("records", recordFacade.findByUser(userFacade.findByUsername(principal.getName()).getId()));
        return "record/list";
    }

    /**
     * Prepares an empty form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCategory(Model model) {
        model.addAttribute("recordCreate", new ActivityRecordCreateDTO());
        return "record/new";
    }
    
     /**
     * Creates new sport activity
     * @param model data to display
     * @return JSP page
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("recordCreate") ActivityRecordCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                model.addAttribute(ge.getObjectName() + "_error", true);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "category/new";
        }
        Long id = recordFacade.create(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Activity record " + id + " was created. ");
        return "redirect:" + uriBuilder.path("/record/list").toUriString();
    }

    /**
     * Shows one activity record
     *
     * @param id    of record
     * @param model data to display
     * @return JSP page
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        try {
            model.addAttribute("record", recordFacade.findById(id));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alert_danger", "Activity record '" + id + "' wasn't found. ");
            return "redirect:" + uriBuilder.path("/record").toUriString();
        }
        return "record/view";
    }

    /**
     * Deletes activity record.
     *
     * @param id                 of record to be deleted
     * @param uriBuilder
     * @param redirectAttributes
     * @return JSP page
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        recordFacade.delete(id);
        redirectAttributes.addFlashAttribute("alert_success", "Activity record '" + id + "' was deleted. ");
        return "redirect:" + uriBuilder.path("/record/list").toUriString();
    }

    /**
     * Updates sport activity
     *
     * @param model data to display
     * @return JSP page
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("recordUpdate") ActivityRecordDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                model.addAttribute(ge.getObjectName() + "_error", true);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "category/new";
        }
        Long id = recordFacade.create(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Activity record " + id + " was created. ");
        return "redirect:" + uriBuilder.path("/record/list").toUriString();
    }
}
