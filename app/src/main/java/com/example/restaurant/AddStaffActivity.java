package com.example.restaurant;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddStaffActivity extends AppCompatActivity {
    EditText edt_manhanvien, edt_user_nv, edt_pass_nv, edt_date_nv;
    CheckBox cbNam, cbNu;
    Button btnThemNV, btnHuyNV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_staff);
        AnhXaLayOut();
        edt_date_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonngay();
            }
        });
        btnHuyNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void AnhXaLayOut() {
        edt_manhanvien = (EditText) findViewById(R.id.EDT_CODE_STAFF);
        edt_user_nv = (EditText) findViewById(R.id.EDT_USER_STAFF);
        edt_pass_nv = (EditText) findViewById(R.id.EDT_PASSWORD_NHANVIEN);
        edt_date_nv = (EditText) findViewById(R.id.EDT_NGAYSINH_STAFF);
        cbNam = (CheckBox) findViewById(R.id.CB_NAM);
        cbNu = (CheckBox) findViewById(R.id.CB_NU);
        btnThemNV = (Button) findViewById(R.id.BTN_THEM_STAFF);
        btnHuyNV = (Button) findViewById(R.id.BTN_HUY_STAFF);

    }
    private void chonngay(){
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang =calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year,month,dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edt_date_nv.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }
}

