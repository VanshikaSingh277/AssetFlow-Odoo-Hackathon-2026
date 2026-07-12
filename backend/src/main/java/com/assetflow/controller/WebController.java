package com.assetflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    // Fallback for Vue Router – forwards to index.html
    @RequestMapping(value = "/**/{path:[^\\.]*}")
    public String forward() {
        return "forward:/index.html";
    }

    // Health check endpoint for Render
    @GetMapping("/healthz")
    @ResponseBody
    public String health() {
        return "OK";
    }
}