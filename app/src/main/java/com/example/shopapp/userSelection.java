package com.example.shopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class userSelection extends AppCompatActivity {
    databasehelper mydb;
    //public static int CurrentUserId;
    Button sell, buy, showUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);

        mydb=new databasehelper(this);
        //CurrentUserId = mydb.getId(sign_in.username);

        sell = findViewById(R.id.sellId);
        buy = findViewById(R.id.buyId);
        showUserData = findViewById(R.id.dataId);

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sellingIntent = new Intent(userSelection.this, com.example.shopapp.SellingActivity.class);
                startActivity(sellingIntent);
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buyingIntent = new Intent(userSelection.this, com.example.shopapp.BuyingActivity.class);
                startActivity(buyingIntent);
            }
        });

        showUserData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showUserDataIntent = new Intent(userSelection.this, com.example.shopapp.UserProductsView.class);
                startActivity(showUserDataIntent);
            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(userSelection.this, MainActivity.class);
        startActivity(intent);

    }
}