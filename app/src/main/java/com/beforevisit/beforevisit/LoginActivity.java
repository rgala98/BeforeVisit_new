package com.beforevisit.beforevisit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beforevisit.beforevisit.utility.DefaultTextConfig;

public class LoginActivity extends AppCompatActivity {

    RelativeLayout main_rel;
    ImageView img_back, img_done, img_head;
    EditText et_email, et_password;
    TextView tv_error_email,tv_error_message, tv_header,tv_forgot_password;
    String email,password;

    AlertDialog.Builder builder;
    AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DefaultTextConfig defaultTextConfig = new DefaultTextConfig();
        defaultTextConfig.adjustFontScale(getResources().getConfiguration(), LoginActivity.this);
        setContentView(R.layout.activity_login);

        init();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        main_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(main_rel.getWindowToken(), 0);
            }
        });

        img_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_error_email.setVisibility(View.GONE);
                tv_error_message.setVisibility(View.GONE);

                email = et_email.getText().toString().trim();
                password = et_password.getText().toString().trim();

                if(email.equals("") || password.equals("")){
                    builder = new AlertDialog.Builder(LoginActivity.this);
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
                    et_email.setBackgroundResource(R.drawable.red_border_rectangle);
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(500);
                }
                else{
                    //LOGIN KARO

                    //IF LOGIN UNSUCCESSFUL
//                    img_head.setImageResource(R.drawable.ic_error_login);
//                    tv_error_message.setText("FIREBASE ERROR TEXT HERE");
//                    tv_error_message.setVisibility(View.VISIBLE);
//                    et_email.setBackgroundResource(R.drawable.red_border_rectangle);
//                    et_password.setBackgroundResource(R.drawable.red_border_rectangle);
//                    tv_header.setText("Sorry :/");
                }

            }
        });

        tv_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(intent);
            }
        });
    }


    private void init() {
        main_rel = (RelativeLayout) findViewById(R.id.main_rel);

        img_back = (ImageView) findViewById(R.id.img_back);
        img_done = (ImageView) findViewById(R.id.img_done);
        img_head = (ImageView) findViewById(R.id.img_head);

        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);

        tv_error_email = (TextView) findViewById(R.id.tv_error_email);
        tv_error_message = (TextView) findViewById(R.id.tv_error_message);
        tv_header = (TextView) findViewById(R.id.tv_header);
        tv_forgot_password = (TextView) findViewById(R.id.tv_forgot_password);

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
