package com.ecit.edu.zpxtbehind.refSetting.mapper;

import com.ecit.edu.zpxtbehind.refSetting.bean.RefTypeSetting;

import java.util.List;

public interface RefSettingMapper {
    List<RefTypeSetting> selectAllRefType();
    RefTypeSetting selectRefTypeByPk_type(RefTypeSetting refTypeSetting);
    List<RefTypeSetting> selectAllRefTypeByParent(RefTypeSetting refTypeSetting);
    void insertType(RefTypeSetting refTypeSetting);
    void resetChildren(RefTypeSetting refTypeSetting);
    void deleteType(RefTypeSetting refTypeSetting);
}
