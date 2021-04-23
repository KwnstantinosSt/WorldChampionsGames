package com.example.myapplication_drawer.ui.athlete;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AthleteViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AthleteViewModel() {
      //  mText = new MutableLiveData<>();
       // mText.setValue("This is athlete");
    }

    public LiveData<String> getText() {
        return mText;
    }
}