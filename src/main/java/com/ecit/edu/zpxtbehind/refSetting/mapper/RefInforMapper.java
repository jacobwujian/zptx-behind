package com.ecit.edu.zpxtbehind.refSetting.mapper;

import com.ecit.edu.zpxtbehind.refSetting.bean.RefSetting;
import com.ecit.edu.zpxtbehind.refSetting.bean.RefTypeSetting;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RefInforMapper {
    List<RefSetting> selectInformationByExample(@Param("name")String name,@Param("arr")String arr);
   void insertRef(RefSetting refSetting);
   void deleteRef(RefSetting refSetting);
   void resetName(RefSetting refSetting);
   void deleteRefByType(RefTypeSetting refSetting);
}
