package com.blackjacksquare.guanqing.solblackjack.Utility;

/**
 * Created by Guanqing on 2015/10/13.
 */
public class Score implements Comparable<Score>{
    int score;
    String info;

    public Score(int score, String info){
        this.score = score;
        this.info = info;
    }

    public int compareTo(Score sc){
        //return 0 if equal
        //1 if passed greater than this
        //-1 if this greater than passed
        return sc.score>score? 1 : sc.score<score? -1 : 0;
    }
}
