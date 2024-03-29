package com.czh;

import com.czh.pojo.Card;
import com.czh.pojo.Player;
import com.czh.pojo.Resp;
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
    private int id;
    private String cards;

    @Test
    public void TestAll(){
        System.out.println("=============================");

        testLogin("po1","123");
        testOpenBattle(token);
        testHandin(token,id);
        System.out.println("=============================");
        testLogin("po2","123");
        testOpenBattle(token);
        testHandin(token,id);
        System.out.println("=============================");
        testLogin("po3","123");
        testOpenBattle(token);
        testHandin(token,id);
        System.out.println("=============================");
        testLogin("test8","test8");
        testOpenBattle(token);
        testHandin(token,id);
//        for (int i = 0; i <20 ; i++) {
//            System.out.println("=============czh==============");
//            try {
//                testLogin("czh","czh");
//                testOpenBattle(token);
//                testHandin(token,id);
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
        System.out.println("=============================");
        testLogin("po4","123");
        testOpenBattle(token);
        testHandin(token,id);
        System.out.println("=============================");
        testLogin("po5","123");
        testOpenBattle(token);
        testHandin(token,id);
        System.out.println("=============================");
        testLogin("test","test");
        testOpenBattle(token);
        testHandin(token,id);

    }

//    @Test
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
//    @Test
//    public void testLogin() {
    public void testLogin(String username,String password) {
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
//            data.put("username", "test");
//            data.put("password", "test");
            data.put("username", username);
            data.put("password", password);
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
//            System.out.println(map);

            token = (String) ((Map) map.get("data")).get("token");
//            System.out.println("token:" +token);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Post
     * 对开启战局的单元测试
     */
//    @Test
//    public void testOpenBattle(){
    public void testOpenBattle(String token){
        try {
//            URL url = new URL(uri + "/test2");
//            token = "5e6883ab-5d54-43de-9344-960ecc3311e7";
            URL url = new URL("https://api.shisanshui.rtxux.xyz/game/open");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoInput(true); // 设置可输入
            connection.setDoOutput(true); // 设置该连接是可以输出的
            connection.setRequestMethod("POST"); // 设置请求方式
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
//            System.out.println(token);
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

//            System.out.println(map);

            double temp = (double) ((Map) map.get("data")).get("id");
            id = (int) temp;
            System.out.println(id);

            cards = (String) ((Map) map.get("data")).get("card");


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
//    @Test
//    public void testHandin(){
    public void testHandin(String token, int id){
        try {
//            URL url = new URL(uri + "/test2");
//            Integer id = 12877;
//            token = "5e6883ab-5d54-43de-9344-960ecc3311e7";
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

            List<String> card = zuPai();
//            List<String> card = new ArrayList<>();
//
//            card.add("$J $6 $10");
//            card.add("*Q #5 &9 *J &6");
//            card.add("#A &A $K &Q *2");
            System.out.println(card);
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

    public List<String> zuPai(){
        String s = cards;
//        System.out.println(s);

        List<Card> handCard = new ArrayList<Card>();


        String[] s1 = s.split(" ");

        // 花色1-4代表四种花色（♥，♣，♦，♠）
        // Rank 1-13表示牌的大小（2，3，4……，K,  A）

        for (String s2 : s1) {
            int type;
            int t;
            char ch1 = s2.charAt(0);
            char ch2 = s2.charAt(1);
            if('$'==ch1)type=4;
            else if('&'==ch1)type=1;
            else if('*'==ch1)type=2;
            else type=3;
            if('2'<=ch2 && ch2<='9')
            {
                t = ch2 - '0' -1;
            }else if(ch2 == 'A') t = 13;
            else if(ch2 == 'K') t = 12;
            else if(ch2 == 'Q') t = 11;
            else if(ch2 == 'J') t= 10;
            else t = 9;
            handCard.add(new Card(t,type));
//            System.out.println(t+"-"+type);
        }

        Player p=new Player();
        p.change(handCard);
        ArrayList<Card> newHandCard = new ArrayList<>();

        for (Card card : p.choice.head) {
//            System.out.println(card);
            newHandCard.add(card);
        }

        for (Card card : p.choice.mid) {
//            System.out.println(card);
            newHandCard.add(card);
        }

        for (Card card : p.choice.end) {
//            System.out.println(card);
            newHandCard.add(card);
        }

        String res = p.toString(newHandCard);

//        System.out.println(res);
        // 花色1-4代表四种花色（♥，♣，♦，♠）
        String temp = res.replace("♥","&").replace("♣","*").replace("♦","#").replace("♠","$");


        String[] s2 = temp.split(" ");

        String shang = "";
        for (int i = 0; i <3 ; i++) {
            shang += s2[i];
            if(i!=2)shang+=" ";
        }
        String zhong = "";
        for (int i = 3; i <8 ; i++) {
            zhong += s2[i];
            if(i!=7)zhong+=" ";
        }
        String xia = "";
        for (int i = 8; i <13 ; i++) {
            xia += s2[i];
            if(i!=12)xia+=" ";
        }


        ArrayList<String> card = new ArrayList<String>();
        card.add(shang);
        card.add(zhong);
        card.add(xia);
        return card;
    }

}

