package com.example.weatherwebservice.clientip.controller;

import com.example.weatherwebservice.clientip.service.RequestService;
import io.ipinfo.api.IPinfo;
import io.ipinfo.api.errors.RateLimitedException;
import io.ipinfo.api.model.IPResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class HomeController {

    @Autowired
    private RequestService requestService;

    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request) throws IOException {
        ModelAndView model = new ModelAndView("index");
        String clientIp = requestService.getClientIp();
        model.addObject("clientIp", clientIp);

        return model;
    }


}
