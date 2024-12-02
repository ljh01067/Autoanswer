package com.koreait.autoanswer1.repository;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface LocalRepository {
    @Select("SELECT sigunguNm FROM location where areaNm='${areaNm}'")
    public List<Map<String, Object>> findSigunguNm(String areaNm);

    @Select("SELECT DISTINCT areaNm FROM location;")
    public List<Map<String, Object>> findAreaList();

    @Select("SELECT areaCd, signguCd FROM location WHERE areaNm = #{areaNm} AND sigunguNm = #{sigunguNm}")
    List<Map<String, Integer>> findAreaCd(@Param("areaNm") String areaNm, @Param("sigunguNm") String sigunguNm);
}
