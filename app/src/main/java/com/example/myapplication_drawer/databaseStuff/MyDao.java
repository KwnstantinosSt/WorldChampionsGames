package com.example.myapplication_drawer.databaseStuff;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication_drawer.classes.Athlete;
import com.example.myapplication_drawer.classes.Sport;
import com.example.myapplication_drawer.classes.SportTeamJoin;
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

    @Query("select distinct sport_name, t.team_name from sport s inner join team t  on (s.sport_id = t.team_sport_id) where sport_kind = \"omadiko\" or sport_kind = \"Omadiko\" or sport_kind = \"Ομαδικό\" or sport_kind = \"ΟΜΑΔΙΚΟ\"")
    public List<SportTeamJoin> getSportsOmadika();

    @Query("select distinct sport_name from sport s inner join team t  on (s.sport_id = t.team_sport_id) where sport_kind = \"omadiko\" or sport_kind = \"Omadiko\" or sport_kind = \"Ομαδικό\" or sport_kind = \"ΟΜΑΔΙΚΟ\"")
    public List<String> getSportsNameJoinTeam();

    @Query("select distinct t.team_name from sport s inner join team t  on (s.sport_id = t.team_sport_id) where sport_kind = \"omadiko\" or sport_kind = \"Omadiko\" or sport_kind = \"Ομαδικό\" or sport_kind = \"ΟΜΑΔΙΚΟ\"")
    public List<String> getTeamNamejoinSport();

    @Query("select distinct s.sport_name from sport s inner join athlete a  on (s.sport_id = a.athlete_sport_id) where sport_kind = \"atomiko\" or sport_kind = \"Atomiko\" or sport_kind = \"Ατομικό\" or sport_kind = \"ΑΤΟΜΙΚΟ\"")
    public List<String> getSportsjoinAtleteNames();

    @Query("select distinct a.athlete_name from sport s inner join athlete a  on (s.sport_id = a.athlete_sport_id) where (sport_kind = \"atomiko\" or sport_kind = \"Atomiko\" or sport_kind = \"Ατομικό\" or sport_kind = \"ΑΤΟΜΙΚΟ\") and sport_name= :name")
    public List<String> getAthletejoinSportNames(String name);

}
