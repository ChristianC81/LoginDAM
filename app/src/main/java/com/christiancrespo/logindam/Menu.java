package com.christiancrespo.logindam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent= getIntent();

        String email= intent.getStringExtra("usr");
        String password= intent.getStringExtra("psw");
        TextView textView =(TextView) findViewById(R.id.textView);
        textView.setText("Email: "+email+ "\nContraseña: "+password);

    }
}