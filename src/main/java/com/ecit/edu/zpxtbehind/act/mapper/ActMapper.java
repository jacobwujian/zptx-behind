package com.ecit.edu.zpxtbehind.act.mapper;

import com.ecit.edu.zpxtbehind.act.bean.Act;

import java.util.List;

public interface ActMapper {
    Act selectAct(Act act);
    List<Act> selectActs(Act act);
    void insertAct(Act act);
    void updateAct(Act act);
    void updateActResultCount(Act act);
    void deleteAct(Act act);
    void deleteActByUser(Integer pk_user);
    List<Act> selectActsByExample(Act act);
    List<Act> selectAllActs(Act act);
}
