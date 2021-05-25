package com.example.restaurant;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant.DAO.OrderDAO;
import com.example.restaurant.DAO.OrderDetailDAO;
import com.example.restaurant.DTO.OrderDetailDTO;

public class PutAmountActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtAmount;
    private Button btnOk;
    private int deskId;
    private int dishId;
    private OrderDAO orderDAO;
    private OrderDetailDAO orderDetailDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_put_amount);

        orderDAO = new OrderDAO(this);
        orderDetailDAO = new OrderDetailDAO(this);
        txtAmount = findViewById(R.id.ed_amount);
        btnOk = findViewById(R.id.btn_ok_put_amount);


        deskId = getIntent().getIntExtra("desk_id",0);
        dishId = getIntent().getIntExtra("dish_id", 0);

        btnOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_ok_put_amount){
            int amount = txtAmount.getText().toString().equals("") ? 0 :Integer.parseInt(txtAmount.getText().toString());
            int orderId = (int)orderDAO.getOrderIdByDeskId(deskId, "false");
            boolean check = orderDetailDAO.checkExistDish(orderId, dishId);
//            Toast.makeText(this, orderId+" "+dishId+ " "+amount , Toast.LENGTH_SHORT).show();
            if(check){
//                Toast.makeText(this, "TonTai" , Toast.LENGTH_SHORT).show();
                int oldAmount = orderDetailDAO.getAmountByOrderId(orderId,dishId);
                int sum = oldAmount + amount;
                OrderDetailDTO order = new OrderDetailDTO(orderId, dishId, sum);
                boolean checkUpdate = orderDetailDAO.updateAmount(order);

                if(checkUpdate){
                    Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(this, R.string.failed, Toast.LENGTH_SHORT).show();
                }
            }else{
//                Toast.makeText(this, "KhongTonTai" , Toast.LENGTH_SHORT).show();
                OrderDetailDTO order = new OrderDetailDTO(orderId, dishId, amount);
                boolean checkAdd = orderDetailDAO.addOrderDetail(new OrderDetailDTO(orderId, dishId, amount));
                if (checkAdd){
                    Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, R.string.failed, Toast.LENGTH_SHORT).show();
                }
            }
            finish();
        }
    }
}
