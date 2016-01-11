package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.ActivityRecordDTO;
import cz.muni.fi.pa165.dto.SportActivityDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.exceptions.SaesServiceException;
import cz.muni.fi.pa165.facade.ActivityRecordFacade;
import cz.muni.fi.pa165.facade.SportActivityFacade;
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

    @Inject
    private ActivityRecordFacade recordFacade;

    @Inject
    private SportActivityFacade activityFacade;

    @Inject
    private UserFacade userFacade;

    /**
     * List of activity records.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, Principal principal, RedirectAttributes redirectAttributes) {
        UserDTO user = userFacade.findByUsername(principal.getName());
        if (user == null) {
            redirectAttributes.addAttribute("alert_warning", "User with username '" + principal.getName() + "' wasn't found. ");
        }
        model.addAttribute("records", recordFacade.findByUser(user.getId()));
        return "record/list";
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
    public String newRecord(Model model, Principal p) {
        if (!model.containsAttribute("record") || model.asMap().get("record") == null) {
            UserDTO userRequesting = userFacade.findByUsername(p.getName());
            ActivityRecordDTO recordDTO = new ActivityRecordDTO();
            recordDTO.setUser(userRequesting);
            model.addAttribute("record", recordDTO);
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
    public String update(@PathVariable long id, Model model, Principal p) {
        model.addAttribute("record", recordFacade.findById(id));
        return newRecord(model, p);
    }

    /**
     * Creates new sport activity
     *
     * @param model data to display
     * @return JSP page
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("record") ActivityRecordDTO formBean, BindingResult bindingResult,
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

        if (formBean.getId() == null) {
            try {
                Long newId = recordFacade.create(formBean);
                redirectAttributes.addFlashAttribute("alert_info", "Activity record " + newId + " was created. ");
            } catch (SaesServiceException e) {
                model.addAttribute("alert_danger", e.getMessage());
                return "record/new";
            }
        } else {
            try {
                recordFacade.update(formBean);
                redirectAttributes.addFlashAttribute("alert_info", "Activity record " + formBean.getId() + " was updated. ");
            } catch (SaesServiceException e) {
                model.addAttribute("alert_danger", e.getMessage());
                return "record/new";
            }
        }
        return "redirect:" + uriBuilder.path("/record/list").toUriString();
    }

    /**
     * Deletes activity record.
     *
     * @param id of record to be deleted
     * @return JSP page
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        recordFacade.delete(id);
        redirectAttributes.addFlashAttribute("alert_success", "Activity record " + id + " was deleted. ");
        return "redirect:" + uriBuilder.path("/record/list").toUriString();
    }
}
