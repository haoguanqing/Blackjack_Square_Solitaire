package com.blackjacksquare.guanqing.solblackjack.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.blackjacksquare.guanqing.solblackjack.Game.GameActivity;
import com.blackjacksquare.guanqing.solblackjack.Help.HelpActivity;
import com.blackjacksquare.guanqing.solblackjack.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    public MainActivityFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.app_title_imageView);
        final ImageButton startButton = (ImageButton) rootView.findViewById(R.id.startButton);
        final ImageButton helpButton = (ImageButton) rootView.findViewById(R.id.helpButton);
        final ImageButton settingButton = (ImageButton) rootView.findViewById(R.id.storeButton);
        final ImageButton leaderboardButton = (ImageButton) rootView.findViewById(R.id.leaderBoardsButton);
        Log.e("HGQ_DEBUG", "main activity fragment on create view");

/*        int drawableResId = 0;
        switch (Utilities.getTheme(getContext())){
            case 1:
                drawableResId = R.drawable.blue_table_withcards;
                break;
            case 2:
                drawableResId = R.drawable.wood_table;
                break;
            case 3:
                drawableResId = R.drawable.metal_table;
                break;
            default:
                drawableResId = R.drawable.poker_table_withcards;
                break;
        }
        rootView.setBackgroundDrawable(getResources().getDrawable(drawableResId));*/

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                    imageView.setImageResource(R.drawable.title_pressed);
                    return true;
                } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                    imageView.setImageResource(R.drawable.title);
                    return true;
                }
                return false;
            }
        });

        startButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                    startButton.setImageResource(R.drawable.start_pressed);
                    return true;
                } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                    startButton.setImageResource(R.drawable.start);
                    Intent intent = new Intent(getActivity(), GameActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        helpButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                    helpButton.setImageResource(R.drawable.help_pressed);
                    return true;
                } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                    helpButton.setImageResource(R.drawable.help);
                    Intent intent = new Intent(getActivity(), HelpActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        settingButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                    settingButton.setImageResource(R.drawable.setting_pressed);
                    return true;
                } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                    settingButton.setImageResource(R.drawable.setting);
                    SettingFragment fragment = new SettingFragment();
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fragment.show(fm, "Dialog");
                    return true;
                }
                return false;
            }
        });

        leaderboardButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                    leaderboardButton.setImageResource(R.drawable.leaderboard_pressed);
                    return true;
                } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                    leaderboardButton.setImageResource(R.drawable.leaderboard);
                    LeaderboardFragment fragment = new LeaderboardFragment();
                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    fragment.show(fm, "Dialog");
                    return true;
                }
                return false;
            }
        });

        return rootView;
    }

/*    @Override
    public void onResume() {
        super.onResume();
        try{
            View rootView = getActivity().getWindow().getDecorView().getRootView();
            int drawableResId = 0;
            switch (Utilities.getTheme(getContext())){
                case 1:
                    drawableResId = R.drawable.blue_table_withcards;
                    break;
                case 2:
                    drawableResId = R.drawable.wood_table;
                    break;
                case 3:
                    drawableResId = R.drawable.metal_table;
                    break;
                default:
                    drawableResId = R.drawable.poker_table_withcards;
                    break;
            }
            rootView.setBackgroundDrawable(getResources().getDrawable(drawableResId));
            rootView.invalidate();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }*/
}
