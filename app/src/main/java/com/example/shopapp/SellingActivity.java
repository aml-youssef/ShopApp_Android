package com.example.shopapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SellingActivity extends AppCompatActivity {

    EditText product, description, price , amount;
    Button insert, choose_photo_btn;
    ImageView choose_photo_iv;
    com.example.shopapp.DBHelper db;
    databasehelper userDB;
    Cursor res;
    final int requestCodeGallery=999;
    public int currentUserId;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==requestCodeGallery)
        {
            if(grantResults.length> 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, requestCodeGallery);
                }
            else {
                Toast.makeText(getApplicationContext(),"You don't have permission ",Toast.LENGTH_LONG).show();
            }
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == requestCodeGallery  && data!=null)
        {
            Uri uri=data.getData();
            try {
                InputStream inputStream= getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                choose_photo_iv.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"There is null",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling);

        product = findViewById(R.id.productid);
        description =findViewById(R.id.descriptionid);
        price = findViewById(R.id.priceid);
        amount = findViewById(R.id.amountid);
        choose_photo_btn=findViewById(R.id.choose_image_btn);
        choose_photo_iv=findViewById(R.id.choose_image_iv);
        insert = findViewById(R.id.insertid);
        db = new com.example.shopapp.DBHelper(this);

        choose_photo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(SellingActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        requestCodeGallery);
            }
        });


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String producttxt = product.getText().toString();
                String descriptiontxt = description.getText().toString();
                String pricetxt = price.getText().toString();
                String amounttxt = amount.getText().toString();
                if(TextUtils.isEmpty(pricetxt)||TextUtils.isEmpty(producttxt)||TextUtils.isEmpty(descriptiontxt)||TextUtils.isEmpty(amounttxt))
                {
                    Toast.makeText(SellingActivity.this, "please fill the empty fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean check = db.insert_Product(producttxt, descriptiontxt, imageViewToByte(choose_photo_iv),Integer.parseInt(pricetxt),Integer.parseInt(amounttxt), Product.currentUserName);
                    if(check){
                        Toast.makeText(SellingActivity.this, "new entry inserted", Toast.LENGTH_SHORT).show();
                        product.setText("");
                        description.setText("");
                        price.setText("");
                        amount.setText("");
                        choose_photo_iv.setImageResource(R.drawable.ic_launcher_background);
                    }
                    else{
                        Toast.makeText(SellingActivity.this, "not inserted", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private byte[] imageViewToByte(ImageView imageView) {
        Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

}