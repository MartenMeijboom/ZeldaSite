package nl.saxion.jm.zeldasite.controller;

import nl.saxion.jm.zeldasite.ApplicationManager;
import nl.saxion.jm.zeldasite.model.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("")
public class ApplicationController {

    private ApplicationManager manager;

    private ApplicationManager myManager()
    {
        if(manager == null)
        {
            manager = new ApplicationManager();
        }
        return manager;
    }

    @GetMapping(path = "")
    public String homepage()
    {
        return "home";
    }

    @GetMapping(path = "/items")
    public String itemsPage(Model model)
    {
        model.addAttribute("items", myManager().getItems());
        return "items";
    }

    @GetMapping(path = "/items/{itemid}")
    public String itemPage(Model model, @PathVariable("itemid") int itemId)
    {
        Item item = myManager().getItem(itemId);

        if(item != null)
        {
            model.addAttribute(item);
            return "item";
        }
        else {
            model.addAttribute("error", "404, Item not found");
            return "error";
        }
    }
}
