package com.example.jashim.day_marker;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

public class CustomAdapter extends BaseAdapter{

    String [] result;
    Context context;
    private static LayoutInflater inflater=null;
    public CustomAdapter(MainActivity mainActivity, String[] datesList) {
        // TODO Auto-generated constructor stub
        result=datesList;
        context=mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        CheckBox ch;
    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.activity_checkbox, null);
        holder.tv= rowView.findViewById(R.id.label);
        holder.ch= rowView.findViewById(R.id.chkbx);

        holder.tv.setText(result[position]);

        SharedPreferences prefs = context.getSharedPreferences("check", MODE_PRIVATE);
        String check1 = prefs.getString("check", "");

        if(check1.contains("-"+position+"-"))
        {holder.ch.setChecked(true);}

        holder.ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                SharedPreferences prefs = context.getSharedPreferences("check", MODE_PRIVATE);
                String check = prefs.getString("check", "");

                if(!isChecked){

                    SharedPreferences.Editor editor = context.getSharedPreferences("check", MODE_PRIVATE).edit();
                    editor.putString("check", check.replaceAll("-"+position+"-",""));
                    editor.apply();


                }else {
                    SharedPreferences.Editor editor = context.getSharedPreferences("check", MODE_PRIVATE).edit();
                    editor.putString("check", check + "-"+position + "-");
                    editor.apply();
                }
            }
        }
        );

        rowView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
               // Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_SHORT).show();

                }
        });

        return rowView;
    }

}