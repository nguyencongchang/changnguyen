package com.example.restaurant;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant.DAO.DeskDAO;

public class ModifyDeskAtivity extends AppCompatActivity {

    EditText editText;
    Button btn;
    DeskDAO deskDAO;
    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_modify_desk);
        deskDAO = new DeskDAO(this);
        editText= findViewById(R.id.ed_desk_name_modify);
        btn = findViewById(R.id.btn_ok_modify_desk);

        int deskId = getIntent().getIntExtra("desk_id", 0);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                if(!name.trim().equals("")){
                    deskDAO.modifyName(deskId, name);
                    setResult(Activity.RESULT_OK);
                    finish();
                } else{
                    Toast.makeText(ModifyDeskAtivity.this, R.string.empty_warning, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
