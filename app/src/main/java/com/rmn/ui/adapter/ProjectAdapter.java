package com.rmn.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rmn.R;
import com.rmn.model.Project;
import com.rmn.ui.MainActivity;
import com.rmn.ui.ProjectFragment;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectHolder> {

    List<Project> projects;
    Context context;
    ProjectClickListener projectClickListener;

    public ProjectAdapter(Context context, ProjectClickListener projectClickListener) {
        this.context = context;
        this.projectClickListener = projectClickListener;
    }

    @NonNull
    @Override
    public ProjectHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ProjectHolder(LayoutInflater.from(context).inflate(R.layout.project_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectHolder projectHolder, int i) {
        final Project project = projects.get(i);
        projectHolder.nameView.setText(project.name);
        projectHolder.projectWatchersView.setText(String.valueOf(project.watchers_count));
        projectHolder.languagesView.setText(project.language);

        projectHolder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projectClickListener.onProjectClicked(project);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (projects == null)
            return 0;

        return projects.size();
    }


    public void setProjects(List<Project> projects) {
        this.projects = projects;
        notifyDataSetChanged();
    }


    public interface ProjectClickListener{
        void onProjectClicked(Project project);
    }

    public class ProjectHolder extends RecyclerView.ViewHolder {

        TextView nameView, languagesView, projectWatchersView;
        View parentView;

        public ProjectHolder(@NonNull View itemView) {
            super(itemView);
            parentView = itemView.findViewById(R.id.parent);
            nameView = itemView.findViewById(R.id.name);
            languagesView = itemView.findViewById(R.id.languages);
            projectWatchersView = itemView.findViewById(R.id.project_watchers);
        }
    }
}
