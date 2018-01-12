package com.fengzkframework.basic.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	
	/**
	 * 将java对象转化为json
	 * @param object 要转化的Java对象
	 * @throws IOException 
	 */
	public static String object2Json(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		StringWriter sw = new StringWriter();
		JsonGenerator jsonGenerator = new JsonFactory().createGenerator(sw);
		mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.writeValue(jsonGenerator, object);
		jsonGenerator.close();
		return sw.toString();
	}

    /**
     * 将json格式的字符串解析成Map对象 <li>
     * json格式：{"name":"admin","retries":"3fff","testname"
     * :"ddd","testretries":"fffffffff"}
     */
    public static String hashMap2Json(Map<String, String> map)
    {
        StringBuffer resultString=new StringBuffer();
        resultString.append("{");
        int i=0;
        for(String key:map.keySet()){
            resultString.append("\""+key+"\":\""+map.get(key)+"\"");
            i++;
            if(i<map.keySet().size()){
                resultString.append(",");
            }
        }
        resultString.append("}");
        return resultString.toString();
    }

    @SuppressWarnings("unchecked")
    public static Map<String,Object> json2Map(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true) ;
        return mapper.readValue(json, Map.class);
    }
}
