package com.example.guanqing.solblackjack.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.guanqing.solblackjack.Game.GameActivity;
import com.example.guanqing.solblackjack.Help.HelpActivity;
import com.example.guanqing.solblackjack.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.app_title_imageView);
        ImageButton startButton = (ImageButton) rootView.findViewById(R.id.startButton);
        ImageButton helpButton = (ImageButton) rootView.findViewById(R.id.helpButton);
        ImageButton storeButton = (ImageButton) rootView.findViewById(R.id.storeButton);
        ImageButton leaderboardButton = (ImageButton) rootView.findViewById(R.id.leaderBoardsButton);
        Log.e("HGQ_DEBUG", "main activity fragment on create view");

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                    imageView.setImageResource(R.drawable.title_pressed);
                    Log.e("HGQ_DEBUG", "action down");
                }
                else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                    imageView.setImageResource(R.drawable.title2);
                    Log.e("HGQ_DEBUG", "action up");

                }
                return false;
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*GameActivityFragment fragment = new GameActivityFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();*/
                Intent intent = new Intent(getActivity(), GameActivity.class);
                startActivity(intent);
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
            }
        });

        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }
}
