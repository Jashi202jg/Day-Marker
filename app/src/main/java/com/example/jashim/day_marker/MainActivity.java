package com.example.jashim.day_marker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView gv;
    Context context;
    ArrayList dates;
    public static String [] datesList = new String[] {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14","15",
            "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gv = (GridView)findViewById(R.id.gridview);
        gv.setAdapter(new CustomAdapter(this, datesList));

    }

    private void LoadPreferences(){
        SharedPreferences prefs = getSharedPreferences("check", MODE_PRIVATE);
        String str= prefs.getString("check", "");

        int count=0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='-'){
                count++;
            }
        }

        TextView tc = (TextView) findViewById(R.id.ItemsChecked);
        tc.setText("No. of days checked : "+count/2);

    }

    @Override
    public void onResume(){
        super.onResume();

        LoadPreferences();
    }
    public void clear(View view){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Do your Yes progress
                        SharedPreferences prefs = getSharedPreferences("check", MODE_PRIVATE);
                        prefs.edit().remove("check").commit();
                        finish();
                        startActivity(getIntent());
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //Do your No progress
                        dialog.dismiss();
                        break;
                }
            }
        };
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("Alert!");ab.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

}
