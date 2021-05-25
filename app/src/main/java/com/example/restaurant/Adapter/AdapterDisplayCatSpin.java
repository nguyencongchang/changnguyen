package com.example.restaurant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.restaurant.DAO.CategoryDAO;
import com.example.restaurant.DTO.CategoryDTO;
import com.example.restaurant.R;

import java.util.List;

public class AdapterDisplayCatSpin extends BaseAdapter {
    private Context context;
    int layout;
    List<CategoryDTO> listCat;
    ViewHolder viewHolder;

    public AdapterDisplayCatSpin(Context context, int layout, List<CategoryDTO> listCat) {
        this.context = context;
        this.layout = layout;
        this.listCat = listCat;
    }

    @Override
    public int getCount() {
        return listCat.size();
    }

    @Override
    public Object getItem(int i) {
        return listCat.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listCat.get(i).getId();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layout, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.txtCat = convertView.findViewById(R.id.txt_cat_spin);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CategoryDTO category = listCat.get(position);
        viewHolder.txtCat.setText(category.getName());
        viewHolder.txtCat.setTag(category.getId());
        return convertView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(layout, viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.txtCat = view.findViewById(R.id.txt_cat_spin);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        CategoryDTO category = listCat.get(i);
        viewHolder.txtCat.setText(category.getName());
        viewHolder.txtCat.setTag(category.getId());
        return view;
    }
    class ViewHolder{
        TextView txtCat;
    }
}
