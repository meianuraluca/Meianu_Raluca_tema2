package com.example.myapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddUserActivity extends AppCompatActivity {
    private EditText editTextName,editTextMark;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        editTextName = findViewById(R.id.editTextName);
        editTextMark = findViewById(R.id.editTextMark);

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();
            }
        });
    }
    private void saveUser(){
        final String sName = editTextName.getText().toString().trim();
        final String sMark = editTextMark.getText().toString().trim();

        if(sName.isEmpty()){
            editTextName.setError("Name is required");
            editTextName.requestFocus();
            return;
        }

        if(sMark.isEmpty()){
            editTextMark.setError("Mark is required");
            editTextMark.requestFocus();
            return;
        }

        class SaveUser extends AsyncTask<Void,Void,Void>{
            @Override
            protected Void doInBackground(Void... voids) {
                User user = new User();
                user.setName(sName);
                user.setMark(Integer.parseInt(sMark));
                DatabaseClient.getInstance(getApplicationContext())
                        .getAppDatabase().userDao().insert(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
            }
        }

        SaveUser user = new SaveUser();
        user.execute();
    }
}
