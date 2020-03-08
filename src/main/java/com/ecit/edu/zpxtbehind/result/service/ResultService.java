package com.ecit.edu.zpxtbehind.result.service;


import com.ecit.edu.zpxtbehind.result.bean.Result;
import com.ecit.edu.zpxtbehind.result.mapper.ResultMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ResultService {
    @Resource
    ResultMapper resultMapper;

   public void insertResult(Result result){
       resultMapper.insertResult(result);
   }
   public void updateResult(Result result){
       resultMapper.updateResult(result);

   }
   public void deleteResult(Integer act){
    resultMapper.deleteResult(act);
   }
   public Result selectResult(Integer act){
       return resultMapper.selectResult(act);
   }
}
