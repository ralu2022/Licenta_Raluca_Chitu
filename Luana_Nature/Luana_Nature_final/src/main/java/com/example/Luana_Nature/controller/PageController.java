
/*
 * Controller responsible for handling HTTP requests for view pages that provide information only.
 * These pages do not include any operations like create, update, or delete.
 * The controller maps requests to corresponding HTML templates that display the content for various pages in the
 * application.
 *
 */


package com.example.Luana_Nature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/loginadmin")
    public String loginadmin() {
        return "/loginadmin";
    }

    @GetMapping("/mainpage")
    public String mainpage() {
        return "mainpage";
    }

    @GetMapping("/mainpageuser")
    public String mainpageuser() {
        return "mainpageuser";
    }

    @GetMapping("/mainpageadmin")
    public String mainpageadmin() {
        return "mainpageadmin";
    }

    @GetMapping("/companies")
    public String companii() {
        return "companies";
    }

    @GetMapping("/companiesuser")
    public String companiiuser() {
        return "companiesuser";
    }

    @GetMapping("/companiesadmin")
    public String companiiadmin() {
        return "companiesadmin";
    }

    @GetMapping("/individuals")
    public String individual() {
        return "individuals";
    }

    @GetMapping("/individualsuser")
    public String individualuser() {
        return "individualsuser";
    }

    @GetMapping("/individualsadmin")
    public String individualadmin() {
        return "individualsadmin";
    }

    @GetMapping("/kids")
    public String kids() {
        return "kids";
    }

    @GetMapping("/kidsuser")
    public String kidsuser() {
        return "kidsuser";
    }

    @GetMapping("/kidsadmin")
    public String kidsadmin() {
        return "kidsadmin";
    }

    @GetMapping("/catering")
    public String catering() {
        return "catering";
    }

    @GetMapping("/cateringuser")
    public String cateringuser() {
        return "cateringuser";
    }

    @GetMapping("/cateringadmin")
    public String cateringadmin() {
        return "cateringadmin";
    }

    @GetMapping("/aboutus")
    public String aboutus() {
        return "aboutus";
    }

    @GetMapping("/aboutususer")
    public String aboutususer() {
        return "aboutususer";
    }

    @GetMapping("/aboutusadmin")
    public String aboutusadmin() {
        return "aboutusadmin";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contactmessages";
    }



}
