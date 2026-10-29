package com.makpyralua.studentapi;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @Value("${app.version}")
    private String version;

    @Value("${app.student}")
    private String student;

    @GetMapping("/api/info")
    public Map<String, String> info() {
        Map<String, String> info = new LinkedHashMap<>();
        info.put("application", "makpyr-alua-student-api");
        info.put("student", student);
        info.put("group", "IT2-2306");
        info.put("version", version);
        return info;
    }
}
