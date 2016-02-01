package com.blackjacksquare.guanqing.solblackjack.Game;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blackjacksquare.guanqing.solblackjack.Main.MainActivity;
import com.blackjacksquare.guanqing.solblackjack.R;
import com.blackjacksquare.guanqing.solblackjack.Utility.Utilities;

import java.util.List;

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
    @Bind(R.id.share_imageview) ImageView wechat_imageview;
    @Bind(R.id.restart_button) Button restart_button;
    @Bind(R.id.back_button) Button back_button;

    private static final String SCORE_KEY = "SCORE_KEY";
    private static final String URI_KEY = "URI_KEY";
    private static final String FACEBOOK = "com.facebook.katana";
    private static final String TWITTER = "com.twitter.android";
    private static final String WECHAT = "com.tencent.mm";
    private int score;
    private Uri screenshotUri;

    private static final String LOG_TAG = "HGQ_DEBUG";

    public ResultFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param finalScore Parameter 1.
     * @return A new instance of fragment ResultFragment.
     */
    public static ResultFragment newInstance(int finalScore, Uri uri) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putInt(SCORE_KEY, finalScore);
        args.putString(URI_KEY, uri.toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SCORE_KEY, score);
        outState.putString(URI_KEY, screenshotUri.toString());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null){
            if (getArguments() != null) {
                score = getArguments().getInt(SCORE_KEY);
                screenshotUri = Uri.parse(getArguments().getString(URI_KEY));
            }
        }else{
            score = savedInstanceState.getInt(SCORE_KEY);
            screenshotUri = Uri.parse(savedInstanceState.getString(URI_KEY));
            savedInstanceState.clear();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView =  inflater.inflate(R.layout.fragment_result, container, false);
        ButterKnife.bind(this, rootView);
        setDialogText();
        getDialog().setCanceledOnTouchOutside(false);

        //image for sharing contents on facebook
        fb_share_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {shareTextAndScreenshot(FACEBOOK);
            }
        });

        //tweeting messages
        tweet_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {shareTextAndScreenshot(TWITTER);}
        });

        //image for sharing contents on wechat
        wechat_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            shareTextAndScreenshot(null);
            }
        });

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


    //--------------------------------------------------------------------------------
    //------------------------------HELPER FUNCTIONS----------------------------------
    //--------------------------------------------------------------------------------
    private void setDialogText(){
        if(Utilities.isNewHighScore(getContext(), score)){
            dialog_textView.setText(getString(R.string.new_high_score_dialog_text, score));
        }else if(score<16){
            dialog_textView.setText(getString(R.string.low_score_dialog_text, score));
        }else{
            dialog_textView.setText(getString(R.string.dialog_text, score));
        }
    }

    private void shareTextAndScreenshot(String application){
        Intent intent = new Intent(Intent.ACTION_SEND);
        String shareContent = getString(R.string.share_content, score);
        intent.putExtra(Intent.EXTRA_TEXT, shareContent);
        intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
        intent.setType("image/*");

        if (application==null || application.length()==0){
            // share via a chooser
            startActivity(Intent.createChooser(intent, getString(R.string.share_from)));
        }else{
            //start the specific application to share the game result
            PackageManager packManager = getContext().getPackageManager();
            List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            boolean resolved = false;
            for (ResolveInfo resolveInfo : resolvedInfoList) {
                if (resolveInfo.activityInfo.packageName.toLowerCase().startsWith(application)) {
                    intent.setClassName(
                            resolveInfo.activityInfo.packageName,
                            resolveInfo.activityInfo.name);
                    resolved = true;
                    break;
                }
            }

            if (resolved) {
                startActivity(intent);
            } else {
                String s;
                if (application.equals(FACEBOOK)) {
                    s = "Facebook";
                } else {
                    s = "Twitter";
                }
                Toast.makeText(getActivity(), getString(R.string.app_not_install, s), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
