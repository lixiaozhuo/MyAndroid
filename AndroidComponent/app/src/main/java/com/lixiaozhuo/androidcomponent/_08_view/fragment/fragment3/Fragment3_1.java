package com.lixiaozhuo.androidcomponent._08_view.fragment.fragment3;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lixiaozhuo.androidcomponent.R;

/**
 * Fragment3_1
 */

public class Fragment3_1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment3_1,container,false);
    }
}
