package com.koreait.autoanswer1.controller;

import com.koreait.autoanswer1.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/usr/answer/question")
    public ResponseEntity<String> getquestion(@RequestParam String question) {
        String answer = answerService.getquestion(question);
        return ResponseEntity.ok(answer); // 직접 문자열 반환
    }
}
