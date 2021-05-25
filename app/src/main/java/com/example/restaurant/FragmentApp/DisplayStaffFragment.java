package com.example.restaurant.FragmentApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.restaurant.Adapter.AdapterDisplayMenu;
import com.example.restaurant.Adapter.AdapterDisplayStaff;
import com.example.restaurant.Adapter.AdapterDisplayStaff;
import com.example.restaurant.AddDishActivity;
import com.example.restaurant.AddStaffActivity;
import com.example.restaurant.DAO.CategoryDAO;
import com.example.restaurant.DAO.StaffDAO;
import com.example.restaurant.DTO.StaffDTO;
import com.example.restaurant.R;

import java.util.ArrayList;

public class DisplayStaffFragment extends Fragment {
    ListView lvdanhsachNV;
    AdapterDisplayStaff adapter_staff;
    ArrayList<StaffDTO> staffDTOS;
    StaffDAO staffDAO;
    public  static  int REQUEST_CODE_ADD_STAFF = 123;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_display_staff,container, false);
        setHasOptionsMenu(true);
        staffDAO = new StaffDAO(getActivity());
        staffDTOS = new ArrayList<StaffDTO>();
        staffDTOS.add(new StaffDTO("1", "username","nam","nam","123"));
        lvdanhsachNV = view.findViewById(R.id.LV_DANHSACH_NV);

        adapter_staff = new AdapterDisplayStaff(getActivity(), R.layout.custom_layout_staff, staffDTOS);
        lvdanhsachNV.setAdapter(adapter_staff);
        adapter_staff.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuItem itemAddDish = menu.add(1,R.id.item_add_staff,1,R.string.add_dish);
        itemAddDish.setIcon(R.drawable.add);
        itemAddDish.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.item_add_staff){
            Intent intent = new Intent(getActivity(), AddStaffActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_STAFF);
        }
        return true;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD_STAFF  && resultCode == Activity.RESULT_OK){

        }
    }

}
