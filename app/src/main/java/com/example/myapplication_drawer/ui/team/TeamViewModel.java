package com.example.myapplication_drawer.ui.team;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TeamViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TeamViewModel() {
     //   mText = new MutableLiveData<>();
    //    mText.setValue("This is team");
    }

    public LiveData<String> getText() {
        return mText;
    }
}