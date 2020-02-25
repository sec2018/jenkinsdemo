package com.example.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.pojo.User;

import java.util.HashMap;
import java.util.Map;

public class ReturnTemplate {
    public static JSONObject GiveTemp(Integer statusnum, User user){
        JSONObject map = new JSONObject();
        JSONObject userstring = new JSONObject();
        try {
            if(statusnum == 200){
                userstring.put("name",user.getName());
                userstring.put("pwd",user.getPassword());
                userstring.put("age",user.getAge().toString());
                map.put("data",userstring);
            }else{
                map.put("data",null);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }
}
