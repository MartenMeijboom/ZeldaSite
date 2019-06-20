package nl.saxion.jm.zeldasite.controller;

import nl.saxion.jm.zeldasite.ApplicationManager;
import nl.saxion.jm.zeldasite.helper.LoginAttempt;
import nl.saxion.jm.zeldasite.model.Boss;
import nl.saxion.jm.zeldasite.model.Item;
import nl.saxion.jm.zeldasite.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.Region;
import java.util.ArrayList;

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
        model.addAttribute("types", myManager().getTypeNames());
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

    @GetMapping(path = "/bosses")
    public String bossesPage(Model model)
    {
        model.addAttribute("bosses", myManager().getBosses());
        model.addAttribute("games", myManager().getSeenInNames());
        return "bosses";
    }

    @GetMapping(path = "/bosses/{bossid}")
    public String bossPage(Model model, @PathVariable("bossid") int bossId)
    {
        Boss boss = myManager().getBoss(bossId);

        if(boss != null)
        {
            model.addAttribute(boss);
            return "boss";
        }
        else {
            model.addAttribute("error", "404, Boss not found");
            return "error";
        }
    }

    @PostMapping(path = "/items")
    public String addItem(Item item)
    {
        myManager().addItem(item);
        return "redirect:/items";
    }

    @PostMapping(path = "/bosses")
    public String addItem(Boss boss)
    {
        myManager().addBoss(boss);
        return "redirect:/bosses";
    }

    @GetMapping(path = "login")
    public String viewLogin(HttpSession session)
    {
        if(session.getAttribute("userName") != null)
        {
            return "redirect:/overview";
        }
        return "login";
    }

    @PostMapping(path = "login")
    public String verifyLogin(LoginAttempt attempt, HttpSession session)
    {
        User user = myManager().verifyLogin(attempt);
        if(user != null)
        {
            session.setAttribute("userName", user.getUserName());
            return "redirect:/overview";
        }

        return "redirect:/login?message=failed";
    }

    @GetMapping(path = "profile")
    public String viewProfile(HttpSession session, Model model)
    {
        if(session.getAttribute("userName") != null)
        {
            User user = myManager().getUser(session.getAttribute("userName").toString());
            model.addAttribute(user);
            return "profile";
        }
        return "login";
    }

    @GetMapping(path = "overview")
    public String overview(HttpSession session, Model model)
    {
        if(session.getAttribute("userName") != null)
        {
            User user = myManager().getUser(session.getAttribute("userName").toString());
            ArrayList<Item> myitems = user.getItems();
            ArrayList<Boss> mybosses = user.getDefeatedBosses();
            ArrayList<Item> items = myManager().getItems();
            ArrayList<Boss> bosses = myManager().getBosses();

            model.addAttribute(user);
            model.addAttribute("myitems", myitems);
            model.addAttribute("mybosses", mybosses);
            model.addAttribute("items", items);
            model.addAttribute("bosses", bosses);
            return "overview";
        }
        return "login";
    }

    @GetMapping(path = "logout")
    public String logout(HttpSession session, Model model)
    {
        if(session.getAttribute("userName") != null)
        {
            session.setAttribute("userName", null);
        }
        return "redirect:/login?message=logout";
    }
}
