package com.koreait.autoanswer1.service;

import com.koreait.autoanswer1.vo.GridCoordinate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class WeatherService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${Meteorological_Administration_API_KEY}")
    private String meteorological;

    @Value("${google.api.key}")
    private String apiKey;

    @Value("${TarRlteTarService}")
    private String tarRlte;

    public String getWeatherInfo(GridCoordinate gridCoordinate, String baseDate, String baseTime) {
        String baseUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst"; // 기상청 API URL
        String apiKey = meteorological; // API 키
        String nx = String.valueOf(gridCoordinate.getNx());
        String ny = String.valueOf(gridCoordinate.getNy());

        // 요청 URL 포맷 설정
        String requestUrl = String.format("%s?dataType=JSON&serviceKey=%s&numOfRows=10&pageNo=1&base_date=%s&base_time=%s&nx=%s&ny=%s",
                baseUrl, apiKey, baseDate, baseTime, nx, ny);

        // API 호출 및 결과 반환
        try {
            String response = restTemplate.getForObject(requestUrl, String.class);
            return response; // 날씨 정보 요청 결과 반환
        } catch (Exception e) {
            e.printStackTrace(); // 오류 발생 시 스택 트레이스 출력
            return null; // 오류 발생 시 null 반환
        }
    }
    public List<Double> getLatLng(String location) {
        String baseUrl = "https://api.vworld.kr/req/address?service=address&request=getCoord&version=2.0&crs=epsg:4326";

        // 주소에서 공백을 %20으로 대체하여 수동으로 URL 생성
        String encodedLocation = location.replace(" ", "%20");
        String requestUrl = baseUrl + "&address=" + encodedLocation +
                "&refine=true&simple=false&format=json&type=road&key=" + apiKey;

        try {
            // API 호출
            String response = restTemplate.getForObject(requestUrl, String.class);

            // 응답에서 위도와 경도 추출 로직
            return extractLatLng(response);

        } catch (Exception e) {
            e.printStackTrace();
            return null; // 오류 발생 시 null 반환
        }
    }

    private List<Double> extractLatLng(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        String status = jsonObject.getJSONObject("response").getString("status");

        // 응답 상태 확인
        if ("NOT_FOUND".equals(status)) {
            System.out.println("지정한 주소에 대한 결과를 찾을 수 없습니다.");
            return null; // 주소가 존재하지 않으면 null 반환
        }

        // 결과에서 x와 y 값 가져오기
        JSONObject point = jsonObject.getJSONObject("response").getJSONObject("result").getJSONObject("point");
        double x = point.getDouble("x"); // 경도
        double y = point.getDouble("y"); // 위도

        return Arrays.asList(y, x); // 위도와 경도를 배열로 반환 (순서 주의)
    }
    public String getCurrentMonth() {
        // 현재 날짜를 가져오고 YYYYMM 형태로 변환
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
        return now.format(formatter);
    }
    public String getAreaBasedList(int areaCd, int signguCd) {
        // 관광지 API URL
        String baseUrl = "http://apis.data.go.kr/B551011/TarRlteTarService/areaBasedList";

        // 요청 URL 생성
        String requestUrl = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("serviceKey", tarRlte)
                .queryParam("pageNo", 1)
                .queryParam("numOfRows", 10)
                .queryParam("MobileOS", "ETC") // OS 유형
                .queryParam("MobileApp", "TestApp") // 앱 이름
                .queryParam("serviceKey", tarRlte) // API 키 추가
                .queryParam("_type", "json") // JSON 응답
                .queryParam("baseYm",202410)
                .queryParam("areaCd", areaCd)
                .queryParam("signguCd",signguCd)
                .build()
                .toUriString();
        System.out.println(requestUrl);

        try {
            // API 호출
            ResponseEntity<String> response = restTemplate.getForEntity(requestUrl, String.class);
            return response.getBody(); // API 응답 반환
        } catch (Exception e) {
            e.printStackTrace();
            return null; // 오류 발생 시 null 반환
        }
    }

}
