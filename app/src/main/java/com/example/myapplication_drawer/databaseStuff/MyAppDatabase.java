package com.example.myapplication_drawer.databaseStuff;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication_drawer.classes.Athlete;
import com.example.myapplication_drawer.classes.Sport;
import com.example.myapplication_drawer.classes.Team;

@Database(entities = {Athlete.class, Sport.class, Team.class}, version = 1)
public abstract class MyAppDatabase extends RoomDatabase {
        public abstract MyDao mydao();
}
