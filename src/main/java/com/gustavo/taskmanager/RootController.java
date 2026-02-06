package com.gustavo.taskmanager;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String root() {
        return "{\"status\":\"ok\",\"service\":\"task-manager-api\"}";
    }
}
