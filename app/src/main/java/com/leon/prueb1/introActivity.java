package com.leon.prueb1;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class introActivity extends AppCompatActivity {

    ViewPager viewPager;
    SlideViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new SlideViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        if(isOpenAlready()){
            Intent i = new Intent(introActivity.this,MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }else{
            SharedPreferences.Editor editor = getSharedPreferences("slide",MODE_PRIVATE).edit();
            editor.putBoolean("slide",true);
            editor.commit();
        }

    }

    private boolean isOpenAlready() {
        SharedPreferences sharedPreferences = getSharedPreferences("slide",MODE_PRIVATE);
        Boolean result = sharedPreferences.getBoolean("slide",false);
        return result;
    }
}