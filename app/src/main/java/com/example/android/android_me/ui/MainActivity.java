package com.example.android.android_me.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.android_me.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        MainFragmentList mfList = new MainFragmentList();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.main_list_layout, mfList).commit();
        super.onCreate(savedInstanceState);
    }
}
