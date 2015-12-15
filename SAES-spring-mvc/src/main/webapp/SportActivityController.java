
import cz.muni.fi.pa165.dto.SportActivityCreateDTO;
import cz.muni.fi.pa165.facade.SportActivityFacade;
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import cz.muni.fi.pa165.service.facade.SportActivityFacadeImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
/**
 *
 * @author Barbora B.
 */

@Controller
@Import({ServiceConfiguration.class})
@RequestMapping("/sportActivity")
public class SportActivityController {
    
    @Autowired
    private SportActivtyFacade sportActivityFacade = new SportActivityFacadeImpl();

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newSportActivity(Model model) {
        model.addAttribute("sportActivityCreate", new SportActivityCreateDTO());
        return "sportActivity/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("sportActivityCreate") SportActivityCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        
        Long id = sportActivityFacade.create(formBean);
        redirectAttributes.addFlashAttribute("alert_info", "Sport Activity " + id + " was created");
        return "redirect:" + uriBuilder.path("/sportActivity").toUriString();
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("sportActivities", SportActivityFacade.findAll());
        return "sportActivity/list";
    }
    
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
    public String remove(@PathVariable("id") long activityId, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        sportActivityFacade.delete(activityId);
        redirectAttributes.addFlashAttribute("alert_info", "SportActivity " + activityId + " was deleted.");
        return "redirect:" + uriBuilder.path("/sportActivity").toUriString();
    }

    
}
