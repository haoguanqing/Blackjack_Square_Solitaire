package com.example.guanqing.solblackjack.Game;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guanqing.solblackjack.Main.MainActivity;
import com.example.guanqing.solblackjack.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends DialogFragment {
    @Bind(R.id.dialog_textview) TextView dialog_textView;
    @Bind(R.id.fb_share_imageview) ImageView fb_share_imageview;
    @Bind(R.id.tweet_imageview) ImageView tweet_imageview;
    @Bind(R.id.wechat_imageview) ImageView wechat_imageview;
    @Bind(R.id.restart_button) Button restart_button;
    @Bind(R.id.back_button) Button back_button;

    private static final String SCORE_KEY = "SCORE_KEY";
    private int score;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param finalScore Parameter 1.
     * @return A new instance of fragment ResultFragment.
     */
    public static ResultFragment newInstance(int finalScore) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putInt(SCORE_KEY, finalScore);
        fragment.setArguments(args);
        return fragment;
    }

    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCORE_KEY, score);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null){
            if (getArguments() != null) {
                score = getArguments().getInt(SCORE_KEY);
            }
        }else{
            score = savedInstanceState.getInt(SCORE_KEY);
            savedInstanceState.clear();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =  inflater.inflate(R.layout.fragment_result, container, false);
        ButterKnife.bind(this, rootView);
        if (score<16){
            dialog_textView.setText(getString(R.string.low_score_dialog_text, score));
        }else{
            dialog_textView.setText(getString(R.string.dialog_text, score));
        }
        getDialog().setCanceledOnTouchOutside(false);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                //set flag to kill the activities on top
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        restart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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
            getDialog().getWindow().setLayout(getResources().getDimensionPixelSize(R.dimen.dialog_horizontal_size),
                    getResources().getDimensionPixelSize(R.dimen.dialog_vertical_size));
        }else{
            getDialog().getWindow().setLayout(getResources().getDimensionPixelSize(R.dimen.dialog_vertical_size),
                    getResources().getDimensionPixelSize(R.dimen.dialog_horizontal_size));
        }
    }
}
