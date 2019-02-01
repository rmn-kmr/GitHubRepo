package com.rmn.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rmn.R;
import com.rmn.model.Project;

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
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView loadingMsgView, nameView,
            descView, lanView, watchersView,
            openIssuesView, createdView, updatedView,
            cloneURLView;
    View parentView;
    private OnFragmentInteractionListener mListener;

    public ProjectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param Parameter 1.
     * @return A new instance of fragment ProjectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProjectFragment forProject(String param) {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            //  mParam2 = getArguments().getString(ARG_PARAM2);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
