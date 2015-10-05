package com.example.guanqing.solblackjack.Utility;

import com.example.guanqing.solblackjack.Utility.Card;

/**
 * Created by Guanqing on 2015/10/5.
 */
public class Table {
    private Card[] row1;
    private Card[] row2;
    private Card[] row3;
    private Card[] row4;

    private Card[] column1;
    private Card[] column2;
    private Card[] column3;
    private Card[] column4;
    private Card[] column5;

    public Table(){
        row1 = new Card[5];
        row2 = new Card[5];
        row3 = new Card[3];
        row4 = new Card[3];

        column1 = new Card[]{row1[0], row2[0]};
        column2 = new Card[]{row1[1], row2[1], row3[0], row4[0]};
        column3 = new Card[]{row1[2], row2[2], row3[1], row4[1]};
        column4 = new Card[]{row1[3], row2[3], row3[2], row4[2]};
        column5 = new Card[]{row1[4], row2[4]};
    }

    private boolean isAce(Card[] row){
        for (Card card: row){
            if (card.getRank()==1){
                return true;
            }
        }
        return false;
    }

    /**
     * Scores a single row or column
     * @param row
     * @return score
     */
    private int scoreRow(Card[] row){
        int sum = 0;
        for (Card card: row){
            sum += card.getValue();
        }
        //score the Aces
        if (sum+10<22 && isAce(row)){
            sum+=10;
        }
        //return the score
        if (sum==21){
            if(row.length==2){
                return 10;
            }else{
                return 7;
            }
        }else if (sum<21 && sum > 16){
            return sum - 15;
        }else if (sum<=16){
            return 1;
        }
        return 0;
    }

    /**
     * get the total score of the table
     * @return score
     */
    public int scoreTable(){
        int score = scoreRow(row1)
                + scoreRow(row2)
                + scoreRow(row3)
                + scoreRow(row4)
                + scoreRow(column1)
                + scoreRow(column2)
                + scoreRow(column3)
                + scoreRow(column4)
                + scoreRow(column5);
        return score;
    }
}
