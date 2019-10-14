package com.czh.shisanshui.controller;


import com.czh.pojo.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GetHanderCardController {

    @RequestMapping(value = "/getCards",method = {RequestMethod.POST})
    public Resp getCards(Rqst rqst){

        String s = rqst.getCard();
        System.out.println(rqst);
        //        s = "*2 *3 *4 #5 *6 *7 #8 #9 *10 *J *Q *K *A";
        //        s = "&2 $2 $3 &4 &6 #6 *A &K $K #4 *4 $A &A";

        System.out.println(s);

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

    @RequestMapping("/test")
    public Rqst get(){
        Rqst rqst = new Rqst();
        rqst.setId(666);
        rqst.setStatus("ok");
        rqst.setCard("12345");
        return rqst;
    }
}
