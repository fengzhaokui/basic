package com.fengzkframework.basic.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UncodeUtil {

    public static String getUTF8XMLString(String xml) {
        // A StringBuffer Object
        StringBuffer sb = new StringBuffer();
        sb.append(xml);
        String xmString = "";
        String xmlUTF8="";
        try {
            xmString = new String(sb.toString().getBytes("UTF-8"));
            xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");
            System.out.println("utf-8 编码：" + xmlUTF8) ;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // return to String Formed
        return xmlUTF8;
//
//        String strSource = "你想要转码的字符串";
//        String strSomeEncoding = "utf-8";   //例如utf-8
//        String strTarget = new String (strSource.getBytes(Charset.forName(strSomeEncoding)), strSomeEncoding);
    }
}
