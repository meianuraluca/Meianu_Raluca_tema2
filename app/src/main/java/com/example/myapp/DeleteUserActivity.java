package com.example.myapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteUserActivity extends AppCompatActivity {
    private EditText editTextName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_user);

        editTextName = findViewById(R.id.editTextRemoveName);
        findViewById(R.id.button_remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeUser();
            }
        });
    }
    private void removeUser(){
        final String name = editTextName.getText().toString().trim();

        if(name.isEmpty()){
            editTextName.setError("Name is required");
            editTextName.requestFocus();
            return;
        }

        class DeleteUser extends AsyncTask<Void,Void,Void>{
            Boolean found = false;
            @Override
            protected Void doInBackground(Void...voids){
                User user = DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase().userDao().findByName(name);
                System.out.println(user);
                if(user!=null) {
                    this.found = true;
                    DatabaseClient.getInstance(getApplicationContext())
                            .getAppDatabase().userDao().deleteByName(name);
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                if(this.found == true) {
                    Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(DeleteUserActivity.this, MainActivity.class));
                }
                else {
                    editTextName.setText("");
                    Toast.makeText(getApplicationContext(), "Don't found", Toast.LENGTH_LONG).show();
                }

            }
        }
        DeleteUser deleteUser = new DeleteUser();
        deleteUser.execute();
    }
}
