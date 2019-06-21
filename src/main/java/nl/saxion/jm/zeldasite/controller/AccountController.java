package nl.saxion.jm.zeldasite.controller;

import io.micrometer.core.lang.Nullable;
import io.netty.handler.codec.http.HttpResponse;
import nl.saxion.jm.zeldasite.helper.LoginAttempt;
import nl.saxion.jm.zeldasite.model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@org.springframework.stereotype.Controller
@RequestMapping("")
public class AccountController extends Controller {

    @PostMapping(path = "/updateUser")
    public String updateUser(User user, HttpSession session) {
        User currentUser = myManager().getUser(session.getAttribute("userName").toString());
        currentUser.setFullName(user.getFullName());
        currentUser.setMailAddress(user.getMailAddress());
        currentUser.setPassword(user.getPassword());
        currentUser.setUserName(user.getUserName());
        session.setAttribute("userName", currentUser.getUserName());
        return "profile";
    }

    @PostMapping(path = "/register")
    public String CreateAccount(User user, HttpSession session) {
        myManager().adduser(user);
        session.setAttribute("userName", user.getUserName());
        return "redirect:/overview";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpSession session, Model model) {
        if (session.getAttribute("userName") != null) {
            session.setAttribute("userName", null);
        }
        return "redirect:/login?message=logout";
    }

    @GetMapping(path = "/login")
    public String viewLogin(HttpSession session) {
        if (session.getAttribute("userName") != null) {
            return "redirect:/overview";
        }
        return "login";
    }

    @GetMapping(path = "/register")
    public String viewRegister(HttpSession session) {
        if (session.getAttribute("userName") != null) {
            return "redirect:/overview";
        }
        return "register";
    }

    @PostMapping(path = "/login")
    public String verifyLogin(LoginAttempt attempt, HttpSession session, HttpServletResponse response, @Nullable @CookieValue("lastLogin") String cdate) {
        User user = myManager().verifyLogin(attempt);
        if (user != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            if (cdate == null) {
                try {
                    String encodedeCookie = URLEncoder.encode(dateFormat.format(date), "UTF-8");
                    Cookie cookie = new Cookie("lastLogin", encodedeCookie);
                    response.addCookie(cookie);
                    cookie = new Cookie("lastLoginToShow", encodedeCookie);
                    response.addCookie(cookie);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    String encodedeCookie = URLEncoder.encode(cdate, "UTF-8");
                    Cookie cookie = new Cookie("lastLoginToShow", encodedeCookie);
                    response.addCookie(cookie);

                    encodedeCookie = URLEncoder.encode(dateFormat.format(date), "UTF-8");
                    cookie = new Cookie("lastLogin", encodedeCookie);
                    response.addCookie(cookie);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            session.setAttribute("userName", user.getUserName());
            return "redirect:/overview";
        }

        return "redirect:/login?message=failed";
    }
}
