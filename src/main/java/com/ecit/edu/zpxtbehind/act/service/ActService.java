package com.ecit.edu.zpxtbehind.act.service;

import com.ecit.edu.zpxtbehind.act.bean.Act;
import com.ecit.edu.zpxtbehind.act.bean.ActScreen;
import com.ecit.edu.zpxtbehind.act.mapper.ActMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActService {
    @Resource
    ActMapper actMapper;
   public Act selectAct(Act act){
       return actMapper.selectAct(act);
   }
   public List<Act> selectActs(){
       return actMapper.selectActs();
   }
   public List<ActScreen> selectActScreens(Act act){
       return actMapper.selectActScreens(act);
   }
   public void insertAct(Act act){
       actMapper.insertAct(act);
   }
   public void insertActScreens(List<ActScreen> actScreens){
       actMapper.insertActScreens(actScreens);
   }
   public void updateAct(Act act){
       actMapper.updateAct(act);
   }
   public void deleteScreens(Act act){
       actMapper.deleteScreens(act);
   }
   public void deleteAct(Act act){
       actMapper.deleteAct(act);
   }
}
