package com.example.guanqing.solblackjack.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guanqing.solblackjack.R;
import com.example.guanqing.solblackjack.Utility.Card;
import com.example.guanqing.solblackjack.Utility.Deck;
import com.example.guanqing.solblackjack.Utility.Table;
import com.example.guanqing.solblackjack.Utility.Utilities;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class GameActivityFragment extends Fragment {
    private static final String IMAGEVIEW_TAG = "TABLE_TAG";
    private static final String IMAGEVIEW_OCCUPIED_TAG = "OCCUPIED_TAG";
    private ViewHolder holder;
    private static Deck deck;
    private static Table table;
    private Card dealCard;
    private Drawable dealCardDrawable;

    public GameActivityFragment() {
        deck = new Deck();
        table = new Table();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);
        holder = new ViewHolder(rootView);
        holder.dealCard();
        return rootView;
    }

    private final class MyDragListener implements View.OnDragListener {
        Drawable emptyCardDrawable = getResources().getDrawable(R.drawable.ce);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            ImageView imageView = (ImageView) v.findViewWithTag(IMAGEVIEW_TAG);
            if(imageView==null){
                imageView = (ImageView) v.findViewWithTag(IMAGEVIEW_OCCUPIED_TAG);
            }
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    imageView.setColorFilter(Color.parseColor("#8C8CA240"));
                    imageView.invalidate();
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    imageView.clearColorFilter();
                    imageView.invalidate();
                    break;
                case DragEvent.ACTION_DROP:
                    if (imageView.getTag().equals(IMAGEVIEW_OCCUPIED_TAG)){
                        imageView.clearColorFilter();
                        imageView.invalidate();
                        return false;
                    }else{
                        imageView.setImageDrawable(dealCardDrawable);
                        imageView.clearColorFilter();
                        imageView.invalidate();
                        imageView.setTag(IMAGEVIEW_OCCUPIED_TAG);
                        holder.dealCard();
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                //ClipData data = ClipData.newPlainText("", "");
                view.startDrag(null, new MyDragShadowBuilder(view), view, 0);
                //view.setVisibility(View.INVISIBLE);
                return true;
            } else if(motionEvent.getAction() == MotionEvent.ACTION_BUTTON_RELEASE){
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    //custom drag shadow builder
    private class MyDragShadowBuilder extends View.DragShadowBuilder {

        // The drag shadow image, defined as a drawable thing
        private Drawable shadow;

        // Defines the constructor for myDragShadowBuilder
        public MyDragShadowBuilder(View v) {
            super(v);
            shadow = getActivity().getResources().getDrawable(Utilities.getCardDrawableResId(dealCard));
        }

        @Override
        public void onProvideShadowMetrics (Point size, Point touch){
            int width, height;
            width = getView().getWidth();
            height = getView().getHeight();
            shadow.setBounds(0, 0, width, height);
            size.set(width, height);
            touch.set(width / 2, height / 2);
        }

        // Defines a callback that draws the drag shadow in a Canvas that the system constructs
        // from the dimensions passed in onProvideShadowMetrics().
        @Override
        public void onDrawShadow(Canvas canvas) {
            shadow.draw(canvas);
        }
    }

    //custom view holder class
    class ViewHolder {
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

        ImageView[] viewsList;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
            viewsList = new ImageView[]{discard1, discard2, discard3, discard4,
                    table1, table2, table3, table4, table5, table6, table7, table8, table9, table10,
                    table11, table12, table13, table14, table15, table16};
            setImageResources();
            setListeners();
            setTags();
        }

        public void setImageResources(){
            deal.setImageResource(R.drawable.ce);
            for (ImageView v: viewsList){
                v.setImageResource(R.drawable.ce);
            }
        }

        public void dealCard(){
            if(deck.isEmpty()){
                deck = new Deck();
            }
            dealCard = deck.deal();
            dealCardDrawable = getActivity().getResources().getDrawable(Utilities.getCardDrawableResId(dealCard));
            deal.setImageResource(Utilities.getCardDrawableResId(dealCard));
        }

        public void setListeners(){
            deal.setOnTouchListener(new MyTouchListener());
            for (ImageView v: viewsList){
                v.setOnDragListener(new MyDragListener());
            }
        }

        public void setTags(){
            for (ImageView v: viewsList){
                v.setTag(IMAGEVIEW_TAG);
            }
        }
    }
}
