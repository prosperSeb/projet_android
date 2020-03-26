package com.example.my_project.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.my_project.MainActivity;
import com.example.my_project.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private TextView dateTimeDisplay;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    private ImageView image;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        image = root.findViewById(R.id.imageView_main);
        dateTimeDisplay = root.findViewById(R.id.date);

        TextView navUsername = root.findViewById(R.id.textView_main);
        navUsername.setText("Welcome back "+MainActivity.Name);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date = dateFormat.format(calendar.getTime());
        dateTimeDisplay.setText(date);

        //load_image("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.icone-png.com%2Ftheme-stylish-meteo.php&psig=AOvVaw3pYMzMZJUptyiWEmWkGUAn&ust=1585319632657000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCPjVqv-tuOgCFQAAAAAdAAAAABAD");
        return root;
    }

    private void load_image(String url_image){

        Picasso.get().load(url_image)
                .resize(250,250)
                .into(image);
    }
}