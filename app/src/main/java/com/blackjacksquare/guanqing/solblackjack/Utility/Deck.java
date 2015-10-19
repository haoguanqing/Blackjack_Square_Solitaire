package com.blackjacksquare.guanqing.solblackjack.Utility;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Guanqing on 2015/10/5.
 * Modified into parcelable on 2015/10/11
 */
public class Deck implements Parcelable {
    private Queue<Card> deck;
    private ArrayList<Card> deckList;

    public Deck() {
        deckList = new ArrayList<>();
        for (int i = 1; i < 14; i++) {
            String[] suitList = new String[]{"S", "C", "H", "D"};
            for (String s : suitList) {
                deckList.add(new Card(i, s));
            }
        }
        Collections.shuffle(deckList);
        deck = new LinkedList<>(deckList);
    }

    public Card deal() {
        return deck.poll();
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }

    public Queue<Card> getDeck() {
        return deck;
    }

    @Override
    public boolean equals(Object o) {
        ArrayList<Card> deckList = new ArrayList<>(deck);
        ArrayList<Card> deckList2 = new ArrayList<>(((Deck) o).getDeck());
        if (deckList.equals(deckList2)) {
            return true;
        }
        return false;
    }


    //-----------------parcelable methods-----------------------
    protected Deck(Parcel in) {
        deckList = in.readArrayList(Card.class.getClassLoader());
        deck = new LinkedList<>(deckList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(deckList);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Deck> CREATOR = new Parcelable.Creator<Deck>() {
        @Override
        public Deck createFromParcel(Parcel in) {
            return new Deck(in);
        }

        @Override
        public Deck[] newArray(int size) {
            return new Deck[size];
        }
    };
}
