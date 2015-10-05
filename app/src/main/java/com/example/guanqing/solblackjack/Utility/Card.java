package com.example.guanqing.solblackjack.Utility;

/**
 * Created by Guanqing on 2015/10/5.
 */
public class Card {
    private int rank;
    private String suit;
    public Card(int rank, String suit){
        this.rank = rank;
        this.suit = suit.toUpperCase();
    }

    public int getRank() {
        return rank;
    }

    public int getValue(){
        if (rank>10){
            return 10;
        }
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return rank + suit;
    }

    @Override
    public boolean equals(Object o) {
        Card card2 = (Card) o;
        if (card2.getRank()==rank && card2.getSuit().equals(suit)){
            return true;
        }
        return false;
    }
}
