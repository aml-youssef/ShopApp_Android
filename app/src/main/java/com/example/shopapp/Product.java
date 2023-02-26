package com.example.shopapp;

public class Product {
    int ID;
    String P_name;
    String P_description;
    byte[] P_photo;
    int P_price;
    int P_amount;
    //int Seller_id;
    String sellerName;
    public static String currentUserName;

    public Product(int ID, String p_name, String p_description, byte[] p_photo, int p_price, int p_amount, String sellerName) {
        this.ID = ID;
        this.P_name = p_name;
        this.P_description = p_description;
        this.P_photo = p_photo;
        this.P_price = p_price;
        this.P_amount = p_amount;
        this.sellerName = sellerName;
    }
}
