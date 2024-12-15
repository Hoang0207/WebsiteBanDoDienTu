package com.group4.controller;

import com.group4.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class LanguageController {

    @Autowired
    private SessionUtil sessionUtil;

    // API để thay đổi ngôn ngữ và lưu vào session
    @GetMapping("/set-language")
    public String setLanguage(@RequestParam("lang") String language) {
        // Lưu ngôn ngữ vào session
        sessionUtil.set("language", language);
        return "Ngôn ngữ đã được thay đổi sang: " + language;
    }

    // API để lấy ngôn ngữ từ session
    @GetMapping("/api/get-language")
    public String getLanguage() {
        // Lấy ngôn ngữ từ session, nếu không có, mặc định là 'vi' (Tiếng Việt)
        String language = sessionUtil.get("language");
        if (language == null) {
            language = "vi";  // Tiếng Việt mặc định
        }
        return "{\"language\": \"" + language + "\"}";
    }
}
