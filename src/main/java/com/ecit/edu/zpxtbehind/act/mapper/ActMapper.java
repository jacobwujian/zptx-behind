package com.ecit.edu.zpxtbehind.act.mapper;

import com.ecit.edu.zpxtbehind.act.bean.Act;

import java.util.List;

public interface ActMapper {
    Act selectAct(Act act);
    List<Act> selectActs(Integer pk_user);
    void insertAct(Act act);
    void updateAct(Act act);
    void deleteAct(Act act);
    List<Act> selectActsByExample(Act act);
    List<Act> selectAllActs();
}
