package jary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 *
 * @author <a href='mailto:jeremy.ary@gmail.com'>jary</a>
 */
@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping
    public String home() {
        return "/WEB-INF/views/angular-index.jsp";
    }
}