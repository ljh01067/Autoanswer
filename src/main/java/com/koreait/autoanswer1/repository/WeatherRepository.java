package com.koreait.resionweather1.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WeatherRepository {
    @Select("""
           SELECT answer
           FROM auto
           WHERE question = #{question}
           """)
    String getquestion(String question);
}
