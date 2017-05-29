package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uelordi on 28/05/17.
 */

public class BodyPartFragment extends Fragment {
    private static final String TAG =">>>> BodyPartFragment";
    private static final String BODY_PART_LIST = "body_part_list";
    private static final String BODY_PART_LIST_INDEX = "body_part_list_index";
    private List<Integer> mImageIDs;
    private int mListIndex;
    public BodyPartFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);
        ImageView imView = (ImageView) rootView.findViewById(R.id.body_part_fragment);
        if(savedInstanceState != null) {
            mListIndex = savedInstanceState.getInt(BODY_PART_LIST_INDEX);
            mImageIDs = savedInstanceState.getIntegerArrayList(BODY_PART_LIST_INDEX);
        }
        if(mImageIDs != null) {
            imView.setImageResource(mImageIDs.get(mListIndex));
        } else {
            Log.v(TAG, "this fragment has null list of images" );
        }
        imView.setImageResource(AndroidImageAssets.getBodies().get(0));
        imView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(mListIndex < mImageIDs.size()) {
                            mListIndex++;
                        }
                       // v.setImageResource(mImageIDs.get(mListIndex));
                    }
                }

        );
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putIntegerArrayList(BODY_PART_LIST, (ArrayList<Integer>) mImageIDs);
        outState.putInt(BODY_PART_LIST_INDEX,mListIndex);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {

        super.onViewStateRestored(savedInstanceState);
    }

    public List<Integer> getImageIDs() {
        return mImageIDs;
    }

    public void setImageIDs(List<Integer> mImageIDs) {
        this.mImageIDs = mImageIDs;
    }

    public int getListIndex() {
        return mListIndex;
    }

    public void setListIndex(int mListIndex) {
        this.mListIndex = mListIndex;
    }
}
