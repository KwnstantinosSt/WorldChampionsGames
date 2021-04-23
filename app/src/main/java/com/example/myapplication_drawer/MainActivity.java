package com.example.myapplication_drawer;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.Toast;

import com.example.myapplication_drawer.classes.Sport;
import com.example.myapplication_drawer.databaseStuff.MyAppDatabase;
import com.example.myapplication_drawer.dialogMessages.InfoDialog;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static MyAppDatabase myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_sport, R.id.nav_athlete,R.id.nav_team,R.id.nav_games)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

       // toolbar.setBackgroundColor(R.color.black);
        AppBarLayout test = findViewById(R.id.app_bar);
        test.setBackground(new ColorDrawable(getResources().getColor(R.color.mat_black)));
        //Toast.makeText(getApplicationContext(),navigationView.getCheckedItem().toString(),Toast.LENGTH_LONG).show();
        myDB = Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"worldSportsDB").allowMainThreadQueries().build();





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_info_btn:
                showMessage();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    public void showMessage(){
      //  Toast.makeText(getApplicationContext(),"Clicked Info",Toast.LENGTH_LONG).show();
        InfoDialog dialog = new InfoDialog();
        dialog.show(getSupportFragmentManager(),"Info Dialog");
    }





}