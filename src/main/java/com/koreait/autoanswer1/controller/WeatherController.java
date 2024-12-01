package com.koreait.resionweather1.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import com.koreait.resionweather1.service.LacalService;
import com.koreait.resionweather1.service.WeatherService;
import com.koreait.resionweather1.vo.GridCoordinate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class WeatherController {

    private final WeatherService weatherService; // WeatherService 인스턴스 추가
    private final LacalService lacalService;

    public WeatherController(WeatherService weatherService, LacalService lacalService) {
        this.weatherService = weatherService; // 초기화
        this.lacalService = lacalService;
    }

    // 날씨 정보 요청을 처리하는 메소드 추가
    @GetMapping("/usr/walk/getWeather")
    @ResponseBody
    public String getWeather(@RequestParam String date, @RequestParam String hour, @RequestParam String nx, @RequestParam String ny) {
        GridCoordinate gridCoordinate = new GridCoordinate();
        gridCoordinate.setNx(nx); // 요청받은 nx 값 사용
        gridCoordinate.setNy(ny); // 요청받은 ny 값 사용

        // 날씨 정보 가져오기
        String weatherInfo = weatherService.getWeatherInfo(gridCoordinate, date, hour);

        return weatherInfo;
    }

    @GetMapping("/usr/walk/getShow")
    @ResponseBody
    public String getShow(@RequestParam int areaCd, @RequestParam int signguCd) {
        String ShowInfo = weatherService.getAreaBasedList(areaCd,signguCd);
        System.out.println(ShowInfo);
        return ShowInfo;
    }

    @GetMapping("/getLatLng")
    public ResponseEntity<List<Double>> getLatLng(@RequestParam String location) {
        try {
            List<Double> latLng = weatherService.getLatLng(location);
            return ResponseEntity.ok(latLng); // 위도와 경도 반환
        } catch (Exception e) {
            System.err.println("Error fetching latitude and longitude: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Arrays.asList(null, null)); // null 반환 또는 예외 처리
        }
    }
}
