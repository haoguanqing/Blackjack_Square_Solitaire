package com.example.guanqing.solblackjack.Game;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guanqing.solblackjack.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class GameActivityFragment extends Fragment {

    public GameActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game, container, false);
    }
}
