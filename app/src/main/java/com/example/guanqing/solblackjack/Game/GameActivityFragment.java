package com.example.guanqing.solblackjack.Game;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guanqing.solblackjack.R;
import com.example.guanqing.solblackjack.Utility.Deck;
import com.example.guanqing.solblackjack.Utility.Table;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class GameActivityFragment extends Fragment {
    private ViewHolder holder;
    private Deck deck;
    private Table table;

    public GameActivityFragment() {
        deck = new Deck();
        table = new Table();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);
        holder = new ViewHolder(rootView);
        holder.initiate();
        
        return rootView;
    }




    /**
     * custom view holder class
     */
    static class ViewHolder {
        @Bind(R.id.score_textView) TextView scoreTextView;

        @Bind(R.id.deal_imageView) ImageView deal;
        @Bind(R.id.discard1) ImageView discard1;
        @Bind(R.id.discard2) ImageView discard2;
        @Bind(R.id.discard3) ImageView discard3;
        @Bind(R.id.discard4) ImageView discard4;
        @Bind(R.id.hand1) ImageView table1;
        @Bind(R.id.hand2) ImageView table2;
        @Bind(R.id.hand3) ImageView table3;
        @Bind(R.id.hand4) ImageView table4;
        @Bind(R.id.hand5) ImageView table5;
        @Bind(R.id.hand6) ImageView table6;
        @Bind(R.id.hand7) ImageView table7;
        @Bind(R.id.hand8) ImageView table8;
        @Bind(R.id.hand9) ImageView table9;
        @Bind(R.id.hand10) ImageView table10;
        @Bind(R.id.hand11) ImageView table11;
        @Bind(R.id.hand12) ImageView table12;
        @Bind(R.id.hand13) ImageView table13;
        @Bind(R.id.hand14) ImageView table14;
        @Bind(R.id.hand15) ImageView table15;
        @Bind(R.id.hand16) ImageView table16;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void initiate(){
            deal.setImageResource(R.drawable.ce);
            discard1.setImageResource(R.drawable.ce);
            discard2.setImageResource(R.drawable.ce);
            discard3.setImageResource(R.drawable.ce);
            discard4.setImageResource(R.drawable.ce);
            table1.setImageResource(R.drawable.ce);
            table2.setImageResource(R.drawable.ce);
            table3.setImageResource(R.drawable.ce);
            table4.setImageResource(R.drawable.ce);
            table5.setImageResource(R.drawable.ce);
            table6.setImageResource(R.drawable.ce);
            table7.setImageResource(R.drawable.ce);
            table8.setImageResource(R.drawable.ce);
            table9.setImageResource(R.drawable.ce);
            table10.setImageResource(R.drawable.ce);
            table11.setImageResource(R.drawable.ce);
            table12.setImageResource(R.drawable.ce);
            table13.setImageResource(R.drawable.ce);
            table14.setImageResource(R.drawable.ce);
            table15.setImageResource(R.drawable.ce);
            table16.setImageResource(R.drawable.ce);
        }
    }
}
