package nl.saxion.jm.zeldasite.controller;

import nl.saxion.jm.zeldasite.model.Boss;
import nl.saxion.jm.zeldasite.model.Item;
import nl.saxion.jm.zeldasite.model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@org.springframework.stereotype.Controller
@RequestMapping("")
public class ApplicationController extends Controller {

    @GetMapping(path = "")
    public String homepage()
    {
        return "home";
    }

    @GetMapping(path = "/profile")
    public String viewProfile(HttpSession session, Model model)
    {
        if(session.getAttribute("userName") != null)
        {
            if(session.getAttribute("lastLogin") != null)
            {
                model.addAttribute("lastlogin", session.getAttribute("LastLoginToShow"));
            }
            else
            {
                model.addAttribute("lastlogin", "N/A");
            }

            User user = myManager().getUser(session.getAttribute("userName").toString());

            model.addAttribute(user);
            return "profile";
        }
        return "login";
    }

    @GetMapping(path = "/overview")
    public String overview(HttpSession session, Model model)
    {
        if(session.getAttribute("userName") != null)
        {
            if(session.getAttribute("lastLogin") != null)
            {
                model.addAttribute("lastlogin", session.getAttribute("LastLoginToShow"));
            }
            else
            {
                model.addAttribute("lastlogin", "N/A");
            }

            User user = myManager().getUser(session.getAttribute("userName").toString());
            ArrayList<Item> myitems = user.getItems();
            ArrayList<Boss> mybosses = user.getDefeatedBosses();
            ArrayList<Item> items = myManager().getItems();
            ArrayList<Boss> bosses = myManager().getBosses();

            for (Item i:myitems) {
                if(items.contains(i))
                {
                    items.remove(i);
                }
            }
            for (Boss b:mybosses) {
                if(bosses.contains(b))
                {
                    bosses.remove(b);
                }
            }

            model.addAttribute(user);
            model.addAttribute("myitems", myitems);
            model.addAttribute("mybosses", mybosses);
            model.addAttribute("items", items);
            model.addAttribute("bosses", bosses);
            return "overview";
        }
        return "login";
    }
}
