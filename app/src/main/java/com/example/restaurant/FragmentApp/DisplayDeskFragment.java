package com.example.restaurant.FragmentApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.restaurant.Adapter.AdapterDisplayDesk;
import com.example.restaurant.AddDeskActivity;
import com.example.restaurant.DAO.DeskDAO;
import com.example.restaurant.DTO.DeskDTO;
import com.example.restaurant.ModifyDeskAtivity;
import com.example.restaurant.R;

import java.util.List;

public class DisplayDeskFragment extends Fragment {
    public static int REQUEST_CODE_ADD = 1111;
    public static int REQUEST_CODE_MODIFY = 1112;

    GridView gvDisplayDesk;
    List<DeskDTO> listDesk;
    DeskDAO deskDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_display_desk,container,false);
        setHasOptionsMenu(true);
        gvDisplayDesk = view.findViewById(R.id.gv_display_desk);
        deskDAO = new DeskDAO(getActivity());
        loadDesh();
        registerForContextMenu(gvDisplayDesk);
        return view;
    }
    private void loadDesh(){
        listDesk = deskDAO.getAllDesk();
        AdapterDisplayDesk adapterDisplayDesk = new AdapterDisplayDesk(getActivity(), R.layout.custom_layout_desk, listDesk);
        gvDisplayDesk.setAdapter(adapterDisplayDesk);
        adapterDisplayDesk.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itemAddDesk = menu.add(1,R.id.item_add_desk,1,R.string.add_desk);
        itemAddDesk.setIcon(R.drawable.thembanan);
        itemAddDesk.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() ==R.id.item_add_desk ){
            Intent intent= new Intent(getActivity(), AddDeskActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD);
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(@NonNull  ContextMenu menu, @NonNull View v, @Nullable  ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.menu_edit_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo  menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int pos = menuInfo.position;
        int deskId = listDesk.get(pos).getId();
        if(item.getItemId()== R.id.menu_modify){

            Intent intent = new Intent(getActivity(),ModifyDeskAtivity.class);
            intent.putExtra("desk_id", deskId);
            startActivityForResult(intent, REQUEST_CODE_MODIFY);
        }
        if(item.getItemId()== R.id.menu_delete){
            boolean check =deskDAO.deleteDesk(deskId);
            if(check) loadDesh();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD){
            if(resultCode == Activity.RESULT_OK){
                assert data != null;
                boolean check = data.getBooleanExtra("result",false);
                if(check) {
                    loadDesh();
                    Toast.makeText(getActivity(),R.string.success,Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(),R.string.failed,Toast.LENGTH_SHORT).show();
                }
            }
        }
        if(requestCode == REQUEST_CODE_MODIFY){
            if(resultCode == Activity.RESULT_OK){
                loadDesh();
            }
        }
    }
}
