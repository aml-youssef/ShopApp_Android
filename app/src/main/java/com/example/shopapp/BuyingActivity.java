package com.example.shopapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BuyingActivity extends AppCompatActivity implements prod_list_adapter.prod_viewhplder.Clistener {

    RecyclerView prod_show_rv;
    RecyclerView.Adapter adapt;
    RecyclerView.LayoutManager lo_manager;
    ArrayList<Product> products=new ArrayList<>();

    DBHelper db;
    Cursor res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buying);
        prod_show_rv=findViewById(R.id.prod_show_rv);
        prod_show_rv.setHasFixedSize(true);
        lo_manager=new LinearLayoutManager(this);
        prod_show_rv.setLayoutManager(lo_manager);
        db = new DBHelper(this);
        res = db.getData();

        if(res!=null && res.getCount() > 0)
        {
            if (res.moveToFirst())
            {
                do {
                    products.add(new Product(res.getInt(0), res.getString(1), res.getString(2), res.getBlob(3), res.getInt(4), res.getInt(5), res.getString(6)));
                } while (res.moveToNext());
            }
        }
        else {
            Toast.makeText(BuyingActivity.this, "no data yet", Toast.LENGTH_LONG).show();
        }


        //      always in the end
        adapt=new prod_list_adapter(this, products, this);
        prod_show_rv.setAdapter(adapt);

    }


    @Override
    public void onclick(int position) {
        Intent intent=new Intent(this, productViewActivity.class);
        intent.putExtra("id",products.get(position).ID);
        intent.putExtra("name",products.get(position).P_name);
        intent.putExtra("desc",products.get(position).P_description);
        intent.putExtra("photo",products.get(position).P_photo);
        intent.putExtra("price",products.get(position).P_price);
        intent.putExtra("amount",products.get(position).P_amount);
        intent.putExtra("seller",products.get(position).sellerName);
        intent.putExtra("mineorgeneral","general");
        startActivity(intent);


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(BuyingActivity.this, userSelection.class);
        startActivity(intent);

    }
}













//
//        if(res.getCount()<1){
//            Toast.makeText(BuyingActivity.this, "no data", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            count++; //temp
//            while (res.moveToNext()) {
//                products.add(new Product(res.getInt(0), res.getString(1), res.getString(2), res.getInt(3), res.getInt(4), res.getInt(5), res.getInt(6)));
//            }
//        }
