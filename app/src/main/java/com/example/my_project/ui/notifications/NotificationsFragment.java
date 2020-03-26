package com.example.my_project.ui.notifications;

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

public class NotificationsFragment extends Fragment {

    public static final String EXTRA_MESSAGE = "com.example.test_retrofit.Select_city";

    private NotificationsViewModel notificationsViewModel;
    private Button send;
    private EditText lat;
    private EditText lon;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        //final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        send = root.findViewById(R.id.button_send_coord);
        lon = root.findViewById(R.id.text_lon);
        lat=root.findViewById(R.id.text_lat);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String test = lon.getText().toString()+","+lat.getText().toString();
                sendMessage(test);
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