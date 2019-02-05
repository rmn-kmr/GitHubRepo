package com.rmn.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rmn.R;
import com.rmn.model.Project;
import com.rmn.viewModel.ProjectDetailViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProjectFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProjectFragment#forProject} factory method to
 * create an instance of this fragment.
 */
public class ProjectFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_REPO_ID = "paramRepoId";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mRepoId;
    private String mParam2;

    TextView loadingMsgView, nameView,
            descView, lanView, watchersView,
            openIssuesView, createdView, updatedView,
            cloneURLView;
    View parentView;
    private OnFragmentInteractionListener mListener;
    Project mProject;

    public ProjectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param repoId Parameter 1.
     * @return A new instance of fragment ProjectFragment.
     */
    public static ProjectFragment forProject(String repoId) {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_REPO_ID, repoId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
                mRepoId = getArguments().getString(ARG_REPO_ID);
         }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_project, container, false);
        parentView = v.findViewById(R.id.parent);
        loadingMsgView = v.findViewById(R.id.loading_project);
        nameView = v.findViewById(R.id.name);
        descView = v.findViewById(R.id.project_desc);
        lanView = v.findViewById(R.id.languages);
        watchersView = v.findViewById(R.id.project_watchers);
        openIssuesView = v.findViewById(R.id.project_open_issues);
        createdView = v.findViewById(R.id.project_created_at);
        updatedView = v.findViewById(R.id.project_updated_at);
        cloneURLView = v.findViewById(R.id.clone_url);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ProjectDetailViewModel.ProjectDetailFactoryMethod projectDetailFactoryMethod = new ProjectDetailViewModel.ProjectDetailFactoryMethod(getActivity().getApplication(), mRepoId);

        ViewModelProviders.of(this, projectDetailFactoryMethod)
                .get(ProjectDetailViewModel.class)
                .getProjectObservable()
                .observe(this, new Observer<Project>() {

            @Override
            public void onChanged(@Nullable Project project) {
                isSetData(project);
            }

        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void isSetData(Project project){
        if (project == null){
            loadingMsgView.setVisibility(View.VISIBLE);
            parentView.setVisibility(View.GONE);
        }else {
            loadingMsgView.setVisibility(View.GONE);
            parentView.setVisibility(View.VISIBLE);
            nameView.setText(project.name);
            descView.setText(project.description);
            lanView.setText(project.language);
            watchersView.setText(String.valueOf(project.watchers));
            openIssuesView.setText(String.valueOf(project.open_issues));
            createdView.setText(project.created_at.toString());
            updatedView.setText(project.updated_at.toString());
            cloneURLView.setText(project.clone_url);
        }
    }

    public interface OnFragmentInteractionListener {
         void onFragmentInteraction(Uri uri);
    }
}
