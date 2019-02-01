package com.rmn.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rmn.R;
import com.rmn.model.Project;
import com.rmn.ui.adapter.ProjectAdapter;
import com.rmn.viewModel.ProjectListViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnProjectListInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProjectListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjectListFragment extends Fragment implements ProjectAdapter.ProjectClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private ProjectAdapter projectAdapter;
    private OnProjectListInteractionListener mListener;

    public ProjectListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProjectListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProjectListFragment newInstance(String param1, String param2) {
        ProjectListFragment fragment = new ProjectListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_project_list, container, false);
        recyclerView = v.findViewById(R.id.recycle);
        projectAdapter = new ProjectAdapter(getContext(), this);
        recyclerView.setAdapter(projectAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewModelProviders
                .of(this)
                .get(ProjectListViewModel.class)
                .getProjectListObservable()
                .observe(this, new Observer<List<Project>>() {
                    @Override
                    public void onChanged(@Nullable List<Project> projects) {
                        projectAdapter.setProjects(projects);
                    }
                });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnProjectListInteractionListener) {
            mListener = (OnProjectListInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnProjectListInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public void showDetail(Project project){

    }

    @Override
    public void onProjectClicked(Project project) {
        mListener.onProjectClicked(project);
    }

    public interface OnProjectListInteractionListener {
         void onProjectClicked(Project project);
    }
}
