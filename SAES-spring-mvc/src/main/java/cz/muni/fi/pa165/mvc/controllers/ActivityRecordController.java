package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.ActivityRecordCreateDTO;
import cz.muni.fi.pa165.dto.ActivityRecordDTO;
import cz.muni.fi.pa165.dto.SportActivityDTO;
import cz.muni.fi.pa165.facade.ActivityRecordFacade;
import cz.muni.fi.pa165.facade.SportActivityFacade;
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
import java.util.List;

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

    final static Logger logger = LoggerFactory.getLogger(ActivityRecordController.class);

    @Inject
    private ActivityRecordFacade recordFacade;

    @Inject
    private SportActivityFacade activityFacade;

    @Inject
    private UserFacade userFacade;

    /**
     * Shows a list of activity records with the ability to add, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String list(Model model, Principal principal) {
        model.addAttribute("records", recordFacade.findByUser(userFacade.findByUsername(principal.getName()).getId()));
        return "record/list";
    }

    @ModelAttribute("activities")
    public List<SportActivityDTO> activities() {
        return activityFacade.findAll();
    }

    /**
     * Prepares an empty form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newRecord(Model model) {
        logger.info("new");
        if (!model.containsAttribute("record")) {
            model.addAttribute("record", new ActivityRecordCreateDTO());
        }
        return "record/new";
    }

    /**
     * Prepares an update form.
     *
     * @param model data to be displayed
     * @return JSP page
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateRecord(@PathVariable long id, Model model) {
        ActivityRecordDTO recordDTO = recordFacade.findById(id);
        model.addAttribute("record", recordDTO);
        return newRecord(model);
    }

    /**
     * Creates new sport activity
     *
     * @param model data to display
     * @return JSP page
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createRecord(@Valid @ModelAttribute("record") ActivityRecordCreateDTO formBean, BindingResult bindingResult,
                               Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                model.addAttribute(ge.getObjectName() + "_error", true);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "record/new";
        }
        if (formBean instanceof ActivityRecordDTO) {
            ActivityRecordDTO updateDTO = (ActivityRecordDTO) formBean;
            recordFacade.update(updateDTO);
            redirectAttributes.addFlashAttribute("alert_success", "Activity record " + updateDTO.getId() + " was updated. ");
        } else {
            Long id = recordFacade.create(formBean);
            redirectAttributes.addFlashAttribute("alert_success", "Activity record " + id + " was created. ");
        }
        return "redirect:" + uriBuilder.path("/record/list").toUriString();
    }

    /**
     * Creates new sport activity
     *
     * @param model data to display
     * @return JSP page
     */
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    public String UPDATE(@Valid @ModelAttribute("record") ActivityRecordCreateDTO formBean, BindingResult bindingResult,
                         @PathVariable long id, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                model.addAttribute(ge.getObjectName() + "_error", true);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "record/new";
        }
        ActivityRecordDTO updateDTO = (ActivityRecordDTO) formBean;
        updateDTO.setId(id);
        recordFacade.update(updateDTO);
        redirectAttributes.addFlashAttribute("alert_success", "Activity record " + updateDTO.getId() + " was updated. ");
        return "redirect:" + uriBuilder.path("/record/list").toUriString();
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
}
