package com.example.Dekanosidze.paging;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.lang.reflect.InvocationTargetException;

public class BloodViewModelFactory implements ViewModelProvider.Factory{
    private final DataRepository mRepository;

    public static BloodViewModelFactory createFactory(Activity activity) {
        Application application = activity.getApplication();
        if (application == null) {
            throw new IllegalStateException("Not yet attached to Application");
        }
        return new BloodViewModelFactory(DataRepository.getInstance(application));
    }

    private BloodViewModelFactory(DataRepository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(DataRepository.class).newInstance(mRepository);
        } catch (InstantiationException | IllegalAccessException |
                NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        }
    }
}
