package com.example.restaurant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant.DAO.CategoryDAO;
import com.example.restaurant.DTO.CategoryDTO;

public class AddCategoryActivity extends AppCompatActivity {

    EditText editTextCatName;
    Button btnOK;
    CategoryDAO catDAO;
    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_cat);
        catDAO = new CategoryDAO(this);

        editTextCatName = findViewById(R.id.ed_cat_name);
        btnOK = findViewById(R.id.btn_ok_add_cat);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String catName = editTextCatName.getText().toString();
                if(!catName.equals("")){
                    CategoryDTO category = new CategoryDTO(catName);
                    boolean check = catDAO.addCatgory(category);
                    Intent intent = new Intent();
                    intent.putExtra("checkCat", check);
                    setResult(Activity.RESULT_OK,intent);
                    if(check){
                        Toast.makeText(AddCategoryActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
                    }

                    else
                        Toast.makeText(AddCategoryActivity.this, R.string.failed, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(AddCategoryActivity.this, R.string.empty_warning, Toast.LENGTH_SHORT).show();
                }
                finish();

            }
        });

    }
}
