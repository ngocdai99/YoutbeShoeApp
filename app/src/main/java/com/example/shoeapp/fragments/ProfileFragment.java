package com.example.shoeapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shoeapp.LoginActivity;
import com.example.shoeapp.R;
import com.example.shoeapp.RegisterActivity;


public class ProfileFragment extends Fragment {

    View view;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);

        LinearLayout layoutLogout = view.findViewById(R.id.layoutLogout);

        layoutLogout.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), LoginActivity.class);
            requireActivity().startActivity(intent);
            requireActivity().finish();
        });
        return view;
    }
}

