package com.animesafar.dinterviewkit.datapackage;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.animesafar.dinterviewkit.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;

public class Recodingsadaptor extends BaseAdapter {


    Context context;
    ArrayList<File> arrayList;
    LayoutInflater layoutInflater;

    selected sele;
    public  interface selected{

        void onselected(int pos);
        void onDelete(int pos);

    }

    public  Recodingsadaptor(Context context,ArrayList<File> arrayList,selected s)
    {
        this.context = context;
        this.arrayList = arrayList;
        layoutInflater = LayoutInflater.from(context);
        this.sele = s;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

    View view1 = layoutInflater.inflate(R.layout.customrecorderview,null);

        TextView textView = view1.findViewById(R.id.textView2);
        textView.setText(arrayList.get(i).getName());

        FloatingActionButton button = view1.findViewById(R.id.button);
        FloatingActionButton deleteButton = view1.findViewById(R.id.deleteAudio);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sele.onselected(i);

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sele.onDelete(i);
            }
        });


        return view1;
    }
}
