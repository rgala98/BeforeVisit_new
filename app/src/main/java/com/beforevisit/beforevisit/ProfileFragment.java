package com.beforevisit.beforevisit;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beforevisit.beforevisit.utility.DefaultTextConfig;

public class ProfileFragment extends Fragment {
    public static final String TAG = "ProfileFragment";
    View view;

    RelativeLayout main_rel;
    EditText et_name, et_email, et_mobile, et_address, et_dob;
    String name, email, mobile, address;
    ImageView img_done;
    TextView tv_error_email,tv_error_mobile;

    AlertDialog.Builder builder;
    AlertDialog alert;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        DefaultTextConfig defaultTextConfig = new DefaultTextConfig();
        defaultTextConfig.adjustFontScale(getResources().getConfiguration(), getActivity());

        view = inflater.inflate(R.layout.fragment_profile, container, false);
        Log.i(TAG,"In Profile Fragment");

        init();

        main_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(main_rel.getWindowToken(), 0);
            }
        });


        img_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_error_email.setVisibility(View.GONE);

                name = et_name.getText().toString().trim();
                email = et_email.getText().toString().trim();
                mobile = et_mobile.getText().toString().trim();
                address = et_address.getText().toString().trim();

                if(name.equals("") || email.equals("") || mobile.equals("")){
                    builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Please enter all the details")
                            .setCancelable(true)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    alert = builder.create();
                    alert.setTitle("Incomplete Details!");
                    alert.show();
                }
                else if (!isValidEmailAddress(email)){
                    tv_error_email.setVisibility(View.VISIBLE);
                    et_email.requestFocus();
                    Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(500);
                }
                else if(mobile.length()!=10){
                    tv_error_mobile.setVisibility(View.VISIBLE);
                    et_mobile.requestFocus();
                    Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(500);
                }
                else{
                    builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Please check the details. These will be updated.")
                            .setCancelable(true)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    UPDATE USER
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });

                    alert = builder.create();
                    alert.setTitle("Confirm Your Details!");
                    alert.show();
                }
            }
        });


        return view;
    }

    private void init(){
        main_rel = (RelativeLayout) view.findViewById(R.id.main_rel);

        et_name = (EditText) view.findViewById(R.id.et_name);
        et_email = (EditText) view.findViewById(R.id.et_email);
        et_mobile = (EditText) view.findViewById(R.id.et_mobile);
        et_address = (EditText) view.findViewById(R.id.et_address);
        et_dob = (EditText) view.findViewById(R.id.et_dob);

        img_done = (ImageView) view.findViewById(R.id.img_done);

        tv_error_email = (TextView) view.findViewById(R.id.tv_error_email);
        tv_error_mobile = (TextView) view.findViewById(R.id.tv_error_mobile);

    }

    public static boolean isValidEmailAddress(String email) {
        boolean stricterFilter = true;
        String stricterFilterString = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
        String laxString = ".+@.+\\.[A-Za-z]{2}[A-Za-z]*";
        String emailRegex = stricterFilter ? stricterFilterString : laxString;
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(emailRegex);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
