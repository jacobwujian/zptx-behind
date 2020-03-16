package com.ecit.edu.zpxtbehind.act.service;

import com.ecit.edu.zpxtbehind.act.bean.Act;
import com.ecit.edu.zpxtbehind.act.mapper.ActMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActService {
    @Resource
    ActMapper actMapper;

    public Act selectAct(Act act) {
        return actMapper.selectAct(act);
    }

    public List<Act> selectActs(Integer pk_user) {
        return actMapper.selectActs(pk_user);
    }

    public List<Act> selectAllActs(Act act) {
        return actMapper.selectAllActs(act);
    }

    public void insertAct(Act act) {
        actMapper.insertAct(act);
    }

    public void updateActResultCount(Act act) {
        actMapper.updateActResultCount(act);
    }

    public void updateAct(Act act) {
        actMapper.updateAct(act);
    }

    public void deleteAct(Act act) {
        actMapper.deleteAct(act);
    }

    public List<Act> selectActsByExample(Act act) {
        return actMapper.selectActsByExample(act);
    }
}
