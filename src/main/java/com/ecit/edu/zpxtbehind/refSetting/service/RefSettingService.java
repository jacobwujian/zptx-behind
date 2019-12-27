package com.ecit.edu.zpxtbehind.refSetting.service;

import com.ecit.edu.zpxtbehind.refSetting.bean.RefTypeSetting;
import com.ecit.edu.zpxtbehind.refSetting.mapper.RefSettingMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RefSettingService {
    @Resource
    RefSettingMapper refSettingMapper;

    public List<RefTypeSetting> getAllType() {
        return refSettingMapper.selectAllRefType();
    }

    public RefTypeSetting getType(RefTypeSetting refTypeSetting) {
        return refSettingMapper.selectRefTypeByPk_type(refTypeSetting);
    }

    public List<RefTypeSetting> selectAllRefTypeByParent(RefTypeSetting refTypeSetting) {
        return refSettingMapper.selectAllRefTypeByParent(refTypeSetting);
    }

    public RefTypeSetting insertType(RefTypeSetting refTypeSetting) {
        refSettingMapper.insertType(refTypeSetting);
        Integer pk_type = refTypeSetting.getPk_type();
        while (refTypeSetting.getParent() != null){
            refTypeSetting.setPk_type(refTypeSetting.getParent());
            refTypeSetting = refSettingMapper.selectRefTypeByPk_type(refTypeSetting);
            ArrayList arrayList = refTypeSetting.getChildrenArry();
            if (arrayList == null){
                arrayList = new ArrayList();
            }
            arrayList.add(pk_type);
            refTypeSetting.setChildrenArry(arrayList);
            refSettingMapper.resetChildren(refTypeSetting);
        }
        refTypeSetting.setPk_type(pk_type);
        return refSettingMapper.selectRefTypeByPk_type(refTypeSetting);
    }

    public void deleteType(RefTypeSetting refTypeSetting) {
        refTypeSetting = refSettingMapper.selectRefTypeByPk_type(refTypeSetting);
        refSettingMapper.deleteType(refTypeSetting);
        Integer pk_type = refTypeSetting.getPk_type();
        while (refTypeSetting.getParent() != null) {
            refTypeSetting.setPk_type(refTypeSetting.getParent());
            refTypeSetting = refSettingMapper.selectRefTypeByPk_type(refTypeSetting);
            ArrayList arrayList = refTypeSetting.getChildrenArry();
            arrayList.remove(String.valueOf(pk_type));
            refTypeSetting.setChildrenArry(arrayList);
            refSettingMapper.resetChildren(refTypeSetting);
        }
    }
}
