package com.example.guanqing.solblackjack.Help;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guanqing.solblackjack.R;

public class HelpTextFragment extends android.support.v4.app.Fragment {
    private static final String TEXT_KEY = "TEXT KEY";
    private Context context;

    public HelpTextFragment() {
        // Required empty public constructor
    }

    static android.support.v4.app.Fragment newInstance(int position){
        Bundle args = new Bundle();
        args.putInt(TEXT_KEY, position);
        HelpTextFragment fragment = new HelpTextFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_help_text, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.help_text_textView);

        Bundle args = getArguments();
        int position = args.getInt(TEXT_KEY);
        CharSequence[] texts = new CharSequence[]{Html.fromHtml(getString(R.string.help_text0)),
                Html.fromHtml(getString(R.string.help_text1)),
                Html.fromHtml(getString(R.string.help_text2)),
                Html.fromHtml(getString(R.string.help_text3))};
        Resources r = getResources();
        int color1 = r.getColor(R.color.color1);
        int color2 = r.getColor(R.color.color2);
        int color3 = r.getColor(R.color.color3);
        int color4 = r.getColor(R.color.color4);

        int[] colors = new int[]{color1, color2, color3, color4};
        textView.setText(texts[position]);
        textView.setTextColor(r.getColor(R.color.help_text_color));
        rootView.setBackgroundColor(colors[position]);
        return rootView;
    }
}
