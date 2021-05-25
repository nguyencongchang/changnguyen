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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.restaurant.Adapter.AdapterDisplayMenu;
import com.example.restaurant.AddDishActivity;
import com.example.restaurant.DAO.CategoryDAO;
import com.example.restaurant.DTO.CategoryDTO;
import com.example.restaurant.HomeActivity;
import com.example.restaurant.R;

import java.util.List;

public class DisplayMenuFragment extends Fragment {
    public static int REQUEST_CODE_ADD_DISH = 20001;
    private GridView gridView;
    private CategoryDAO categoryDAO;
    private List<CategoryDTO> listCategory;
    private FragmentManager fragmentManager;
    private int deskId;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_display_catgory,container, false);
        setHasOptionsMenu(true);
        categoryDAO = new CategoryDAO(getActivity());
        gridView = view.findViewById(R.id.gv_display_category);
        fragmentManager = getActivity().getSupportFragmentManager();
        // lay ma ban cho goi mon
        if(getArguments()!= null){
            deskId = getArguments().getInt("desk_id");
        }

        loadCat();



        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull  Menu menu, @NonNull  MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem itemAddDish = menu.add(1,R.id.item_add_dish,1,R.string.add_dish);
        itemAddDish.setIcon(R.drawable.menu);
        itemAddDish.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.item_add_dish){
            Intent intent = new Intent(getActivity(), AddDishActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_DISH);
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD_DISH  && resultCode == Activity.RESULT_OK){
            loadCat();
        }
    }

    private  void loadCat(){
        listCategory = categoryDAO.getAllCategory();
        AdapterDisplayMenu adapterDisplayMenu = new AdapterDisplayMenu(getActivity(),R.layout.custom_layout_catgory, listCategory);
        gridView.setAdapter(adapterDisplayMenu);
        adapterDisplayMenu.notifyDataSetChanged();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                DisplayDishFragment displayDishFragment = new DisplayDishFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("cat_id", listCategory.get(i).getId());
                bundle.putInt("desk_id", deskId);
                bundle.putString("cat_name",listCategory.get(i).getName());
                displayDishFragment.setArguments(bundle);
                transaction.replace(R.id.content, displayDishFragment).addToBackStack("return");
                transaction.commit();
            }
        });
    }
}
