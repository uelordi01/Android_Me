package com.example.android.android_me.ui;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.android.android_me.R;

public class MainActivity extends AppCompatActivity implements
        MainFragmentList.OnImageClickListener {
    private static final String LOG_TAG ="MainActivity";
    private int headIndex;
    private int bodyIndex;
    private int legIndex;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.two_pane_layout) != null) {
            mTwoPane = true;
        } else {
            mTwoPane = false;
        }
    }

    @Override
    public void onImageSelected(int position) {
        Log.v(LOG_TAG,"positin clicked -> " +  position);
        int bodyPartNumber = position / 12;
        int listIndex = position - 12*bodyPartNumber;

        // Set the currently displayed item for the correct body part fragment
        switch(bodyPartNumber) {
            case 0: headIndex = listIndex;
                break;
            case 1: bodyIndex = listIndex;
                break;
            case 2: legIndex = listIndex;
                break;
            default: break;
        }

        // Put this information in a Bundle and attach it to an Intent that will launch an AndroidMeActivity
        Bundle b = new Bundle();
        b.putInt("headIndex", headIndex);
        b.putInt("bodyIndex", bodyIndex);
        b.putInt("legIndex", legIndex);
        final Intent intent = new Intent(this,AndroidMeActivity.class);
        intent.putExtras(b);
        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
