package com.example.guanqing.solblackjack.Utility;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Guanqing on 2015/10/5.
 * Modified into parcelable on 2015/10/11
 */
public class Card implements Parcelable {
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

    //-----------------parcelable methods-----------------------
    protected Card(Parcel in) {
        rank = in.readInt();
        suit = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(rank);
        dest.writeString(suit);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Card> CREATOR = new Parcelable.Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };
}
