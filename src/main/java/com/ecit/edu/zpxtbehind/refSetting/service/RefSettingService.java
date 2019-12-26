package com.ecit.edu.zpxtbehind.refSetting.service;

import com.ecit.edu.zpxtbehind.refSetting.bean.RefTypeSetting;
import com.ecit.edu.zpxtbehind.refSetting.mapper.RefSettingMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RefSettingService {
    @Resource
    RefSettingMapper refSettingMapper;

            public List<RefTypeSetting> getAllType(){
               return refSettingMapper.selectAllRefType();
            }

}
