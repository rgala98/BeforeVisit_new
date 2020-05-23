package com.beforevisit.beforevisit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beforevisit.beforevisit.utility.DefaultTextConfig;

public class CreateAccountActivity extends AppCompatActivity {

    RelativeLayout main_rel;
    ImageView img_back, img_done;
    EditText et_email,et_name,et_mobile,et_password,et_re_password;

    AlertDialog.Builder builder;
    AlertDialog alert;

    String email, name, mobile, password, re_password;

    TextView tv_error_email,tv_error_mobile,tv_error_password;

    public static CreateAccountActivity instanceOfCreateAccAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DefaultTextConfig defaultTextConfig = new DefaultTextConfig();
        defaultTextConfig.adjustFontScale(getResources().getConfiguration(), CreateAccountActivity.this);
        setContentView(R.layout.activity_create_account);
        instanceOfCreateAccAct = this;

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



                email = et_email.getText().toString().trim();
                name = et_name.getText().toString().trim();
                mobile = et_mobile.getText().toString().trim();
                password = et_password.getText().toString().trim();
                re_password = et_re_password.getText().toString().trim();

                tv_error_email.setVisibility(View.GONE);
                tv_error_mobile.setVisibility(View.GONE);
                tv_error_password.setVisibility(View.GONE);

                if(email.equals("") || name.equals("") || mobile.equals("") || password.equals("") || re_password.equals("")){
                    builder = new AlertDialog.Builder(CreateAccountActivity.this);
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
                else if(mobile.length()!=10){
                    tv_error_mobile.setVisibility(View.VISIBLE);
                    et_mobile.requestFocus();
                    et_mobile.setBackgroundResource(R.drawable.red_border_rectangle);
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(500);
                }
                else if (!password.equals(re_password)){

                    tv_error_password.setVisibility(View.VISIBLE);
                    et_re_password.setText("");
                    et_re_password.requestFocus();
                    et_password.setBackgroundResource(R.drawable.red_border_rectangle);
                    et_re_password.setBackgroundResource(R.drawable.red_border_rectangle);
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(500);
                }
                else{
                    builder = new AlertDialog.Builder(CreateAccountActivity.this);
                    builder.setMessage("You will be sent a confirmation email. Please check your email to verify your email.")
                            .setCancelable(true)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //ADD NEW USER FROM HERE
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
    }

    private void init(){
        main_rel = (RelativeLayout) findViewById(R.id.main_rel);

        img_back = (ImageView) findViewById(R.id.img_back);
        img_done = (ImageView) findViewById(R.id.img_done);

        et_email = (EditText) findViewById(R.id.et_email);
        et_name = (EditText) findViewById(R.id.et_name);
        et_mobile = (EditText) findViewById(R.id.et_mobile);
        et_password = (EditText) findViewById(R.id.et_password);
        et_re_password = (EditText) findViewById(R.id.et_re_password);

        tv_error_email = (TextView) findViewById(R.id.tv_error_email);
        tv_error_mobile = (TextView) findViewById(R.id.tv_error_mobile);
        tv_error_password = (TextView) findViewById(R.id.tv_error_password);

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
