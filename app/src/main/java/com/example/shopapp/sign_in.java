package com.example.shopapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class sign_in extends AppCompatActivity {
    databasehelper mydb;
    private EditText user,pass;
    private Button signintoacc;
    public static String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();

        mydb=new databasehelper(this);
        user=findViewById(R.id.userin);
        pass=findViewById(R.id.passin);
        signintoacc=findViewById(R.id.signintoacc);
        signintoacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mydb.checkhasaccount(user.getText().toString(),pass.getText().toString()))
                {
                    username = user.getText().toString().trim();

                    //userSelection.CurrentUserId = mydb.getId(user.getText().toString().trim());
                    Product.currentUserName = user.getText().toString().trim();

                    Intent intacc = new Intent(sign_in.this, com.example.shopapp.userSelection.class);/////////
                    startActivity(intacc);
                }
                else {
                    pass.setText("");
                    Toast.makeText(sign_in.this, "Sorry.. The username or password is invalid", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}