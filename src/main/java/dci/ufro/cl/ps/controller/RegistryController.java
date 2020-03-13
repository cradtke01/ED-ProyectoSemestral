package dci.ufro.cl.ps.controller;

import dci.ufro.cl.ps.model.RegistryList;
import dci.ufro.cl.ps.model.RegistryManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Carlos Radtke
 * @version 1.0
 * @since 1.0
 */
@Controller //Defines the class as a controller.
public class RegistryController {

    /**
     * The index method handles the user request from the view (web page).
     *
     * @param model Model through which variables will be sent to the view as attributes.
     * @param date  Selected date to filter matching registries.
     * @param time  Selected time to filter matching registries.
     */
    @RequestMapping("/index") //Method's unleashing url.
    public String index(Model model, @RequestParam(name = "date", required = false) String date, @RequestParam(name = "time", required = false) String time) {
        RegistryManager.filterRegistries(date, time);
        model.addAttribute("date", date);
        model.addAttribute("time", time);
        model.addAttribute("registryList", RegistryList.getRegistryList());
        return "index";
    }
}