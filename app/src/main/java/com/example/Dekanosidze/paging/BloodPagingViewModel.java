package com.example.Dekanosidze.paging;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.Dekanosidze.room.BloodBank;

public class BloodPagingViewModel extends ViewModel {
    private final DataRepository mRepository;
    public static int PAGE_SIZE = 30;
    public static boolean PLACEHOLDERS = true;

    public BloodPagingViewModel(DataRepository repository) {
        mRepository = repository;
    }

    public void save(BloodBank smiley) {

    }

    public void delete(BloodBank smiley) {
        mRepository.delete(smiley);
    }

    public LiveData<PagedList<BloodBank>> getAllSmileys(){
        DataSource.Factory<Integer, BloodBank> smileys = mRepository.getSmileys();
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(PLACEHOLDERS)
                .setPageSize(PAGE_SIZE).build();
        return new LivePagedListBuilder<>(smileys,config).build();
    }
}
