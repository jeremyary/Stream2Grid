package jary.controllers;

import jary.model.KickoffResponse;
import jary.service.KickoffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author <a href='mailto:jeremy.ary@gmail.com'>jary</a>
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    protected KickoffService kickoffService;

    @RequestMapping
    public String home() {
        return "/index.html";
    }

    @RequestMapping("kickoff")
    public @ResponseBody KickoffResponse kickoff() throws Exception {
        return kickoffService.getResponse();
    }
}