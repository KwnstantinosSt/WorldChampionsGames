package com.example.myapplication_drawer.ui.home;

import android.widget.Toolbar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.myapplication_drawer.R;
import com.google.android.material.appbar.AppBarLayout;

import static com.example.myapplication_drawer.R.id.toolbar;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Καλώς Ήρθατε.");


    }

    public LiveData<String> getText() {
        return mText;
    }
}