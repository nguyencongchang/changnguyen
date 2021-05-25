package com.example.restaurant;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.restaurant.Adapter.AdapterDisplayPay;
import com.example.restaurant.DAO.DeskDAO;
import com.example.restaurant.DAO.OrderDAO;
import com.example.restaurant.DAO.OrderDetailDAO;
import com.example.restaurant.DTO.DishPayDTO;
import com.example.restaurant.FragmentApp.DisplayDeskFragment;

import java.util.List;

public class PayActivity extends AppCompatActivity implements View.OnClickListener {
    private GridView gridView;
    private TextView txtSum;
    private Button btnOk, btnClose;
    private int deskId;
    private OrderDAO orderDAO;
    private OrderDetailDAO orderDetailDAO;
    private DeskDAO deskDAO;
    private int orderId;
    private List<DishPayDTO> listDishPay;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pay);

        gridView = findViewById(R.id.gv_dish_pay);
        txtSum = findViewById(R.id.txt_sum_pay);
        btnOk = findViewById(R.id.btn_ok_pay);
        btnClose = findViewById(R.id.btn_cancel_pay);

        fragmentManager = getSupportFragmentManager();
        orderDAO = new OrderDAO(this);
        orderDetailDAO= new OrderDetailDAO(this);
        deskDAO = new DeskDAO(this);
        deskId = getIntent().getIntExtra("desk_id",0);
        if(deskId>0){

            loadGridview();

            // tinh tong tien
            int sum =0;
            for(int x=0; x<listDishPay.size(); x++){
                DishPayDTO dishPayDTO = listDishPay.get(x);
                sum+= dishPayDTO.getAmount()*dishPayDTO.getPrice();
            }
            txtSum.setText("Tổng tiền: "+sum);

        }

        btnOk.setOnClickListener(this);
        btnClose.setOnClickListener(this);

    }

    private void loadGridview(){
        orderId = (int) orderDAO.getOrderIdByDeskId(deskId,"false");
        listDishPay = orderDetailDAO.getListDishPayByOrderId(orderId);
        AdapterDisplayPay adapterDisplayPay = new AdapterDisplayPay(this,R.layout.custom_layout_pay,listDishPay);
        gridView.setAdapter(adapterDisplayPay);
        adapterDisplayPay.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_ok_pay){
           orderDAO.updateOrderStatusByDesk(deskId, "true");
           deskDAO.updateStatus(deskId, "false");
            Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show();
            loadGridview();
        }
        if(view.getId() == R.id.btn_cancel_pay){
            finish();
//            FragmentTransaction tran = fragmentManager.beginTransaction();
//            DisplayDeskFragment displayDeskFragment = new DisplayDeskFragment();
//            tran.replace(R.id.content,displayDeskFragment);
//            tran.commit();
        }
    }
}
