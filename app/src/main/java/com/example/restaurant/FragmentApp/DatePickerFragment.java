package com.example.restaurant.FragmentApp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import com.example.restaurant.R;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calender = Calendar.getInstance();
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH);
        int day = calender.get(Calendar.DAY_OF_MONTH);
        return  new DatePickerDialog(getActivity(), this, year,month,day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        EditText dateEdit = getActivity().findViewById(R.id.ed_date_of_birth_regist);
        String dateStr = ""+dayOfMonth+"/"+(month+1)+"/"+year;
        dateEdit.setText(dateStr);
    }
}
