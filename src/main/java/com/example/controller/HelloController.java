package com.example.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.pojo.User;
import com.example.util.ReturnTemplate;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController{


    @RequestMapping("/hello")
    public String helloqqq() {

//        JSONObject object = new JSONObject();
//        JSONObject childObj = new JSONObject();

        try {
//            childObj.put("NO1","大众");
//            childObj.put("NO2","奔驰");
//            object.put("name", "王小二");
//            object.put("age", 25.2);
//            object.put("birthday", "1990-01-01");
//            object.put("school", "蓝翔");
//            object.put("major", new String[] {"理发", "挖掘机"});
//            object.put("has_girlfriend", false);
//            object.put("car", childObj);
//            object.put("comment", "这是一个注释");
            User user = new User();
            user.setName("wang");
            user.setPassword("123");
            user.setAge(12);


            JSONObject map = ReturnTemplate.GiveTemp(200,user);

            return map.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String GetDog(){
        return "test";
    }


    @RequestMapping(value="/api/stars/api/login",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String login(HttpServletRequest request){
        JSONObject object = new JSONObject();
        try {
            //获取到JSONObject
            JSONObject jsonParam = this.getJSONParam(request);


            object.put("name", "qqq");
            object.put("data", jsonParam);
            return object.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public JSONObject getJSONParam(HttpServletRequest request){
        JSONObject jsonParam = null;
        try {
            // 获取输入流
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

            // 写入数据到Stringbuilder
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = streamReader.readLine()) != null) {
                sb.append(line);
            }
            jsonParam = JSONObject.parseObject(sb.toString());
            // 直接将json信息打印出来
            System.out.println(jsonParam.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonParam;
    }

}
