package com.beforevisit.beforevisit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beforevisit.beforevisit.utility.DefaultTextConfig;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";
    ImageView img_menu,img_notification_bell;
    RelativeLayout expanded_menu;
    Boolean is_menu_clicked = false;
    RelativeLayout super_rel;

    TextView tv_profile, tv_saved_places, tv_feedback, tv_list_business,tv_help, tv_about, tv_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DefaultTextConfig defaultTextConfig = new DefaultTextConfig();
        defaultTextConfig.adjustFontScale(getResources().getConfiguration(), MainActivity.this);
        setContentView(R.layout.activity_main);

        init();

        img_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!is_menu_clicked){
                    is_menu_clicked = true;
                    img_menu.setBackgroundColor(getColor(R.color.light_black));
                    expanded_menu.setVisibility(View.VISIBLE);
                }else{
                    is_menu_clicked = false;
                    img_menu.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                    expanded_menu.setVisibility(View.GONE);
                }

            }
        });

        img_notification_bell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginMainActivity.class);
                startActivity(intent);
            }
        });

//        super_rel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                is_menu_clicked = false;
//                img_menu.setBackgroundColor(getColor(R.color.colorPrimaryDark));
//                expanded_menu.setVisibility(View.GONE);
//            }
//        });

        Fragment fragment = new HomeFragment();
        loadFragment(fragment);
        makeMenuItemInvisible(tv_home);
    }


    private void init(){
       img_menu = (ImageView) findViewById(R.id.img_menu);
       img_notification_bell = (ImageView) findViewById(R.id.img_notification_bell);

       expanded_menu = (RelativeLayout) findViewById(R.id.expanded_menu);
       super_rel = (RelativeLayout) findViewById(R.id.super_rel);

       tv_profile = (TextView) findViewById(R.id.tv_profile);
       tv_profile.setOnClickListener(this);

       tv_saved_places = (TextView) findViewById(R.id.tv_saved_places);
       tv_saved_places.setOnClickListener(this);

       tv_feedback = (TextView) findViewById(R.id.tv_feedback);
       tv_feedback.setOnClickListener(this);

       tv_list_business = (TextView) findViewById(R.id.tv_list_business);
       tv_list_business.setOnClickListener(this);

       tv_help = (TextView) findViewById(R.id.tv_help_support);
       tv_help.setOnClickListener(this);

       tv_about = (TextView) findViewById(R.id.tv_about_us);
       tv_about.setOnClickListener(this);

       tv_home = (TextView) findViewById(R.id.tv_home);
       tv_home.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

        Fragment fragment = null;
        switch(view.getId()) {
            case R.id.tv_profile:
                makeMenuItemInvisible(tv_profile);
                fragment = new ProfileFragment();
                loadFragment(fragment);
                break;
            case R.id.tv_saved_places:
                makeMenuItemInvisible(tv_saved_places);
                fragment = new SavedPlacesFragment();
                loadFragment(fragment);
                break;
            case R.id.tv_feedback:
                makeMenuItemInvisible(tv_feedback);
                fragment = new FeedbackFragment();
                loadFragment(fragment);
                break;
            case R.id.tv_list_business:
                makeMenuItemInvisible(tv_list_business);
                fragment = new ListBusinessFragment();
                loadFragment(fragment);
                break;
            case R.id.tv_help_support:
                makeMenuItemInvisible(tv_help);
                fragment = new HelpSupportFragment();
                loadFragment(fragment);
                break;
            case R.id.tv_about_us:
                makeMenuItemInvisible(tv_about);
                fragment = new AboutUsFragment();
                loadFragment(fragment);
                break;

            case R.id.tv_home:
                makeMenuItemInvisible(tv_home);
                fragment = new HomeFragment();
                loadFragment(fragment);
                break;

        }
        loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    private void makeMenuItemInvisible(TextView textView){
        is_menu_clicked = false;
        img_menu.setBackgroundColor(getColor(R.color.colorPrimaryDark));
        expanded_menu.setVisibility(View.GONE);

        tv_profile.setVisibility(View.VISIBLE);
        tv_saved_places.setVisibility(View.VISIBLE);
        tv_feedback.setVisibility(View.VISIBLE);
        tv_list_business.setVisibility(View.VISIBLE);
        tv_help.setVisibility(View.VISIBLE);
        tv_about.setVisibility(View.VISIBLE);
        tv_home.setVisibility(View.VISIBLE);
        textView.setVisibility(View.GONE);
    }
}
