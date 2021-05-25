package com.example.restaurant.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurant.DTO.DishDTO;
import com.example.restaurant.R;

import java.util.List;

public class AdapterDisplayDish extends BaseAdapter {
    private Context context;
    private int layout;
    private List<DishDTO> listDish;
    ViewHolder viewHolder;

    public AdapterDisplayDish(Context context, int layout, List<DishDTO> listDish) {
        this.context = context;
        this.layout = layout;
        this.listDish = listDish;
    }

    @Override
    public int getCount() {
        return  listDish.size();
    }

    @Override
    public Object getItem(int i) {
        return listDish.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listDish.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if( view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(layout,viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.imgDish = view.findViewById(R.id.img_dish_custom);
            viewHolder.txtDishName = view.findViewById(R.id.txt_dish_name_custom);
            viewHolder.txtDishPrice = view.findViewById(R.id.txt_dish_price_custom);
            view.setTag(viewHolder);
        } else {
            viewHolder =(ViewHolder) view.getTag();
        }
        DishDTO dish = listDish.get(i);
        viewHolder.imgDish.setImageURI(Uri.parse(dish.getImageUrl()));
        viewHolder.txtDishName.setText(dish.getName());
        viewHolder.txtDishPrice.setText(""+dish.getPrice());

        return view;
    }
    class ViewHolder{
        ImageView imgDish;
        TextView txtDishName;
        TextView txtDishPrice;

    }
}
