package com.ranamulesoftapi.bilal.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ranamulesoftapi.bilal.models.MyDataList;
import com.ranamulesoftapi.bilal.repositories.ApiRepository;

public class SearchViewModel extends AndroidViewModel {
    private ApiRepository apiRepository;
    private LiveData<MyDataList> apiResponseLiveData;

    public SearchViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        apiRepository = new ApiRepository();
        apiResponseLiveData = apiRepository.getApiResponseLiveData();
    }

    public void callApi(String client_id, String client_secret) {
        apiRepository.ApiCall(client_id, client_secret);
    }

    public LiveData<MyDataList> getApiResponseLiveData() {
        return apiResponseLiveData;
    }
}
