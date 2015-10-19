package com.blackjacksquare.guanqing.solblackjack.Main;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.blackjacksquare.guanqing.solblackjack.R;
import com.blackjacksquare.guanqing.solblackjack.Utility.Utilities;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingFragment extends DialogFragment {
    @Bind(R.id.radioButton_group_container) RadioGroup radioGroup;
    @Bind(R.id.set_blue_radioButton) RadioButton blue_button;
    @Bind(R.id.set_green_radioButton) RadioButton green_button;
    @Bind(R.id.set_wood_radioButton) RadioButton wood_button;
    @Bind(R.id.setting_button) Button apply_button;

    private static int theme;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        theme = Utilities.getTheme(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, rootView);
        switch (theme){
            case 1:
                blue_button.setChecked(true);
                break;
            case 2:
                wood_button.setChecked(true);
                break;
            default:
                green_button.setChecked(true);
                break;
        }
        apply_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedbuttonId = radioGroup.getCheckedRadioButtonId();
                switch (checkedbuttonId){
                    case R.id.set_blue_radioButton:
                        Utilities.setTheme(getContext(), 1);
                        break;
                    case R.id.set_wood_radioButton:
                        Utilities.setTheme(getContext(), 2);
                        break;
                    default:
                        Utilities.setTheme(getContext(), 0);
                        break;
                }
                getDialog().dismiss();
/*                Intent intent = new Intent(getActivity(), MainActivity.class);
                //set flag to kill the activities on top
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);*/

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
            getDialog().getWindow().setLayout(getResources().getDimensionPixelSize(R.dimen.setting_dialog_horizontal_size),
                    getResources().getDimensionPixelSize(R.dimen.setting_dialog_vertical_size));
        }else{
            getDialog().getWindow().setLayout(getResources().getDimensionPixelSize(R.dimen.setting_dialog_vertical_size),
                    getResources().getDimensionPixelSize(R.dimen.setting_dialog_horizontal_size));
        }
    }
}
