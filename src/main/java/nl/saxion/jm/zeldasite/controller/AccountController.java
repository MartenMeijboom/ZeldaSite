package nl.saxion.jm.zeldasite.controller;

import nl.saxion.jm.zeldasite.helper.LoginAttempt;
import nl.saxion.jm.zeldasite.model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@org.springframework.stereotype.Controller
@RequestMapping("")
public class AccountController extends Controller {

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
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            session.setAttribute("lastLogin", dateFormat.format(date));

            session.setAttribute("userName", user.getUserName());
            return "redirect:/overview";
        }

        return "redirect:/login?message=failed";
    }
}
