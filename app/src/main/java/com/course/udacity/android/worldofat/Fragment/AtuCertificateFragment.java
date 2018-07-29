package com.course.udacity.android.worldofat.Fragment;

import android.app.SearchManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.course.udacity.android.worldofat.Jobs;
import com.course.udacity.android.worldofat.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import static com.course.udacity.android.worldofat.R.id.jobname_tv;

//import com.course.udacity.android.worldofat.CertificateAdapter;

public class AtuCertificateFragment extends BaseContainerFragment{
    private static final String TAG = AtuCertificateFragment.class.getSimpleName() ;

    private OnFragmentInteractionListener mListener;

    private RecyclerView mRecyclerView;

    private static FirebaseRecyclerAdapter mCertificateAdapter;

    private static String searchString;

    private TextView mEmptyView;

    public static final String FIREBASE_PRIMARY_CHILD_NODE = "jobsearch";

    private SearchView mSearchView;

    private static ActionMode.Callback2 mActionModeCb = null;

    public static interface ClickListener {

        public void onLongClick(View view, int pos);
    }


    public AtuCertificateFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AtuCertificateFragment newInstance(String searchjobs) {

        Bundle args = new Bundle();
        args.putString("JobSearch", searchjobs);
        AtuCertificateFragment fragment = new AtuCertificateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = 28)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        DatabaseReference mRef = FirebaseDatabase.getInstance()
                .getReference("wordlofat-35400").child(FIREBASE_PRIMARY_CHILD_NODE);

        mRef.push().setValue(new Jobs("Occupational Therapist", 3,"NewYork"));

        // Inflate the layout for this fragment
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_atu_certificate,
                container, false);
        return v;
    }
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEmptyView = view.findViewById(R.id.empty_tv);

        mRecyclerView = view.findViewById(R.id.certificate_rv);

        mEmptyView.setVisibility(View.INVISIBLE);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        registerForContextMenu(mRecyclerView);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(
                getContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onLongClick(View view, int pos) {

                mActionModeCb = new ActionMode.Callback2() {
                    @Override
                    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                        MenuInflater menuInflater = actionMode.getMenuInflater();
                        menuInflater.inflate(R.menu.context_menu, menu);
                        return true;

                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                        return false;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.cut_copy_menu:
//                   // What happens when menu option is chosen inside the floating menu
                                Toast.makeText(getContext(), "ROX", Toast.LENGTH_LONG).show();
                                actionMode.finish();
                                return true;

                            case R.id.share_to_keep:
                                Toast.makeText(getContext(), "Open Keep", Toast.LENGTH_SHORT).show();
                                actionMode.finish();
                                return true;
                            default:
                                return false;
                        }

                    }
                    @Override
                    public void onDestroyActionMode(ActionMode actionMode) {
                        actionMode = null;
                    }
                };

                TextView mJobName = mRecyclerView.findViewHolderForAdapterPosition(pos).itemView.findViewById(R.id.jobname_tv);

                mJobName.setTextIsSelectable(true);

                mJobName.setCustomSelectionActionModeCallback(mActionModeCb);

                mJobName.setSelected(true);

        }
        }));

        Query iquery = FirebaseDatabase.getInstance().getReference("wordlofat-35400").child(FIREBASE_PRIMARY_CHILD_NODE)
                .orderByChild("jobName").equalTo(searchString);

        FirebaseRecyclerOptions<Jobs> options = new FirebaseRecyclerOptions.Builder<Jobs>()
                .setQuery(iquery, Jobs.class).build();

        mCertificateAdapter = new FirebaseRecyclerAdapter<Jobs, CertificateViewHolder>(options) {

            @NonNull
            @Override
            public CertificateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(getContext()).inflate(R.layout.certifcate_adapter, parent, false);
                return new CertificateViewHolder(v);
            }

            @Override
            protected void onBindViewHolder(@NonNull CertificateViewHolder holder, int position, @NonNull Jobs model) {
                holder.mJobName.setText(model.getJobName());
                holder.mJobLoction.setText(model.getLocation());

            }

        };

        mRecyclerView.setAdapter(mCertificateAdapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_detail, menu);

        SearchManager searchManager = (SearchManager)getActivity().getSystemService(Context.SEARCH_SERVICE);

        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        mSearchView.setQueryHint("Search Jobs");

        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.action_search):
                mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        searchString = query;
                        Toast.makeText(getContext(), searchString, Toast.LENGTH_LONG).show();
                        if (query.length() > 0) {
                            assert getFragmentManager() != null;
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            Fragment newFragment = new AtuCertificateFragment();
                            Bundle args = new Bundle();
                            args.putString("query_string", searchString);
                            newFragment.setArguments(args);

                            transaction.replace(R.id.fragment_atu_certificate, newFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });

            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{
        private ClickListener mClickListener;
        private GestureDetector mGestureDetector;
        public RecyclerTouchListener(Context context, final RecyclerView rv, final ClickListener clickListener){
            this.mClickListener = clickListener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = rv.findChildViewUnder(e.getX(), e.getY());
                    if(child!=null && clickListener!=null){
                        clickListener.onLongClick(child,
                                rv.getChildAdapterPosition(child));
                    }
                }
            });
        }


        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && mClickListener != null && mGestureDetector.onTouchEvent(e)) {
                mClickListener.onLongClick(child,
                        rv.getChildAdapterPosition(child));
            }
                return false;

        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
    @Override
    public void onStart() {
        super.onStart();
        mCertificateAdapter.startListening();

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
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        mCertificateAdapter.stopListening();

    }

    public class CertificateViewHolder extends RecyclerView.ViewHolder{

        public TextView mJobName;
        public TextView mJobLoction;

        public CertificateViewHolder(View itemView) {
            super(itemView);
            mJobName = itemView.findViewById(jobname_tv);
            mJobLoction = itemView.findViewById(R.id.jobloc_tv);
            }
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

    }
}
