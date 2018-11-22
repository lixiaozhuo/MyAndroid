package com.lixiaozhuo.androidcomponent.view.fragment.fragment3;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lixiaozhuo.androidcomponent.R;

/**
 *Fragment3_2
 */

public class Fragment3_2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment3_2,container,false);
    }
    @Override
    public void  onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        //获取控件并绑定事件
        Button button = getActivity().findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //获取Fragment3_1的文本并显示
                TextView textView = getActivity().findViewById(R.id.fragment1_text);
                Toast.makeText(getActivity(),textView.getText(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

