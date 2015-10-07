package com.example.guanqing.solblackjack.Utility;

import com.example.guanqing.solblackjack.R;

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

    private Card[] discards;

    public Table(){
        init();
    }

    //initialize the table
    //clear all the cards
    public void init(){
        row1 = new Card[5];
        row2 = new Card[5];
        row3 = new Card[3];
        row4 = new Card[3];

        column1 = new Card[]{row1[0], row2[0]};
        column2 = new Card[]{row1[1], row2[1], row3[0], row4[0]};
        column3 = new Card[]{row1[2], row2[2], row3[1], row4[1]};
        column4 = new Card[]{row1[3], row2[3], row3[2], row4[2]};
        column5 = new Card[]{row1[4], row2[4]};

        discards = new Card[4];
    }

    //return true if there is any Aces in a row/column
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
            if(card==null){
                return 0;
            }
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

    public void placeCard(int id, Card card){
        switch (id){
            case R.id.hand1:
                row1[0] = card;
                column1[0] = card;
                break;
            case R.id.hand2:
                row1[1] = card;
                column2[0] = card;
                break;
            case R.id.hand3:
                row1[2] = card;
                column3[0] = card;
                break;
            case R.id.hand4:
                row1[3] = card;
                column4[0] = card;
                break;
            case R.id.hand5:
                row1[4] = card;
                column5[0] = card;
                break;
            case R.id.hand6:
                row2[0] = card;
                column1[1] = card;
                break;
            case R.id.hand7:
                row2[1] = card;
                column2[1] = card;
                break;
            case R.id.hand8:
                row2[2] = card;
                column3[1] = card;
                break;
            case R.id.hand9:
                row2[3] = card;
                column4[1] = card;
                break;
            case R.id.hand10:
                row2[4] = card;
                column5[1] = card;
                break;
            case R.id.hand11:
                row3[0] = card;
                column2[2] = card;
                break;
            case R.id.hand12:
                row3[1] = card;
                column3[2] = card;
                break;
            case R.id.hand13:
                row3[2] = card;
                column4[2] = card;
                break;
            case R.id.hand14:
                row4[0] = card;
                column2[3] = card;
                break;
            case R.id.hand15:
                row4[1] = card;
                column3[3] = card;
                break;
            case R.id.hand16:
                row4[2] = card;
                column4[3] = card;
                break;

            case R.id.discard1:
                discards[0] = card;
                break;
            case R.id.discard2:
                discards[1] = card;
                break;
            case R.id.discard3:
                discards[2] = card;
                break;
            case R.id.discard4:
                discards[3] = card;
                break;
            default:
                break;
        }
    }

    private boolean rowIsFull(Card[] row){
        for (Card c: row){
            if (c==null){
                return false;
            }
        }
        return true;
    }

    public boolean isFull(){
        boolean b = rowIsFull(row1) && rowIsFull(row2)
                && rowIsFull(row3) && rowIsFull(row4);
        return b;
    }

    @Override
    public String toString() {
        String s = "table\n[";
        for (Card c: row1){
            s += c + " ";
        }
        s+= "]\n[";
        for (Card c: row2){
            s += c + " ";
        }
        s+= "]\n[";
        for (Card c: row3){
            s += c + " ";
        }
        s+= "]\n[";
        for (Card c: row4){
            s += c + " ";
        }
        s+= "]\ndiscards: [";
        for (Card c: discards){
            s += c + " ";
        }
        s+= "]\n";
        return s;
    }
}
