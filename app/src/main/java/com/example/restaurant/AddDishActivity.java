package com.example.restaurant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant.Adapter.AdapterDisplayCatSpin;
import com.example.restaurant.DAO.CategoryDAO;
import com.example.restaurant.DAO.DishDAO;
import com.example.restaurant.DTO.CategoryDTO;
import com.example.restaurant.DTO.DishDTO;

import java.util.List;

public class AddDishActivity extends AppCompatActivity implements View.OnClickListener {
    public static int REQUEST_CODE_CHANGE_IMG = 30001;
    public static int REQUEST_CODE_ADD_CAT = 30002;

    EditText editTextDishName, editTextPrice;
    ImageView img_dish, img_add_cat;
    Spinner spinCat;
    Button btn_ok, btn_cancel;
    String imgUrl;
    List<CategoryDTO> listCat;
    CategoryDAO categoryDAO;
    DishDAO dishDAO;
    AdapterDisplayCatSpin adapterDisplayCatSpin;

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_dish);
        categoryDAO = new CategoryDAO(this);
        dishDAO = new DishDAO(this);

        editTextDishName = findViewById(R.id.ed_dish_name);
        editTextPrice = findViewById(R.id.ed_price);
        img_dish = findViewById(R.id.img_dish);
        img_add_cat = findViewById(R.id.img_add_cat);
        spinCat = findViewById(R.id.spin_cat);
        btn_ok = findViewById(R.id.btn_ok_add_dish);
        btn_cancel = findViewById(R.id.btn_cancel_add_dish);
         loadCattoSpin();


        img_add_cat.setOnClickListener(this);
        img_dish.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
    }

    private void loadCattoSpin(){
        listCat = categoryDAO.getAllCategory();
        adapterDisplayCatSpin = new AdapterDisplayCatSpin(AddDishActivity.this, R.layout.custom_layout_cat_spin, listCat);
        spinCat.setAdapter(adapterDisplayCatSpin);
        adapterDisplayCatSpin.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_dish:
                Intent intentChangeIMG = new Intent();
                intentChangeIMG.setType("image/*");
                intentChangeIMG.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(intentChangeIMG,"Select photo"),REQUEST_CODE_CHANGE_IMG);
                break;
            case R.id.img_add_cat:
                Intent intent = new Intent(AddDishActivity.this, AddCategoryActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_CAT);
                break;
            case R.id.btn_ok_add_dish:
                int pos = spinCat.getSelectedItemPosition();
                int catID = listCat.get(pos).getId();
                String dishName = editTextDishName.getText().toString();
                String p = editTextPrice.getText().toString();
                if(!dishName.equals("") && !p.equals("")){
                    int price = Integer.parseInt(p);
                    DishDTO dish = new DishDTO(dishName, price,catID ,imgUrl);
                    boolean check = dishDAO.addDish(dish);
                    if(check){
                        Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent();
                        setResult(Activity.RESULT_OK,intent1);
                        finish();
                    }
                    else
                        Toast.makeText(this, R.string.failed, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, R.string.empty_warning, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_cancel_add_dish:
                setResult(Activity.RESULT_OK);
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD_CAT && resultCode== Activity.RESULT_OK){
            boolean check = data.getBooleanExtra("checkCat",false);
            if(check){
                loadCattoSpin();
            }
        }
        if(requestCode == REQUEST_CODE_CHANGE_IMG && resultCode == Activity.RESULT_OK){
            imgUrl = data.getData().toString();
            img_dish.setImageURI(data.getData());
        }

    }

}
