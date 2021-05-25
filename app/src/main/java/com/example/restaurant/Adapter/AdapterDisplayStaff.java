package com.example.restaurant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.restaurant.DTO.StaffDTO;
import com.example.restaurant.R;

import java.util.List;

public class AdapterDisplayStaff extends BaseAdapter {
    private Context context;
    private int layout;
    private List<StaffDTO> staffDTOList;

    public AdapterDisplayStaff(Context context, int layout, List<StaffDTO> staffDTOList) {
        this.context = context;
        this.layout = layout;
        this.staffDTOList = staffDTOList;
    }

    @Override
    public int getCount() {
        return staffDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView tvMaNV, tvUserNV;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder = new ViewHolder();
            viewHolder.tvMaNV = convertView.findViewById(R.id.DONG_NHAN_VIEN_MAnv);
            viewHolder.tvUserNV =convertView.findViewById(R.id.DONG_NHAN_VIEN_USERnv);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        StaffDTO staffDTO = staffDTOList.get(position);
        viewHolder.tvMaNV.setText(staffDTO.getIden());
        viewHolder.tvUserNV.setText(staffDTO.getUsername());

        return convertView;
    }
}
