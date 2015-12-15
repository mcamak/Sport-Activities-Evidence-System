package cz.muni.fi.pa165.facade;


import cz.muni.fi.pa165.dto.BurnedCaloriesCreateDTO;
import cz.muni.fi.pa165.facade.BurnedCaloriesFacade;
import cz.muni.fi.pa165.service.mapping.ServiceConfiguration;
import cz.muni.fi.pa165.service.facade.BurnedCaloriesFacadeImpl;

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
@RequestMapping("/burnedCalories")
public class BurnedCaloriesController {
    
    @Autowired
    private BurnedCaloriesFacade burnedCaloriesFacade = new BurnedCaloriesFacadeImpl();

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newBurnedCalories(Model model) {
        model.addAttribute("burnedCaloriesCreate", new BurnedCaloriesCreateDTO());
        return "burnedCalories/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("burnedCaloriesCreate") SportActivityCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        
        Long id = burnedCaloriesFacade.create(formBean);
        redirectAttributes.addFlashAttribute("alert_info", "Burned Calories" + id + " was created");
        return "redirect:" + uriBuilder.path("/burnedCalories").toUriString();
    }
    
    
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("burnedCalories", BurnedCaloriesFacade.findById(id));
        return "burnedCalories/detail";
    }
    
    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
    public String remove(@PathVariable("id") long caloriesId, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        BurnedCaloriesFacade.delete(caloriesId);
        redirectAttributes.addFlashAttribute("alert_info", "Burned Calories " + caloriesId + " was deleted.");
        return "redirect:" + uriBuilder.path("/burnedCalories").toUriString();
    }

    
}

