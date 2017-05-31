package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by uelordi on 31/05/17.
 */

public class MainFragmentList extends Fragment{
    GridView mGView;
    OnImageClickListener mCallback;
    public interface OnImageClickListener {
        void onImageSelected(int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnImageClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                             " must implement OnImageClickListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);
        mGView = (GridView) rootView.findViewById(R.id.gv_puppet_list);
        MasterListAdapter mAdapter = new MasterListAdapter(getActivity().getApplicationContext(),
                                                            AndroidImageAssets.getAll());
        mGView.setAdapter(mAdapter);
        mGView.setOnClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallback.onImageSelected(position);
            }
        });

        return rootView;
    }

}
