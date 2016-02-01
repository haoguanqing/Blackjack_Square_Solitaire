package com.blackjacksquare.guanqing.solblackjack.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.blackjacksquare.guanqing.solblackjack.Game.GameActivity;
import com.blackjacksquare.guanqing.solblackjack.Help.HelpActivity;
import com.blackjacksquare.guanqing.solblackjack.R;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    @Bind(R.id.app_title_imageView) protected ImageView imageView;
    @Bind(R.id.startButton) protected ImageButton startButton;
    @Bind(R.id.helpButton) protected ImageButton helpButton;
    @Bind(R.id.storeButton) protected ImageButton settingButton;
    @Bind(R.id.leaderBoardsButton) protected ImageButton leaderboardButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);

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
}
