package com.example.android.android_me.ui;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

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
            GridView gridView = (GridView) findViewById(R.id.gv_puppet_list);
            gridView.setNumColumns(2);

            // Getting rid of the "Next" button that appears on phones for launching a separate activity
            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);
            if(savedInstanceState == null) {
                BodyPartFragment head_fragment = new BodyPartFragment();
                BodyPartFragment body_fragment = new BodyPartFragment();
                BodyPartFragment leg_fragment = new BodyPartFragment();
                int headIndex = getIntent().getIntExtra("headIndex", 0);

                head_fragment.setImageIDs(AndroidImageAssets.getHeads());
                head_fragment.setListIndex(headIndex);

                int bodyIndex = getIntent().getIntExtra("bodyIndex", 0);
                body_fragment.setImageIDs(AndroidImageAssets.getBodies());
                body_fragment.setListIndex(bodyIndex);

                int legIndex = getIntent().getIntExtra("legIndex", 0);
                leg_fragment.setImageIDs(AndroidImageAssets.getLegs());
                leg_fragment.setListIndex(legIndex);
                FragmentManager fm = getSupportFragmentManager();

                fm.beginTransaction()
                        .add(R.id.head_container, head_fragment)
                        .commit();
                fm.beginTransaction()
                        .add(R.id.body_container, body_fragment)
                        .commit();
                fm.beginTransaction()
                        .add(R.id.leg_container, leg_fragment)
                        .commit();
            }
        } else {
            mTwoPane = false;
        }
    }

    @Override
    public void onImageSelected(int position) {
        Log.v(LOG_TAG,"positin clicked -> " +  position);
        int bodyPartNumber = position / 12;
        int listIndex = position - 12*bodyPartNumber;
        if(mTwoPane) {
            BodyPartFragment newFragment = new BodyPartFragment();

            // Set the currently displayed item for the correct body part fragment
            switch (bodyPartNumber) {
                case 0:
                    // A head image has been clicked
                    // Give the correct image resources to the new fragment
                    newFragment.setImageIDs(AndroidImageAssets.getHeads());
                    newFragment.setListIndex(listIndex);
                    // Replace the old head fragment with a new one
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container, newFragment)
                            .commit();
                    break;
                case 1:
                    newFragment.setImageIDs(AndroidImageAssets.getBodies());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container, newFragment)
                            .commit();
                    break;
                case 2:
                    newFragment.setImageIDs(AndroidImageAssets.getLegs());
                    newFragment.setListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container, newFragment)
                            .commit();
                    break;
                default:
                    break;
            }
        } else {
            switch(bodyPartNumber) {
                case 0: headIndex = listIndex;
                    break;
                case 1: bodyIndex = listIndex;
                    break;
                case 2: legIndex = listIndex;
                    break;
                default: break;
            }

        // Set the currently displayed item for the correct body part fragment


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
}
