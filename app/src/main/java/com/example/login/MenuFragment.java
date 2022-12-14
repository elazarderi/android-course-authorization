package com.example.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        Button buttonLogin = view.findViewById(R.id.loginButton);
        buttonLogin.setOnClickListener(view1 ->
                Navigation.findNavController(view1).navigate(R.id.action_menuFragment_to_loginFragment));

        Button buttonRegister = view.findViewById(R.id.registerButton);
        buttonRegister.setOnClickListener(view12 ->
                Navigation.findNavController(view12).navigate(R.id.action_menuFragment_to_registerFragment));

        return view;
    }
}