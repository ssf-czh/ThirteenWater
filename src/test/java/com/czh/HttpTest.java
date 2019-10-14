package com.czh;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpTest {

    private String token;


    @Test
    public void testGet() {
        try {

//            URL url = new URL(uri + "/test");
            URL url = new URL("d");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true); // 设置该连接是可以输出的
            connection.setRequestMethod("GET"); // 设置请求方式
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) { // 读取数据
                result.append(line + "\n");
            }
            connection.disconnect();

            System.out.println(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Post
     * 对登入接口的单元测试
     */
    @Test
    public void testLogin() {
        try {
//            URL url = new URL(uri + "/test2");
            URL url = new URL("https://api.shisanshui.rtxux.xyz/auth/login");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true); // 设置可输入
            connection.setDoOutput(true); // 设置该连接是可以输出的
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("username", "czh");
            data.put("password", "czh");
            PrintWriter pw = new PrintWriter(new BufferedOutputStream(connection.getOutputStream()));
            pw.write(objectMapper.writeValueAsString(data));
            pw.flush();
            pw.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) { // 读取数据
                result.append(line + "\n");
            }
            connection.disconnect();

//            System.out.println(result.toString());

            Gson gson = new Gson();
            Map<String, Object> map = new HashMap<String, Object>();
            map = gson.fromJson(result.toString(), map.getClass());

            token = (String) ((Map) map.get("data")).get("token");
            System.out.println(token);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Post
     * 对开启战局的单元测试
     */
    @Test
    public void testOpenBattle(){
        try {
//            URL url = new URL(uri + "/test2");
            token = "e105ee38-91dc-4d3e-8a39-e0a76e02dae1";
            URL url = new URL("https://api.shisanshui.rtxux.xyz/game/open");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true); // 设置可输入
            connection.setDoOutput(true); // 设置该连接是可以输出的
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            System.out.println(token);
            connection.setRequestProperty("X-Auth-Token", token);

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> data = new HashMap<String, Object>();
//            data.put("username", "czh");
//            data.put("password", "czh");
            PrintWriter pw = new PrintWriter(new BufferedOutputStream(connection.getOutputStream()));
            pw.write(objectMapper.writeValueAsString(data));
            pw.flush();
            pw.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) { // 读取数据
                result.append(line + "\n");
            }
            connection.disconnect();

            System.out.println(result.toString());

            Gson gson = new Gson();
            Map<String, Object> map = new HashMap<String, Object>();
            map = gson.fromJson(result.toString(), map.getClass());

            System.out.println(map);

            double id = (double) ((Map) map.get("data")).get("id");
            System.out.println((int)id);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Post
     * 进行出牌
     */
    /**
     * {
     *   "id": 1000,
     *   "card": [
     *     "*2 *3 *4",
     *     "*5 *6 *7 *8 *9",
     *     "*10 *J *Q *K *A"
     *   ]
     * }
     */
    @Test
    public void TestHandin(){
        try {
//            URL url = new URL(uri + "/test2");
            Integer id = 5096;
            token = "e105ee38-91dc-4d3e-8a39-e0a76e02dae1";
            URL url = new URL("https://api.shisanshui.rtxux.xyz/game/submit");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true); // 设置可输入
            connection.setDoOutput(true); // 设置该连接是可以输出的
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

            connection.setRequestProperty("X-Auth-Token", token);
            connection.setRequestProperty("Content-Type", "application/json");

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> data = new HashMap<String, Object>();
            List<String> card = new ArrayList<>();
            card.add("$4 $5 $7");
            card.add("#8 #J $A *2 *3");
            card.add("&5 &6 &7 &8 &K");

            data.put("id", id);
            data.put("card", card);
            PrintWriter pw = new PrintWriter(new BufferedOutputStream(connection.getOutputStream()));
            pw.write(objectMapper.writeValueAsString(data));
            pw.flush();
            pw.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) { // 读取数据
                result.append(line + "\n");
            }
            connection.disconnect();

            System.out.println(result.toString());

            Gson gson = new Gson();
            Map<String, Object> map = new HashMap<String, Object>();
            map = gson.fromJson(result.toString(), map.getClass());

            System.out.println(map);




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

