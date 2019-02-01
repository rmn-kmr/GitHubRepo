package com.rmn.ui;

import android.arch.lifecycle.Lifecycle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rmn.R;
import com.rmn.model.Project;

public class MainActivity extends AppCompatActivity implements ProjectListFragment.OnProjectListInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            ProjectListFragment fragment = ProjectListFragment.newInstance("s", "s");
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }
    }

    public void show(Project project){
        ProjectFragment projectFragment = ProjectFragment.forProject(project.name);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("project")
                .replace(R.id.container, projectFragment, null)
                .commit();
    }

    @Override
    public void onProjectClicked(Project project) {
        show(project);
    }

}
