package nl.saxion.jm.zeldasite.controller;

import nl.saxion.jm.zeldasite.helper.LoginAttempt;
import nl.saxion.jm.zeldasite.model.Boss;
import nl.saxion.jm.zeldasite.model.Item;
import nl.saxion.jm.zeldasite.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class accountController extends controller{

    @PostMapping(path = "/updateUser")
    public String updateUser(User user, HttpSession session)
    {
        User currentUser = myManager().getUser(session.getAttribute("userName").toString());
        currentUser.setFullName(user.getFullName());
        currentUser.setMailAddress(user.getMailAddress());
        currentUser.setPassword(user.getPassword());
        currentUser.setUserName(user.getUserName());
        session.setAttribute("userName", currentUser.getUserName());
        return "profile";
    }

    @PostMapping(path = "/addItemToCharacter")
    public String addItemToCharacter(Integer itemid, HttpSession session)
    {
        Item item = myManager().getItem(itemid);
        User user = myManager().getUser(session.getAttribute("userName").toString());
        user.addItem(item);
        return "redirect:/overview";
    }

    @PostMapping(path = "/addBossToCharacter")
    public String addBossToCharacter(Integer bossid, HttpSession session)
    {
        Boss boss = myManager().getBoss(bossid);
        User user = myManager().getUser(session.getAttribute("userName").toString());
        user.addBoss(boss);
        return "redirect:/overview";
    }

    @PostMapping(path = "/register")
    public String CreateAccount(User user, HttpSession session)
    {
        myManager().adduser(user);
        session.setAttribute("userName", user.getUserName());
        return "redirect:/overview";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpSession session, Model model)
    {
        if(session.getAttribute("userName") != null)
        {
            session.setAttribute("userName", null);
        }
        return "redirect:/login?message=logout";
    }

    @GetMapping(path = "/login")
    public String viewLogin(HttpSession session)
    {
        if(session.getAttribute("userName") != null)
        {
            return "redirect:/overview";
        }
        return "login";
    }

    @GetMapping(path = "/register")
    public String viewRegister(HttpSession session)
    {
        if(session.getAttribute("userName") != null)
        {
            return "redirect:/overview";
        }
        return "register";
    }

    @PostMapping(path = "/login")
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
}
