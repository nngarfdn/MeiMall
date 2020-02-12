package com.example.meimall;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.meimall.Models.GroceryItem;

public class AddReviewDialog extends DialogFragment {
    private static final String TAG = "AddReviewDialog";

    private EditText edtName, edtReview;
    private TextView txtItemName, txtWarning ;
    private Button btnAdd ;
    private int itemId = 0 ;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_review,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Add Review")
                .setView(view);

        initViews(view);

        Bundle bundle = getArguments();
        try {
            GroceryItem item = bundle.getParcelable("item");
            txtItemName.setText(item.getName());
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReview();
            }
        });

        return builder.create();
    }

    private void addReview(){
        Log.d(TAG, "addReview: started");

    }

    private void initViews(View view){
        Log.d(TAG, "initViews: started");
        edtName = view.findViewById(R.id.edtName);
        edtReview = view.findViewById(R.id.edtReview);
        txtItemName = view.findViewById(R.id.txtItemName);
        txtWarning = view.findViewById(R.id.txtWarning);
        btnAdd = view.findViewById(R.id.btnAdd);
    }
}
