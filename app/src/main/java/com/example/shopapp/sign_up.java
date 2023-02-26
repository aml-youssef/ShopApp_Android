package com.example.shopapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class sign_up extends AppCompatActivity {
    databasehelper mydb;
    private EditText uname,pass,conpass;
    private Button signin,submit;
    private boolean completed;
    private boolean unique_username=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        mydb=new databasehelper(this);
        uname=findViewById(R.id.uname);
        pass=findViewById(R.id.passup);
        conpass=findViewById(R.id.conpassup);
        submit=findViewById(R.id.submit);
        signin=findViewById(R.id.signin);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                completed=true;
                //check that username is unique
                if(mydb.checkunique(uname.getText().toString())) {
                    if(uname.getText().toString().isEmpty() || pass.getText().toString().isEmpty() ||conpass.getText().toString().isEmpty() || !(pass.getText().toString().equals(conpass.getText().toString())))
                    {
                        Toast.makeText(sign_up.this, "Please check your data", Toast.LENGTH_LONG).show();
                        completed = false;
                    }

                }else {
                    Toast.makeText(sign_up.this, "Username is taken", Toast.LENGTH_LONG).show();
                    completed = false;
                }


                if(completed)
                {
                    if(mydb.insertdata(uname.getText().toString(),pass.getText().toString())) {
                        Product.currentUserName = uname.getText().toString().trim();
                        Toast.makeText(sign_up.this, "Account created", Toast.LENGTH_LONG).show();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                //sign_in.username=uname.getText().toString().trim();
                                //userSelection.CurrentUserId = mydb.getId(uname.getText().toString().trim());

                                Product.currentUserName = uname.getText().toString().trim();
                                Intent intupacc = new Intent(sign_up.this, com.example.shopapp.userSelection.class);
                                startActivity(intupacc);
                            }
                        },1000);
                    }
                    else
                        Toast.makeText(sign_up.this,"Sorry..Try agin",Toast.LENGTH_LONG).show();
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intlog=new Intent(sign_up.this,sign_in.class);
                startActivity(intlog);
            }
        });
    }
}