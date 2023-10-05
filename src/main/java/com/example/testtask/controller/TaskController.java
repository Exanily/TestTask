package com.example.testtask.controller;

import com.example.testtask.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/task")
    public ResponseEntity<String> getCountCharsToString(@RequestParam String str) {
        try {
            String result = taskService.getCountCharsToString(str);
            log.info("request - " + str + "; response - " + result);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            log.error("request - " + str + "; " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
