package com.koreait.autoanswer1.service;

import com.koreait.autoanswer1.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    public String getquestion(String question) {
        String answer = answerRepository.getquestion(question);

        if (answer == null) {
            return "잘못된 질문입니다";
        }

        return answer;
    }
}
