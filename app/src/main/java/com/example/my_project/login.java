package com.example.my_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.test_retrofit.Select_city";
    private EditText Name;
    private EditText Password;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Name = findViewById(R.id.name_login);
        Password = findViewById(R.id.password_login);
        button = findViewById(R.id.button_send);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Name.getText().toString().equals("Prosper") && Password.getText().toString().equals("1234"))
                {
                    sendMessage();
                }
            }
        });

    }
    public void sendMessage() {
        Intent intent = new Intent(this, MainActivity.class);
        String message = Name.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, "Prosper");
        startActivity(intent);
    }
}
