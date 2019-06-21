package nl.saxion.jm.zeldasite.controller;

import nl.saxion.jm.zeldasite.model.Boss;
import nl.saxion.jm.zeldasite.model.Item;
import nl.saxion.jm.zeldasite.model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
@RequestMapping("")
public class ModelController extends Controller {

    @GetMapping(path = "/items")
    public String itemsPage(Model model) {

        model.addAttribute("items", myManager().getItems());
        model.addAttribute("types", myManager().getTypeNames());
        return "items";
    }

    @GetMapping(path = "/items/search")
    public String itemsPage(Model model, @RequestParam("query") String query) {
        model.addAttribute("items", myManager().getItems(query));
        model.addAttribute("types", myManager().getTypeNames());
        return "items";
    }

    @GetMapping(path = "/items/{itemid}")
    public String itemPage(Model model, @PathVariable("itemid") int itemId) {
        Item item = myManager().getItem(itemId);

        if (item != null) {
            model.addAttribute(item);
            return "item";
        } else {
            model.addAttribute("error", "404, Item not found");
            return "error";
        }
    }

    @GetMapping(path = "/bosses")
    public String bossesPage(Model model) {
        model.addAttribute("bosses", myManager().getBosses());
        model.addAttribute("games", myManager().getSeenInNames());
        return "bosses";
    }

    @GetMapping(path = "/bosses/search")
    public String bossesPage(Model model, @RequestParam("query") String query) {
        model.addAttribute("bosses", myManager().getBosses(query));
        model.addAttribute("types", myManager().getTypeNames());
        return "bosses";
    }

    @GetMapping(path = "/bosses/{bossid}")
    public String bossPage(Model model, @PathVariable("bossid") int bossId) {
        Boss boss = myManager().getBoss(bossId);

        if (boss != null) {
            model.addAttribute(boss);
            return "boss";
        } else {
            model.addAttribute("error", "404, Boss not found");
            return "error";
        }
    }

    @PostMapping(path = "/items")
    public String addItem(Item item) {
        myManager().addItem(item);
        return "redirect:/items";
    }

    @PostMapping(path = "/bosses")
    public String addItem(Boss boss) {
        myManager().addBoss(boss);
        return "redirect:/bosses";
    }


    @PostMapping(path = "/addItemToCharacter")
    public String addItemToCharacter(Integer itemid, HttpSession session) {
        Item item = myManager().getItem(itemid);
        User user = myManager().getUser(session.getAttribute("userName").toString());
        user.addItem(item);
        return "redirect:/overview";
    }

    @PostMapping(path = "/addBossToCharacter")
    public String addBossToCharacter(Integer bossid, HttpSession session) {
        Boss boss = myManager().getBoss(bossid);
        User user = myManager().getUser(session.getAttribute("userName").toString());
        user.addBoss(boss);
        return "redirect:/overview";
    }

}
