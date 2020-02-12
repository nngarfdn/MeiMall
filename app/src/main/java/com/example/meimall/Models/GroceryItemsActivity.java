package com.example.meimall.Models;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.meimall.R;

import java.util.ArrayList;

public class GroceryItemsActivity extends AppCompatActivity {
    private static final String TAG = "GroceryItemsActivity";

    private TextView txtName,  txtPrice, txtDescrption, txtAvailibility ;
    private ImageView itemImage;
    private Button btnAddToCart;

    private ImageView firstFilledStar, scondFilledStar, thirdFilledStar, firstEmptyStar, scondEmptyStar, thirdEmptyStar ;
    private RecyclerView reviewRecView;
    private Utils utils;
    private ReviewsAdapter adapter;


    private RelativeLayout addReviewRelLayout;
    private GroceryItem incomingItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_items);
        utils = new Utils(this);
        Intent intent = getIntent();
        try {
            incomingItem = intent.getParcelableExtra("item");
            setViewsValue();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        
        initViews();
    }

    private void setViewsValue() {
        Log.d(TAG, "setViewsValue: started");
        txtName.setText(incomingItem.getName());
        txtPrice.setText(String.valueOf(incomingItem.getPrice() + "$"));
        txtAvailibility.setText(incomingItem.getAvailableAmount() + "number(s) available");

        Glide.with(this)
                .asBitmap()
                .load(incomingItem.getImageUrl())
                .into(itemImage);

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: add item to the cart
            }
        });

        //TODO:  handle the star situation
        adapter = new ReviewsAdapter();
        reviewRecView.setAdapter(adapter);
        reviewRecView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Review> reviews = utils.getReviewForItem(incomingItem.getId());
        if(null != reviews ) {
            adapter.setReviews(reviews);
        }

        addReviewRelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : show dialog
            }
        });
    }

    private void initViews() {
     txtName = findViewById(R.id.txtName);
     txtPrice = findViewById(R.id.txtPrice);
     txtDescrption = findViewById(R.id.txtDescription);
     txtAvailibility = findViewById(R.id.txtAvailability);
     itemImage = findViewById(R.id.itemImage);
     btnAddToCart = findViewById(R.id.btnAddToCart);
     firstEmptyStar = findViewById(R.id.firstEmptyStar);
     firstFilledStar = findViewById(R.id.firstFilledStar);
     scondEmptyStar = findViewById(R.id.scondEmptyStar);
     scondFilledStar = findViewById(R.id.scondFilledStar);
     thirdEmptyStar = findViewById(R.id.thirdEmptyStar);
     thirdFilledStar = findViewById(R.id.thirdFilledStar);
     reviewRecView = findViewById(R.id.reviewsRecView);
     addReviewRelLayout = findViewById(R.id.addReviewRelLayout);

    }
}
