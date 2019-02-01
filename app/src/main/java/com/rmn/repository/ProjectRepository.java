package com.rmn.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.rmn.model.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProjectRepository {

    private GitHubService gitHubService;
    private static ProjectRepository projectRepository;

    private ProjectRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GitHubService.HTTPS_API_GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gitHubService = retrofit.create(GitHubService.class);
    }

    public synchronized static ProjectRepository getInstance() {
        if (projectRepository == null) {
            projectRepository = new ProjectRepository();
        }
        return projectRepository;
    }

    public LiveData<List<Project>> getProjectList(String userId) {

        final MutableLiveData<List<Project>> projectList = new MutableLiveData<>();

        gitHubService.getProjectList(userId).enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                projectList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                projectList.setValue(null);
            }

        });

        return projectList;
    }

    public LiveData<Project> getProjectDetails(String userId, String repoName) {

        final MutableLiveData<Project> projectDetail = new MutableLiveData<>();

        gitHubService.getProjectDetails(userId, repoName).enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                projectDetail.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                projectDetail.setValue(null);
            }
        });

        return projectDetail;

    }


}
