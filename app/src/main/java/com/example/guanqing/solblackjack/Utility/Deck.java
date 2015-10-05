package com.example.guanqing.solblackjack.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Guanqing on 2015/10/5.
 */
public class Deck {
    private Queue<Card> deck;

    public Deck(){
        ArrayList<Card> deckList = new ArrayList<>();
        for (int i=1;i<14;i++){
            String[] suitList = new String[]{"S","C","H","D"};
            for (String s: suitList){
                deckList.add(new Card(i, s));
            }
        }
        Collections.shuffle(deckList);
        deck = new LinkedList<>(deckList);
    }

    public Card deal(){
        return deck.poll();
    }

    public boolean isEmpty(){
        return deck.isEmpty();
    }

    public Queue<Card> getDeck() {
        return deck;
    }

    @Override
    public boolean equals(Object o) {
        ArrayList<Card> deckList = new ArrayList<>(deck);
        ArrayList<Card> deckList2 = new ArrayList<>(((Deck) o).getDeck());
        if(deckList.equals(deckList2)){
            return true;
        }
        return false;
    }
}
