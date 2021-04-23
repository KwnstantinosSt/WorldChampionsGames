package com.example.myapplication_drawer.ui.games;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication_drawer.R;

public class GamesViewModel extends ViewModel {

    private MutableLiveData<String> mText;


    public GamesViewModel() {
     //   mText = new MutableLiveData<>();
    //    mText.setValue("This is Games");

    }

    public LiveData<String> getText() {
        return mText;
    }



}