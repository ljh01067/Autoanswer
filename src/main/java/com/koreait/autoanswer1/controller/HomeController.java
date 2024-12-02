package com.koreait.autoanswer1.controller;

import com.koreait.autoanswer1.service.LacalService;
import com.koreait.autoanswer1.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final LacalService lacalService;

    public HomeController(LacalService lacalService) {
        this.lacalService = lacalService;
    }

    // 지역 목록을 JSON으로 반환
    @GetMapping("/getAreaList")
    @ResponseBody
    public List<Map<String, Object>> findAreaListRedirect() {
        // 지역 목록을 가져옵니다.
        List<Map<String, Object>> areaList = lacalService.findAreaList();
        return areaList; // JSON 형식으로 반환
    }

    // 시군구 목록을 JSON으로 반환
    @GetMapping("/getSigunguList")
    @ResponseBody
    public List<Map<String, Object>> findSigunguNm(@RequestParam String areaNm) {
        // 지역 이름에 해당하는 시군구 목록을 찾는 서비스 메소드 호출
        return lacalService.findSigunguNm(areaNm); // JSON 형식으로 반환
    }
    @GetMapping("/getCode")
    @ResponseBody
    public List<Map<String, Integer>> getCode(@RequestParam String areaNm, @RequestParam String sigunguNm) {
        return lacalService.findAreaCd(areaNm,sigunguNm); // JSON 형식으로 자동 변환
    }

    @GetMapping("/usr/home/main")
    public String showMain() {
        // 별도의 데이터 전달 없이 그냥 메인 페이지를 반환
        return "usr/home/main";
    }
}
