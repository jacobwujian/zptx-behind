package com.ecit.edu.zpxtbehind.result.mapper;



import com.ecit.edu.zpxtbehind.result.bean.Result;


public interface ResultMapper {
    void insertResult(Result result);
    void updateResult(Result result);
    void deleteResult(Integer act);
    Result selectResult(Integer act);
}
