package com.example.shoeapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shoeapp.LoginActivity;
import com.example.shoeapp.R;
import com.example.shoeapp.daos.UserDAO;
import com.example.shoeapp.models.User;


public class ProfileFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);

        LinearLayout layoutLogout = view.findViewById(R.id.layoutLogout);
        TextView txtFullname = view.findViewById(R.id.txtFullname);
        TextView txtUsername = view.findViewById(R.id.txtUsername);

        setInfor(txtFullname, txtUsername);
        layoutLogout.setOnClickListener(v -> {
           onLogout();
        });
        return view;
    }

    private void setInfor(TextView textViewName, TextView textViewEmail) {
        UserDAO userDAO = new UserDAO(requireActivity());
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);
        if (username != null) {
            User user = userDAO.getUser(username);
            textViewName.setText(user.getFullname());
            textViewEmail.setText(user.getUsername());
        }
    }

    private void onLogout(){
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("username");
        editor.apply();

        Intent intent = new Intent(requireActivity(), LoginActivity.class);
        requireActivity().startActivity(intent);
        requireActivity().finish();
    }
}

