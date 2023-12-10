package com.example.messenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatActivity extends AppCompatActivity {

    private TextView textViewTitle;
    private View onlineStatus;
    private RecyclerView recyclerViewMessages;
    private EditText editTextMessage;
    private ImageView imageViewSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initViews();
    }


    private void initViews() {
        textViewTitle = findViewById(R.id.textViewTitle);
        onlineStatus = findViewById(R.id.onlineStatus);
        recyclerViewMessages = findViewById(R.id.recyclerViewMessages);
        editTextMessage = findViewById(R.id.editTextMessage);
        imageViewSend = findViewById(R.id.imageViewSend);
    }
}