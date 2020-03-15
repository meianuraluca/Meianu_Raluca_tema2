package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    private FloatingActionButton floatingRemoveButton;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview_users);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        floatingActionButton = findViewById(R.id.floating_button_add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_add = new Intent(MainActivity.this,AddUserActivity.class);
                startActivity(intent_add);
            }
        });

        floatingRemoveButton = findViewById(R.id.floating_button_remove);
        floatingRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_remove = new Intent(MainActivity.this, DeleteUserActivity.class);
                startActivity(intent_remove);
            }
        });
        getUsers();
    }

    private void getUsers(){
        class GetUsers extends AsyncTask<Void,Void, List<User>>{
            @Override
            protected List<User> doInBackground(Void...voids){
                List<User> userList = DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase().userDao().getAll();
                return userList;
            }
            @Override
            protected void onPostExecute(List<User> users){
                super.onPostExecute(users);
                UsersAdapter adapter = new UsersAdapter(MainActivity.this,users);
                recyclerView.setAdapter(adapter);
            }
        }
        GetUsers users = new GetUsers();
        users.execute();
    }
}
