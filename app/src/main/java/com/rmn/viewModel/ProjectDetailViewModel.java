package com.rmn.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.rmn.model.Project;
import com.rmn.repository.ProjectRepository;

public class ProjectDetailViewModel extends AndroidViewModel{

    LiveData<Project> projectObservable;
    private final String projectId;

    public ProjectDetailViewModel(@NonNull Application application,@NonNull String projectId) {
        super(application);
        this.projectId = projectId;
        projectObservable = ProjectRepository.getInstance().getProjectDetails("Google", projectId);
    }

    public LiveData<Project> getProjectObservable(){
        return projectObservable;
    }

}
