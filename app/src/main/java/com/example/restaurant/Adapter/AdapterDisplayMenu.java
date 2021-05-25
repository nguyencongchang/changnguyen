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

import com.example.restaurant.DAO.CategoryDAO;
import com.example.restaurant.DTO.CategoryDTO;
import com.example.restaurant.R;

import java.util.List;

public class AdapterDisplayMenu extends BaseAdapter {
    Context context;
    int layout;
    List<CategoryDTO> listCat;
    ViewHolder viewHolder;
    CategoryDAO categoryDAO;

    public AdapterDisplayMenu(Context context, int layout, List<CategoryDTO> listCat) {
        this.context = context;
        this.layout = layout;
        this.listCat = listCat;
        categoryDAO = new CategoryDAO(context);
    }

    @Override
    public int getCount() {
        return listCat.size();
    }
    @Override
    public Object getItem(int position) {
        return listCat.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listCat.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if( convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layout,parent, false);
            viewHolder = new ViewHolder();
            viewHolder.img_cat = convertView.findViewById(R.id.img_cat_custom);
            viewHolder.txtCatName = convertView.findViewById(R.id.txt_cat_custom);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CategoryDTO category = listCat.get(position);
        String img = categoryDAO.getImgById(category.getId());
        if(!img.equals(""))
            viewHolder.img_cat.setImageURI(Uri.parse(img));
        viewHolder.txtCatName.setText(category.getName());
        return convertView;

    }
    class ViewHolder{
        ImageView img_cat;
        TextView txtCatName;
    }
}
