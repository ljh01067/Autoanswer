package com.koreait.resionweather1.service;

import com.koreait.resionweather1.repository.LocalRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class LacalService {
    private final LocalRepository localRepository;

    public LacalService(LocalRepository localRepository) {
        this.localRepository = localRepository;
    }
    public List<Map<String, Object>> findSigunguNm(String areaNm) {
        return localRepository.findSigunguNm(areaNm);
    }

    public List<Map<String, Object>> findAreaList() {
        return localRepository.findAreaList();
    }

    public List<Map<String, Integer>> findAreaCd(String areaNm, String sigunguNm) {
        return localRepository.findAreaCd(areaNm, sigunguNm);
    }
}
