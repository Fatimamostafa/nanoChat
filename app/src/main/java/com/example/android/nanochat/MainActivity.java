package com.example.android.nanochat;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;

import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Firebase ref;
    ListView messagesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSend = (Button) findViewById(R.id.buttonSend);
        final EditText textMessage = (EditText) findViewById(R.id.etMessage);
        messagesList = (ListView) findViewById(R.id.messagesList);

        Firebase.setAndroidContext(this);
        ref = new Firebase("https://nanochat-cc415.firebaseio.com/");

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatMessage chat = new ChatMessage("Fatima",textMessage.getText().toString());
                ref.push().setValue(chat);
                textMessage.setText("");
            }
        });

        Query recent = ref.limitToLast(3);

        FirebaseListAdapter<ChatMessage> adapter = new FirebaseListAdapter<ChatMessage>(
                this, ChatMessage.class, android.R.layout.two_line_list_item, recent
                ) {
            @Override
            protected void populateView(View view, ChatMessage chat) {
                ((TextView)view.findViewById(android.R.id.text1)).setText(chat.getName());
                ((TextView)view.findViewById(android.R.id.text2)).setText(chat.getMessage());
            }
        };



        messagesList.setAdapter(adapter);



    }
}
