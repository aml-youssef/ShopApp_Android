package com.example.shopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Showuserproducts extends AppCompatActivity {

    ImageView IV_PRODUCT;
    TextView TV_NAME, TV_PRICE, TV_DESCRIPTION, TV_AMOUNT, TV_SELLER, TV_PRODUCTID;
    Button BTN_Delete;
    DBHelper db;
    databasehelper userDB;
    String sellername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBHelper(this);
        setContentView(R.layout.activity_showuserproducts);
        IV_PRODUCT=findViewById(R.id.IV_products);
        TV_NAME=findViewById(R.id.TV_Name);
        TV_PRICE=findViewById(R.id.TV_Price);
        TV_DESCRIPTION=findViewById(R.id.TV_Description);
        TV_AMOUNT=findViewById(R.id.TV_Amount);
        TV_SELLER=findViewById(R.id.TV_seller);
        TV_PRODUCTID=findViewById(R.id.TV_product_id);
        BTN_Delete=findViewById(R.id.BTN_Delete);

        TV_NAME.setText(getIntent().getStringExtra("NAME"));
        TV_PRICE.setText("Price: " + getIntent().getIntExtra("PRICE",0) + " EGP");
        TV_DESCRIPTION.setText("description: " + getIntent().getStringExtra("DESC"));
        TV_AMOUNT.setText("Available: " + getIntent().getIntExtra("AMOUNT",0) + " piece");
        TV_SELLER.setText("Seller_id: " + Product.currentUserName);
        TV_PRODUCTID.setText("Product ID: " + getIntent().getIntExtra("ID",1));

        byte[] Image = getIntent().getByteArrayExtra("PHOTO");
        Bitmap bitmap= BitmapFactory.decodeByteArray(Image, 0, Image.length);
        IV_PRODUCT.setImageBitmap(bitmap);
        sellername = Product.currentUserName;

        BTN_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deleting=String.valueOf(getIntent().getIntExtra("ID",1));
                db.delete(deleting);
                Toast.makeText(Showuserproducts.this, "this item has been deleted", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Showuserproducts.this, UserProductsView.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

            Intent intent = new Intent(Showuserproducts.this, UserProductsView.class);
            startActivity(intent);

    }

}