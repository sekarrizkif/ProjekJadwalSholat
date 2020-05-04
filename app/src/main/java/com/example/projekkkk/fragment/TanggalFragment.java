package com.example.projekkkk.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TanggalFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar kalender = Calendar.getInstance();
        int day = kalender.get(Calendar.DAY_OF_MONTH);
        int month = kalender.get(Calendar.MONTH);
        int year = kalender.get(Calendar.YEAR);
        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), day, month, year);

    }
}

