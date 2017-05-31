package com.example.android.android_me.ui;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.android.android_me.R;

public class MainActivity extends AppCompatActivity implements
        MainFragmentList.OnImageClickListener {
    private static final String LOG_TAG ="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onImageSelected(int position) {
        Log.v(LOG_TAG,"positin clicked -> " +  position);
    }
}
