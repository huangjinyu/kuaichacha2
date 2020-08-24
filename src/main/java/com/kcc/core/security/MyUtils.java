package com.kcc.core.security;

import javax.servlet.http.HttpServletRequest;

public class MyUtils {
    /**
     * 判断是否是Ajax请求
     *
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
//        Enumeration<String> names = request.getHeaderNames();
//        while (names.hasMoreElements()) {
//            System.out.println(names.nextElement());
//        }

        return (request.getHeader("X-Requested-With") != null &&
                "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With").toString()));
    }

//    public static isAjax(HttpServletRequest request) {
//        String requestedWith = request.getHeader("X-Requested-With");
//        if (request.getHeader("X-Requested-With") != null
//                && request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
//            out.print("该请求是 AJAX 异步HTTP请求。");
//        } else {
//            out.print("该请求是传统的 同步HTTP请求。");
//        }
//    }

//    public static boolean isAjaxRequest(HttpServletRequest request) {
//        String requestType = request.getHeader("X-Requested-With");
//        //如果requestType能拿到值，并且值为 XMLHttpRequest ,表示客户端的请求为异步请求，那自然是ajax请求了，反之如果为null,则是普通的请求
//        if (requestType == null) {
//            return false;
//        }
//        return true;
//
//    }

//    public static boolean isAjaxRequest(HttpServletRequest request) {
//        String requestedWith = request.getHeader("x-requested-with");
//        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}
