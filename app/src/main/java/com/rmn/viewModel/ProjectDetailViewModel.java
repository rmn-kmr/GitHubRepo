package com.rmn.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
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

    public static class ProjectDetailFactoryMethod implements ViewModelProvider.Factory {

        private Application mApplication;
        private String mRepoId;

        public ProjectDetailFactoryMethod(Application application, String repoId){
            mApplication = application;
            mRepoId = repoId;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ProjectDetailViewModel(mApplication, mRepoId);
        }
    }

}
