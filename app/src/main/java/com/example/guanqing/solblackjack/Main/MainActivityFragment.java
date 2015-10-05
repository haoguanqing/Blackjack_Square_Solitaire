package com.example.guanqing.solblackjack.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.guanqing.solblackjack.Game.GameActivityFragment;
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
        ImageButton startButton = (ImageButton) rootView.findViewById(R.id.startButton);
        ImageButton helpButton = (ImageButton) rootView.findViewById(R.id.helpButton);
        ImageButton storeButton = (ImageButton) rootView.findViewById(R.id.storeButton);
        ImageButton leaderboardButton = (ImageButton) rootView.findViewById(R.id.leaderBoardsButton);
        Log.e("HGQ_DEBUG", "main activity fragment on create view");

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameActivityFragment fragment = new GameActivityFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
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
