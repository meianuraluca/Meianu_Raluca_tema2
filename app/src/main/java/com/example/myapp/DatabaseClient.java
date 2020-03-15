package com.example.myapp;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {
    private Context myContext;
    private static DatabaseClient myInstance;
    private AppDatabase appDatabase;

    private DatabaseClient(Context myContext){
        this.myContext = myContext;
        appDatabase = Room.databaseBuilder(myContext,AppDatabase.class,"MyUsers").build();

    }

    public static synchronized DatabaseClient getInstance(Context myContext){
        if(myInstance == null){
            myInstance = new DatabaseClient(myContext);
        }
        return myInstance;
    }

    public AppDatabase getAppDatabase(){
        return appDatabase;
    }
}
