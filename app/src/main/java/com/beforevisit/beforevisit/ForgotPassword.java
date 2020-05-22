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

public class ForgotPassword extends AppCompatActivity {

    RelativeLayout main_rel;
    ImageView img_back, img_done;
    EditText et_email;
    TextView tv_error_email;
    String email;

    AlertDialog.Builder builder;
    AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DefaultTextConfig defaultTextConfig = new DefaultTextConfig();
        defaultTextConfig.adjustFontScale(getResources().getConfiguration(), ForgotPassword.this);
        setContentView(R.layout.activity_forgot_password);

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
                email = et_email.getText().toString().trim();

                if(email.equals("")){
                    builder = new AlertDialog.Builder(ForgotPassword.this);
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
                    //EMAIL SEND KARO
                }
            }
        });
    }

    private void init() {
        main_rel = (RelativeLayout) findViewById(R.id.main_rel);

        img_back = (ImageView) findViewById(R.id.img_back);
        img_done = (ImageView) findViewById(R.id.img_done);

        tv_error_email = (TextView) findViewById(R.id.tv_error_email);

        et_email = (EditText) findViewById(R.id.et_email);
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
