package com.lixiaozhuo.androidcomponent._08_view.fragment.fragment1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lixiaozhuo.androidcomponent.R;

/**
 * Fragment1_1
 */
public class Fragment1_1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment1_1,container,false);
    }
}
