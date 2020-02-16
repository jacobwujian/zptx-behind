package com.ecit.edu.zpxtbehind;

import javax.servlet.http.HttpServletRequest;

public class HeaderParamUtil {

    public static Integer getPKUser(HttpServletRequest request) {
        String param = request.getHeader("pk-user");
        if (param==null || "".equals(param)) {
            System.out.println("用户主键：" + param);
            return -1;
        }
        return Integer.parseInt(request.getHeader("pk-user"));
    }

    public static String getXToken(HttpServletRequest request) {
        return request.getHeader("X-Token");
    }
}
