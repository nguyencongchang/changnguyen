package com.example.restaurant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.restaurant.DTO.DishPayDTO;
import com.example.restaurant.R;

import java.util.List;

public class AdapterDisplayPay extends BaseAdapter {
    private Context context;
    private int layout;
    private List<DishPayDTO> list;
    private ViewHolder viewHolder;

    public AdapterDisplayPay(Context context, int layout, List<DishPayDTO> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if( view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(layout,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.txtdishName = view.findViewById(R.id.dish_name_pay_custom);
            viewHolder.txtAmount = view.findViewById(R.id.amount_pay_custom);
            viewHolder.txtPrice = view.findViewById(R.id.price_pay_custom);
            view.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) view.getTag();
        }
        DishPayDTO dish = list.get(i);
        viewHolder.txtPrice.setText(String.valueOf(dish.getPrice()));
        viewHolder.txtAmount.setText(String.valueOf(dish.getAmount()));
        viewHolder.txtdishName.setText(dish.getName());

        return view;
    }
    class ViewHolder {
        TextView txtdishName,txtAmount, txtPrice;
    }
}
