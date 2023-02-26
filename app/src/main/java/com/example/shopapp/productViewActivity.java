package com.example.shopapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class productViewActivity extends AppCompatActivity {

    int ID;
    ImageView IV_PRODUCT;
    TextView TV_NAME, TV_PRICE, TV_DESCRIPTION, TV_AMOUNT, TV_SELLER;
    Button BTN_BUY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_view);
        IV_PRODUCT=findViewById(R.id.IV_product);
        TV_NAME=findViewById(R.id.TV_NAME);
        TV_PRICE=findViewById(R.id.TV_PRICE);
        TV_DESCRIPTION=findViewById(R.id.TV_DESCRIPTION);
        TV_AMOUNT=findViewById(R.id.TV_AMOUNT);
        TV_SELLER=findViewById(R.id.TV_SELLER);
        BTN_BUY=findViewById(R.id.BTN_BUY);

        ID=getIntent().getIntExtra("id",0);

        TV_NAME.setText(getIntent().getStringExtra("name"));
        TV_PRICE.setText("Price: " + getIntent().getIntExtra("price",0) + " EGP");
        TV_DESCRIPTION.setText("description: " + getIntent().getStringExtra("desc"));
        TV_AMOUNT.setText("Available: " + getIntent().getIntExtra("amount",0) + " piece");
        TV_SELLER.setText("Seller name: " + getIntent().getStringExtra("seller"));


        byte[] Image = getIntent().getByteArrayExtra("photo");
        Bitmap bitmap= BitmapFactory.decodeByteArray(Image, 0, Image.length);
        IV_PRODUCT.setImageBitmap(bitmap);

        BTN_BUY.setOnClickListener(new View.OnClickListener() {
            int finish=getIntent().getIntExtra("amount",0);
            @Override
            public void onClick(View view) {


                if(finish==0)
                {
                    Toast.makeText(getApplicationContext(),"Sorry this item is no longer available",Toast.LENGTH_LONG).show();
                }
                else{
                    com.example.shopapp.DBHelper db=new com.example.shopapp.DBHelper(getApplicationContext());
                    boolean result=db.update_Product(String.valueOf(ID),
                            getIntent().getStringExtra("name"),
                            getIntent().getStringExtra("desc"),
                            getIntent().getByteArrayExtra("photo"),
                            getIntent().getIntExtra("price",0),
                            finish,
                            getIntent().getStringExtra("seller"));


                    finish--;
                    TV_AMOUNT.setText("Available: " + finish + " piece");
                    Toast.makeText(getApplicationContext(),"You have successfully bought this item",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(productViewActivity.this, BuyingActivity.class);
        startActivity(intent);
    }
}