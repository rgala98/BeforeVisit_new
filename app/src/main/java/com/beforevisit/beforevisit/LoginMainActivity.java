package com.beforevisit.beforevisit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.beforevisit.beforevisit.utility.DefaultTextConfig;

public class LoginMainActivity extends AppCompatActivity {

    Button btn_create_account,btn_login;
    TextView tv_skip;
    ImageView image_facebook_login,image_google_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DefaultTextConfig defaultTextConfig = new DefaultTextConfig();
        defaultTextConfig.adjustFontScale(getResources().getConfiguration(), LoginMainActivity.this);
        setContentView(R.layout.activity_login_main);

        init();

        btn_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init(){

        btn_create_account = (Button) findViewById(R.id.btn_create_account);
        btn_login = (Button) findViewById(R.id.btn_login);

        tv_skip = (TextView) findViewById(R.id.tv_skip);

        image_facebook_login = (ImageView) findViewById(R.id.image_facebook_login);
        image_google_login = (ImageView) findViewById(R.id.image_google_login);

    }
}
