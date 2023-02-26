package com.example.shopapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class UserProductsView extends AppCompatActivity implements prod_list_adapter.prod_viewhplder.Clistener {


    RecyclerView prod_show_rv;
    RecyclerView.Adapter adapt;
    RecyclerView.LayoutManager lo_manager;
    ArrayList<Product> products=new ArrayList<>();
    DBHelper db;
    databasehelper userDB;
    Cursor res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_products_view);
        prod_show_rv=findViewById(R.id.prod_show_rv);
        prod_show_rv.setHasFixedSize(true);
        lo_manager=new LinearLayoutManager(this);
        prod_show_rv.setLayoutManager(lo_manager);
        db = new DBHelper(this);

        res = db.getProductOfUser(Product.currentUserName);

        if(res!=null && res.getCount() > 0)
        {
            if (res.moveToFirst())
            {
                do {
                    products.add(new Product(res.getInt(0), res.getString(1), res.getString(2), res.getBlob(3), res.getInt(4), res.getInt(5), res.getString(6)));
                } while (res.moveToNext());
            }
        }else {
            Toast.makeText(UserProductsView.this, "no data yet", Toast.LENGTH_LONG).show();
        }


        //      always in the end
        adapt=new prod_list_adapter(this, products, this);
        prod_show_rv.setAdapter(adapt);

    }


    @Override
    public void onclick(int position) {
        Intent intent=new Intent(this, Showuserproducts.class);
        intent.putExtra("ID",products.get(position).ID);
        intent.putExtra("NAME",products.get(position).P_name);
        intent.putExtra("DESC",products.get(position).P_description);
        intent.putExtra("PHOTO",products.get(position).P_photo);
        intent.putExtra("PRICE",products.get(position).P_price);
        intent.putExtra("AMOUNT",products.get(position).P_amount);
        startActivity(intent);

    }
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(UserProductsView.this, userSelection.class);
        startActivity(intent);

    }
}