package com.blackjacksquare.guanqing.solblackjack.Main;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.blackjacksquare.guanqing.solblackjack.R;
import com.blackjacksquare.guanqing.solblackjack.Utility.Utilities;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LeaderboardFragment extends DialogFragment {
    @Bind(R.id.high_scores) TextView high_scores_textView;
    @Bind(R.id.average_score) TextView average_score_textView;
    @Bind(R.id.total_games_num) TextView total_games_num_textView;
    @Bind(R.id.reset_button) Button reset_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_leaderboard, container, false);
        ButterKnife.bind(this, rootView);
        setDialogText();
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilities.resetStatistics(getContext());
                setDialogText();
            }
        });
        return rootView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getDialog() == null)
            return;
        if (getResources().getConfiguration().orientation==1){
            //get dimensions from dimens.xml so as to write them in dp and retrieve them in px
            //ORIENTATION_PORTRAIT
            getDialog().getWindow().setLayout(getResources().getDimensionPixelSize(R.dimen.leaderboard_horizontal_size),
                    getResources().getDimensionPixelSize(R.dimen.leaderboard_vertical_size));
        }else{
            //ORIENTATION_LANDSCAPE
            getDialog().getWindow().setLayout(getResources().getDimensionPixelSize(R.dimen.dialog_vertical_size),
                    getResources().getDimensionPixelSize(R.dimen.leaderboard_horizontal_size));
        }
    }

    private void setDialogText(){
        high_scores_textView.setText(Utilities.getHighScores(getContext()));
        average_score_textView.setText(Utilities.getAvgScore(getContext()));
        total_games_num_textView.setText(Utilities.getTotalGameNum(getContext())+"");
        high_scores_textView.invalidate();
        average_score_textView.invalidate();
        total_games_num_textView.invalidate();
    }
}
