package com.rmn.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.rmn.model.Project;
import com.rmn.repository.ProjectRepository;

import java.util.List;

public class ProjectListViewModel extends AndroidViewModel {

    private LiveData<List<Project>> projectListObservable;

    public ProjectListViewModel(@NonNull Application application) {
        super(application);

        projectListObservable = ProjectRepository.getInstance().getProjectList("Google");

    }

    public LiveData<List<Project>> getProjectListObservable() {
        return projectListObservable;
    }
}
