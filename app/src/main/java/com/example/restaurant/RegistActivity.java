package com.example.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant.DAO.StaffDAO;
import com.example.restaurant.DTO.StaffDTO;
import com.example.restaurant.FragmentApp.DatePickerFragment;


public class RegistActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    EditText editTextUsername, editTextPasswd, editTextDateOfBirth, editTextIdentification;
    Button btnSubmit, btnCancel;
    RadioGroup radioGroupSex;
    StaffDAO staffDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_regist);
        editTextUsername = findViewById(R.id.ed_username_regist);
        editTextPasswd = findViewById(R.id.ed_passwd_regist);
        radioGroupSex = findViewById(R.id.sex_regist);
        editTextDateOfBirth =findViewById(R.id.ed_date_of_birth_regist);
        editTextIdentification =findViewById(R.id.ed_identification_regist);
        btnSubmit = findViewById(R.id.bnt_submit_regist);
        btnCancel = findViewById(R.id.btn_cancel_regist);

        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        editTextDateOfBirth.setOnFocusChangeListener(this);

        staffDAO = new StaffDAO(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bnt_submit_regist:
                String strUsername = editTextUsername.getText().toString();
                String strPasswd = editTextPasswd.getText().toString();
                String strSex="";
                switch (radioGroupSex.getCheckedRadioButtonId()){
                    case R.id.male:
                        strSex = "Nam";break;
                    case R.id.female:
                        strSex = "Nữ";break;
                }
                String strDateOfBirth = editTextDateOfBirth.getText().toString();
                String strIdentification= editTextIdentification.getText().toString();
                if (strUsername == null || strUsername.equals("")){
                    Toast.makeText(this, R.string.alert_username, Toast.LENGTH_SHORT).show();
                }else if (strPasswd == null || strPasswd.equals("")){
                    Toast.makeText(this, R.string.alert_password, Toast.LENGTH_SHORT).show();
                } else {
                    StaffDTO staffDTO = new StaffDTO(strUsername,strPasswd,strSex,strDateOfBirth,strIdentification);
                    long check = staffDAO.addStuff(staffDTO);
                    if(check != 0 ){
                        Intent iLogin  = new Intent(this,LoginActivity.class);
                        startActivity(iLogin);
                    }else {
                        Toast.makeText(this, R.string.failed, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btn_cancel_regist:l:
                finish();
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.ed_date_of_birth_regist:
                if(hasFocus){
                    DatePickerFragment datePickerFragment = new DatePickerFragment();
                    datePickerFragment.show(getSupportFragmentManager(),"Date of birth");
                }
                break;
        }

    }
}