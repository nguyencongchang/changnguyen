 package com.example.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant.DAO.StaffDAO;


 public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
     Button btnLogin, btnRegist;
     EditText edUsername, edPassword;
     StaffDAO staffDAO;

     @Override
     protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.layout_login);

         staffDAO = new StaffDAO(this);
         edUsername = (EditText) findViewById(R.id.ed_username_login);
         edPassword = (EditText) findViewById(R.id.ed_password_login);
         btnLogin = (Button) findViewById(R.id.btn_login);
         btnRegist = (Button) findViewById(R.id.btn_regist);
         displayButton();
         btnLogin.setOnClickListener(this);
         btnRegist.setOnClickListener(this);

     }
     @Override
     protected void onResume() {
         super.onResume();
         displayButton();
     }
     private  void displayButton(){
         boolean check = staffDAO.hasStuff();
         if (check){
             btnRegist.setVisibility(View.VISIBLE);
             btnLogin.setVisibility(View.VISIBLE);
         }
         else{
             btnRegist.setVisibility(View.VISIBLE);
             btnLogin.setVisibility(View.GONE);
         }
     }
     @Override
     public void onClick(View v) {
         switch (v.getId()){
             case R.id.btn_login:
                 login();
                 break;
             case R.id.btn_regist:
                 regist();
                 break;
         }
     }
     private void login(){
         String username = edUsername.getText().toString();
         String passwd = edPassword.getText().toString();
         int stuffId = staffDAO.checkStuff(username, passwd);
         if(stuffId != 0){
             Intent intentHome = new Intent(this, HomeActivity.class );
             intentHome.putExtra("username", edUsername.getText().toString());
             intentHome.putExtra("staff_id", stuffId);
             startActivity(intentHome);
         }else
             Toast.makeText(this, R.string.failed, Toast.LENGTH_SHORT).show();
     }
     private void regist(){
         Intent intentRegist = new Intent(this, RegistActivity.class);
         startActivity(intentRegist);
     }
 }
