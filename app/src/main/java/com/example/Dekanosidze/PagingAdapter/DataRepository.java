package com.example.Dekanosidze.PagingAdapter;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.example.Dekanosidze.RoomDB.AppDatabase;
import com.example.Dekanosidze.RoomDB.BloodBank;
import com.example.Dekanosidze.RoomDB.UserDao;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataRepository {

    private final UserDao mDao;
    private final ExecutorService mIoExecutor;
    private static volatile DataRepository sInstance = null;
    private static final String TAG = "Repo";

    public static DataRepository getInstance(Context context) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    AppDatabase database = AppDatabase.getInstance(context);
                    sInstance = new DataRepository(database.userDao(), Executors.newSingleThreadExecutor());
                }
            }
        }
        return sInstance;
    }

    public DataRepository(UserDao dao, ExecutorService executor) {
        mIoExecutor = executor;
        mDao = dao;
    }

    public DataSource.Factory<Integer, BloodBank> getSmileys() {
        return mDao.getAll();
    }

    public LiveData<List<BloodBank>> getRandomSmileys(int limit) {
        try {
            return mIoExecutor.submit(new Callable<LiveData<List<BloodBank>>>() {
                @Override
                public LiveData<List<BloodBank>> call() throws Exception {
                    LiveData<List<BloodBank>> data = mDao.getRandom(limit);
                    return data;
                }
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(BloodBank smiley) {
        mIoExecutor.execute(() -> mDao.delete(smiley));
    }

    public void save(BloodBank smiley){

        mIoExecutor.execute(() -> mDao.insert(smiley));
    }

    public BloodBank getSmiley() {
        try {
            return mIoExecutor.submit(mDao::getSmiley).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

}
