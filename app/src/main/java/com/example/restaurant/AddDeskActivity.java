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

import com.example.restaurant.DAO.DeskDAO;
import com.example.restaurant.DTO.DeskDTO;

public class AddDeskActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edDeskName;
    Button btnOK;
    DeskDAO deskDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_desk);

        edDeskName = findViewById(R.id.ed_desk_name);
        btnOK = findViewById(R.id.btn_ok_add_desk);
        deskDAO = new DeskDAO(this);
        btnOK.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ok_add_desk:
                String deskName = edDeskName.getText().toString();
                if(deskName == null || deskName.equals("")){
                    Toast.makeText(this, R.string.empty_warning, Toast.LENGTH_SHORT).show();
                } else {
                    boolean check = deskDAO.AddDesk(deskName);
                    Intent intent = new Intent();
                    intent.putExtra("result", check);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                    break;
                }

        }
    }
}
