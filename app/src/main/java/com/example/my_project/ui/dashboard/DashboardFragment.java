package com.example.my_project.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.my_project.R;

public class DashboardFragment extends Fragment {

    public static final String EXTRA_MESSAGE = "com.example.test_retrofit.Select_city";

    private DashboardViewModel dashboardViewModel;

    private TextView text;
    private Button button;
    private EditText edit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        //final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        //text = root.findViewById(R.id.textView_dash);
        edit = root.findViewById(R.id.editText_dash);
        button = root.findViewById(R.id.send);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(edit.getText().toString());
            }
        });
        return root;
    }


    public void sendMessage(String city_name) {
        Intent intent = new Intent(getActivity(),com.example.my_project.display.display_city.class);
        intent.putExtra(EXTRA_MESSAGE, city_name);
        startActivity(intent);
    }
}