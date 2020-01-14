package com.ecit.edu.zpxtbehind.act.mapper;

import com.ecit.edu.zpxtbehind.act.bean.Act;
import com.ecit.edu.zpxtbehind.act.bean.ActScreen;

import java.util.List;

public interface ActMapper {
    Act selectAct(Act act);
    List<Act> selectActs();
    List<ActScreen> selectActScreens(Act act);
    void insertAct(Act act);
    void insertActScreens(List<ActScreen> actScreens);
    void updateAct(Act act);
    void deleteScreens(Act act);
    void deleteAct(Act act);
}
