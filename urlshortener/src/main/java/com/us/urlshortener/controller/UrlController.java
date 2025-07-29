package com.us.urlshortener.controller;

import com.us.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1")
public class UrlController {
    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public Map<String, String> shorten(@RequestParam String originalUrl,
                                       @RequestParam(required = false) String customCode,
                                       @RequestParam(required = false) Integer userId) {
        System.out.println("Enter==");
        System.out.println("originalUrl=="+originalUrl);
        System.out.println("customCode=="+customCode);
        System.out.println("userId=="+userId);

        String code = urlService.shortenUrl(originalUrl, userId, customCode);

        return Map.of("shortCode", code);
    }

    @GetMapping("/s/{code}")
    public String redirect(@PathVariable String code) {
        return urlService.getOriginalUrl(code);
    }
}