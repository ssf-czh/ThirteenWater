package com.czh.shisanshui.controller;


import com.czh.pojo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
public class GetHanderCardController {

//    private static String token;
//    private static int id;
//    private static String cards;
    private static Boolean isBattle=false;



    @RequestMapping(value = "/getCards",method = {RequestMethod.POST})
    public Resp getCards(Rqst rqst){


        String s = rqst.getCard();
//        System.out.println(rqst);
        //        s = "*2 *3 *4 #5 *6 *7 #8 #9 *10 *J *Q *K *A";
        //        s = "&2 $2 $3 &4 &6 #6 *A &K $K #4 *4 $A &A";

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

        Resp resp = new Resp();
        resp.setId(rqst.getId());

        ArrayList<String> card = new ArrayList<String>();
        card.add(shang);
        card.add(zhong);
        card.add(xia);
        resp.setCard(card);

        return resp;
    }


    @RequestMapping(value = "/openAutoBattle1")
    public String openAutoBattle(){

//        if(isBattle)return "已经开启自动出牌";
        isBattle = true;
        int count = 0;
        while(isBattle){
            System.out.println("=============czh=============="+(++count));
            try {
                String token = login("czh","czh");
                List<Object> list = openBattle(token);
                int id = (int)list.get(0);
                String cards = (String) list.get(1);
                handin(token,id,cards);
            } catch (Exception e) {
                System.out.println(e);
            }

        }
        return "自动出牌停止";
    }

    @RequestMapping(value = "/openAutoBattle2")
    public String openAutoBattle1(){

//        if(isBattle)return "已经开启自动出牌";
        isBattle = true;
        int count = 0;
        while(isBattle){
            System.out.println("=============po1=============="+(++count));
            try {
                String token = login("po1","123");
                List<Object> list = openBattle(token);
                int id = (int)list.get(0);
                String cards = (String) list.get(1);
                handin(token,id,cards);
            } catch (Exception e) {
                System.out.println(e);
            }

        }
        return "自动出牌停止";
    }

    @RequestMapping(value = "/openAutoBattle3")
    public String openAutoBattle2(){

//        if(isBattle)return "已经开启自动出牌";
        isBattle = true;
        int count = 0;
        while(isBattle){
            System.out.println("=============po2=============="+(++count));
            try {
                String token = login("po2","123");
                List<Object> list = openBattle(token);
                int id = (int)list.get(0);
                String cards = (String) list.get(1);
                handin(token,id,cards);
            } catch (Exception e) {
                System.out.println(e);
            }

        }
        return "自动出牌停止";
    }

    @RequestMapping(value = "/openAutoBattle4")
    public String openAutoBattle3(){

        if(isBattle)return "已经开启自动出牌";
        isBattle = true;
        int count = 0;
        while(isBattle){
            System.out.println("=============po3=============="+(++count));
            try {
                String token = login("po3","123");
                List<Object> list = openBattle(token);
                int id = (int)list.get(0);
                String cards = (String) list.get(1);
                handin(token,id,cards);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return "自动出牌停止";
    }
    @RequestMapping(value = "/closeAutoBattle")
    public String closeAutoBattle(){
        isBattle = false;
        return "成功关闭自动出牌";
    }
    @RequestMapping("/test")
    public Rqst get(){
        Rqst rqst = new Rqst();
        rqst.setId(666);
        rqst.setStatus("ok");
        rqst.setCard("12345");
        return rqst;
    }

    public static String login(String username, String password) {
        String token =null;
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
            System.out.println(e);
        }
        return token;
    }

    public static List<Object> openBattle(String token){
        List<Object> list = new ArrayList<>();
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

//            System.out.println(result.toString());

            Gson gson = new Gson();
            Map<String, Object> map = new HashMap<String, Object>();
            map = gson.fromJson(result.toString(), map.getClass());

            System.out.println(map);

            double temp = (double) ((Map) map.get("data")).get("id");
            list.add((int) temp);

            list.add((String) ((Map) map.get("data")).get("card"));


        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public static void handin(String token, int id, String cards){
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

            List<String> card = zuPai(cards);
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
            System.out.println(e);
        }
    }

    public static List<String> zuPai(String cards){
        try {
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
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
