package com.example.myapplication_drawer.databaseStuff;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication_drawer.classes.Athlete;
import com.example.myapplication_drawer.classes.Sport;
import com.example.myapplication_drawer.classes.Team;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MyDao {

    @Insert
    public void insertSport(Sport sport);

    @Insert
    public void insertAthlete(Athlete athlete);

    @Insert
    public void insertTeam(Team team);

    @Delete
    public void deleteSport(Sport sport);

    @Delete
    public void deleteAthlete(Athlete athlete);

    @Delete
    public void deleteTeam(Team team);

    @Update
    public void updateSport(Sport sport);

    @Update
    public void updateAthlete(Athlete athlete);

    @Update
    public void updateTeam(Team team);

    @Query("select * from sport")
    public List<Sport> getSports();

    @Query("select * from athlete")
    public List<Athlete> getAthlets();

    @Query("select * from team")
    public List<Team> getTeams();

}
