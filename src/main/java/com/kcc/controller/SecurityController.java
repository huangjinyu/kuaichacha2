package com.kcc.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequestMapping("/security")
public class SecurityController {

    @RequestMapping("/login.html")
    public String login() {
        return "security/login";
    }

    @RequestMapping("/logout.html")
    public String logout() {
        return "security/logout";
    }

//    public void logout() {
////        new SecurityContextLogoutHandler().logout();
//    }

    /**
     * 通过Principal参数获取
     *
     * @param principal
     * @return
     */
    @RequestMapping(value = "/username1", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    /**
     * 通过Authentication参数获取
     *
     * @param authentication
     * @return
     */
    @RequestMapping(value = "/username2", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        return authentication.getName();
    }
}
