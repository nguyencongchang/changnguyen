package com.example.restaurant.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.restaurant.DAO.DeskDAO;
import com.example.restaurant.DAO.OrderDAO;
import com.example.restaurant.DTO.DeskDTO;
import com.example.restaurant.DTO.OrderDTO;
import com.example.restaurant.FragmentApp.DisplayMenuFragment;
import com.example.restaurant.HomeActivity;
import com.example.restaurant.LoginActivity;
import com.example.restaurant.PayActivity;
import com.example.restaurant.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AdapterDisplayDesk  extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private int layout;
    private List<DeskDTO> listDesk;

    private DeskDAO deskDAO;
    private ViewHolder viewHolder;
    private FragmentManager fragmentManager;
    private OrderDAO orderDAO;

    public AdapterDisplayDesk(Context context, int layout, List<DeskDTO> listDesk) {
        this.context = context;
        this.layout = layout;
        this.listDesk = listDesk;
        deskDAO = new DeskDAO(context);
        orderDAO = new OrderDAO(context);
        fragmentManager = ((HomeActivity)context).getSupportFragmentManager();
    }
    @Override
    public int getCount() {
        return listDesk.size();
    }
    @Override
    public Object getItem(int position) {
        return listDesk.get(position);
    }
    @Override
    public long getItemId(int position) {
        return listDesk.get(position).getId();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater =LayoutInflater.from(context);
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(layout, parent, false);
            viewHolder.txtDeskName = convertView.findViewById(R.id.txt_desk_name);
            viewHolder.imgDesk = convertView.findViewById(R.id.img_desk);
            viewHolder.imgOrder = convertView.findViewById(R.id.img_order);
            viewHolder.imgPay = convertView.findViewById(R.id.img_pay);
            viewHolder.imgRemove = convertView.findViewById(R.id.img_remove);
            convertView.setTag(viewHolder);


        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(listDesk.get(position).isSelected()){
            loadButton();
        } else {
            hideButton();
        }


        DeskDTO desk = listDesk.get(position);

        String status = deskDAO.getStatusById(desk.getId());
        if( status.equals("true")){
            viewHolder.imgDesk.setImageResource(R.drawable.banantrue);
        } else {
            viewHolder.imgDesk.setImageResource(R.drawable.banan);
        }
        viewHolder.txtDeskName.setText(desk.getName());
        viewHolder.imgDesk.setTag(position);

        viewHolder.imgDesk.setOnClickListener(this);
        viewHolder.imgOrder.setOnClickListener(this);
        viewHolder.imgPay.setOnClickListener(this);
        viewHolder.imgRemove.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View view) {
        viewHolder = (ViewHolder) ((View)view.getParent()).getTag();
        int pos1 = (int)viewHolder.imgDesk.getTag();
        int deskId = listDesk.get(pos1).getId();
        int id = view.getId();
        switch (id) {
            case R.id.img_desk:
                int pos =(int) view.getTag();
                listDesk.get(pos).setSelected(true);
                loadButton();
                break;
            case R.id.img_order:
                HomeActivity activity = (HomeActivity)context;
                Intent intentHome = activity.getIntent();
                int staffId = intentHome.getIntExtra("staff_id",0);
                String status = deskDAO.getStatusById(deskId);
                if(status.equals("false")){
                    // them bang goi mon
                    // cap nhat lai tinh trang ban an
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");
                    String date = simpleDateFormat.format(calendar.getTime());
                    OrderDTO order = new OrderDTO(deskId,staffId,"false",date);
                    int orderId = orderDAO.addOrder(order);
                    if(orderId == 0){
                        Toast.makeText(context, R.string.failed, Toast.LENGTH_SHORT).show();
                    }
                    deskDAO.updateStatus(deskId,"true");
                }
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                DisplayMenuFragment displayMenuFragment = new DisplayMenuFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("desk_id",deskId);
                displayMenuFragment.setArguments(bundle);
                transaction.replace(R.id.content, displayMenuFragment).addToBackStack("adapter_display_desk");
                transaction.commit();

                break;
            case R.id.img_pay:
                if(deskDAO.getStatusById(deskId).equals("true")){
                    Intent intent = new Intent(context, PayActivity.class);
                    intent.putExtra("desk_id", deskId);
                    context.startActivity(intent);
                }else {
                    Toast.makeText(context, "Bàn chưa gọi món!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.img_remove:
                break;
        }
    }
    private void loadButton(){
        viewHolder.imgOrder.setVisibility(View.VISIBLE);
        viewHolder.imgPay.setVisibility(View.VISIBLE);
        viewHolder.imgRemove.setVisibility(View.VISIBLE);
    }
    private void hideButton(){
        viewHolder.imgOrder.setVisibility(View.INVISIBLE);
        viewHolder.imgPay.setVisibility(View.INVISIBLE);
        viewHolder.imgRemove.setVisibility(View.INVISIBLE);
    }


    public class ViewHolder {
        TextView txtDeskName;
        ImageView imgDesk,imgOrder, imgPay, imgRemove;
    }


}
