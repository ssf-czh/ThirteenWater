package com.czh;

import com.czh.pojo.Card;
import com.czh.pojo.Player;
import com.czh.pojo.Resp;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmTest {

    public static void main(String []args)
    {
        // 花色1-4代表四种花色（♥，♣，♦，♠）& * # $
        // Rank 1-13表示牌的大小（2，3，4……，K,  A）


//        #2 #4 $10 *3 $3 #5 *5 #A *8 &9 $A &7 *Q
//        String s = "$9 $2 #7 *A *10 $Q $3 &J #4 #10 &4 *6 #Q";




        List<Card> handCard = new ArrayList<Card>();
        handCard.add(new Card(1,3));
        handCard.add(new Card(3,3));
        handCard.add(new Card(9,4));
        handCard.add(new Card(2,2));
        handCard.add(new Card(2,4));
//        handCard.add(new Card(4,3));
//        ---
        handCard.add(new Card(7,2));
//        ---
        handCard.add(new Card(4,2));
        handCard.add(new Card(13,3));
        handCard.add(new Card(7,2));
        handCard.add(new Card(8,1));
        handCard.add(new Card(13,4));
        handCard.add(new Card(6,1));
        handCard.add(new Card(11,2));
//        List<Card> handCard = new ArrayList<Card>();
//        handCard.add(new Card(8,3));
//        handCard.add(new Card(7,2));
//        handCard.add(new Card(8,2));
//        handCard.add(new Card(7,1));
//        handCard.add(new Card(7,3));
//        handCard.add(new Card(8,1));
//        handCard.add(new Card(5,2));
//        handCard.add(new Card(6,2));
//        handCard.add(new Card(13,3));
//        handCard.add(new Card(11,2));
//        handCard.add(new Card(9,3));
//        handCard.add(new Card(4,2));
//        handCard.add(new Card(9,2));
////        ----------
//        handCard.add(new Card(4,3));
//        handCard.add(new Card(4,2));

        Player p=new Player();
        System.out.println(p.toString(handCard));

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
        System.out.println(res);



    }
    @Test
    public void TestShiSanShui(){

//        $8 &7
//          *10 #10
//        *5 #5
//        error
//        String s = "#2 #4 $10 *3 $3 #5 *5 #A *8 &9 $A &7 *Q";
            String s = "$9 $2 #7 *A *10 $Q $3 &J #4 #10 &4 *6 #Q";
//            s = "$3 *A &J $8 &7 *3 &10 $6 *2 #8 #J *Q #4";

//            collect
//        String s = "*A #2 #9 *2 *7 $7 *4 &10 &8 *10 #10 &4 *8";
//        s = "$9 $8 *K $Q *7 #J *6 &7 $5 $3 &4 $4 *5";
//        s = "*A #2 #9 *2 *7 $7 *4 &10 &8 *10 #10 &4 *8";
//        s = "#9 #8 &2 $6 #Q $7 *5 *Q $A #4 &4 *A $2";
//        s = "&8 *Q &A *2 &K $K #3 $5 *K &Q $J &5 *9";
//            s = "$2 &6 &5 &2 *9 #4 #K $K $9 $10 &J #7 #3";

//          String s = "*2 *3 *4 #5 *6 *7 #8 #9 *10 *J *Q *K *A";
//        String s = "&2 $2 $3 &4 &6 #6 *A &K $K #4 *4 $A &A";
//        s =  "*K $K *A #10 &7 *6 *9 #A $3 &A #K #8 *2";
//       1  s = "&6 $5 &3 #J $K #9 $7 $9 &4 *2 $6 &A #K";
//      1   s = "*5 $A #7 &A $3 &7 &2 &4 $9 $4 #4 $6 *3";
//     1    s = "$5 *A $Q #K $2 *3 &9 $10 #J #Q &7 #3 $3";
//        s = "*7 &10 &8 $7 #3 *Q #5 &6 *4 #4 &5 &3 $K";

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
//            System.out.println(t+": "+type);
            handCard.add(new Card(t,type));
//            System.out.println(t+"-"+type);
        }
        System.out.println(handCard.size());

        Player p=new Player();
        p.change(handCard);
        System.out.println(p.choice.head.size());
        System.out.println(p.choice.mid.size());
        System.out.println(p.choice.end.size());
        ArrayList<Card> newHandCard = new ArrayList<>();

        System.out.println();

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

        String temp = res.replace("♥","&").replace("♣","*").replace("♦","#").replace("♠","$");

        System.out.println(temp);


    }
}
