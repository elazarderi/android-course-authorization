package com.example.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterFragment extends Fragment {

    private EditText email;
    private EditText password;
    private EditText phone;
    private EditText name;

    private FirebaseAuth mAuth;
    final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnForApp = view.findViewById(R.id.submitRegisterButton);
        btnForApp.setOnClickListener(v -> {
            email = view.findViewById(R.id.registerEmail);
            password = view.findViewById(R.id.registerPassword);
            phone = view.findViewById(R.id.registerPhone);
            name = view.findViewById(R.id.registerName);

            Person person = new Person(
                    name.getText().toString(),
                    password.getText().toString(),
                    phone.getText().toString(),
                    email.getText().toString()
            );

            register(email.getText().toString(), password.getText().toString(), person);
        });
    }

    public void register(String email, String password, Person person) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                dbRef.child("users").child(person.id).setValue(person);
                Navigation.findNavController(requireView()).navigate(R.id.action_registerFragment_to_usersFragment);
            } else {
                Toast.makeText(requireContext(), "Registration failed", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}