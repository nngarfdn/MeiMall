package com.example.meimall;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meimall.Models.GroceryItem;
import com.example.meimall.Models.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static android.content.ContentValues.TAG;

public class MainFragment extends Fragment {

    private RecyclerView newItemsRecView , pupulerItemRecView, suggestedItemRecView ;
    private BottomNavigationView bottomNavigationView;

    private GroceryItemAdapter newItemsAdapter, populerItemsAdapter, suggestedItemAdapter ;

    private  Utils utils;
    private static final String Tag = "MainFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initViews(view);
        initButtonNavMenu();

        utils = new Utils(getActivity());
        utils.initDatabase();

        initRecViews();

//        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("fake database", Context.MODE_PRIVATE);
//        String returnedValue = sharedPreferences.getString("cheese", "");
//        Gson gson = new Gson();
//        GroceryItem cheese = gson.fromJson(returnedValue, GroceryItem.class);
//        Toast.makeText(getActivity(),"asaaf",Toast.LENGTH_SHORT).show();


        return view;
    }

    private void initRecViews() {
        Log.d(TAG, "initRecViews: Started");
        newItemsAdapter = new GroceryItemAdapter(getActivity());
        populerItemsAdapter = new GroceryItemAdapter(getActivity());
        suggestedItemAdapter = new GroceryItemAdapter(getActivity());

        newItemsRecView.setAdapter(newItemsAdapter);
        pupulerItemRecView.setAdapter(populerItemsAdapter);
        suggestedItemRecView.setAdapter(suggestedItemAdapter);

        newItemsRecView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        pupulerItemRecView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        suggestedItemRecView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        ArrayList<GroceryItem> newItems = utils.getAllItems();

        if (null!= newItems) {
            newItemsAdapter.setItems(newItems);
        }

        Comparator<GroceryItem> newItemsComparator = new Comparator<GroceryItem>() {
            @Override
            public int compare(GroceryItem o1, GroceryItem o2) {
                return o1.getId() - o2.getId();
            }
        };

        Comparator<GroceryItem> reversedNewItemsComparator = Collections.reverseOrder(newItemsComparator);
        Collections.sort(newItems, reversedNewItemsComparator);

                if(null != newItems ){
            newItemsAdapter.setItems(newItems);

        }

        ArrayList<GroceryItem> populerItems = utils.getAllItems();

        Comparator<GroceryItem> popularyComparator = new Comparator<GroceryItem>() {
            @Override
            public int compare(GroceryItem o1, GroceryItem o2) {
                return compareByPopularity(o1,o2);
            }
        };

        Comparator<GroceryItem> reversePopularityComparator = Collections.reverseOrder(popularyComparator);
        Collections.sort(populerItems , reversePopularityComparator);

        populerItemsAdapter.setItems(populerItems);


        ArrayList<GroceryItem> suggestedItems = utils.getAllItems();
        Comparator<GroceryItem> suggestedItemsComparator = new Comparator<GroceryItem>() {
            @Override
            public int compare(GroceryItem o1, GroceryItem o2) {
                return o1.getUserPoint() - o2.getUserPoint();
            }
        };

        Comparator<GroceryItem> reversedSuggestedItemsComparator = Collections.reverseOrder(suggestedItemsComparator);
        Collections.sort(suggestedItems,reversedSuggestedItemsComparator);

        suggestedItemAdapter.setItems(suggestedItems);
    }




    private int compareByPopularity (GroceryItem item1, GroceryItem item2){
        Log.d(TAG, "compareByPopularity: Started");

        if (item1.getPopularityPoint()> item2.getPopularityPoint()){
            return 1;
        }else if (item1.getPopularityPoint()<item2.getPopularityPoint()){
            return -1;
        } else {
            return 0;
        }
    }

    private void initViews(View view) {
        Log.d(TAG, "initViews: ");
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        pupulerItemRecView = view.findViewById(R.id.populerItemRecView);
        newItemsRecView = view.findViewById(R.id.newItemRecView);
        suggestedItemRecView = view.findViewById(R.id.suggestedItemRecView);
    }

    private void initButtonNavMenu(){
        Log.d(TAG, "initButtonNavMenu: ");
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.search :
                        Toast.makeText(getActivity(),"Search selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.home :
                        Toast.makeText(getActivity(),"Home selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.cart :
                        Toast.makeText(getActivity(),"Cart selected", Toast.LENGTH_SHORT).show();
                        break;

                }

                return false;
            }
        });

    }
}
