//package com.course.udacity.android.worldofat;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//
//
//public class CertificateAdapter extends RecyclerView.Adapter<CertificateAdapter.CertificateViewHolder> {
//    private Context mContext;
//    private static ArrayList<com.course.udacity.android.worldofat.Jobs> mJobsArrayList = new ArrayList<>();
//    public static String searchString;
//    private static final String FIREBASE_PRIMARY_CHILD_NODE= "jobsearch";
//    private static com.course.udacity.android.worldofat.Jobs j;
//
//    public CertificateAdapter(Context context){
//        mContext = context;
////        searchString = query;
//    }
//    public CertificateAdapter(Context context, ArrayList<com.course.udacity.android.worldofat.Jobs> queryArrayList){
//        mContext = context;
//        mJobsArrayList = queryArrayList;
//    }
//    @NonNull
//    @Override
//    public CertificateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        View v = inflater.inflate(R.layout.certifcate_adapter, parent, false);
//        return new CertificateViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CertificateViewHolder holder, int position) {
//
//        com.course.udacity.android.worldofat.Jobs j = mJobsArrayList.get(position);
//        holder.mJobName.setText(j.getJobName());
//        holder.mJobLoction.setText(j.getLocation());
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 5;
//    }
//
//    public class CertificateViewHolder extends RecyclerView.ViewHolder{
//
//        private TextView mJobName;
//        private TextView mJobLoction;
//
//        public CertificateViewHolder(View itemView) {
//            super(itemView);
//            mJobName = itemView.findViewById(R.id.jobname_tv);
//            mJobLoction = itemView.findViewById(R.id.jobloc_tv);
//        }
//    }
//
//    public static ArrayList<com.course.udacity.android.worldofat.Jobs> queryJob(String s) {
//
//        final ArrayList<com.course.udacity.android.worldofat.Jobs> local = new ArrayList<>();
//
//        Query query = FirebaseDatabase.getInstance().getReference("wordlofat-35400").child(FIREBASE_PRIMARY_CHILD_NODE)
//                .orderByChild("jobName").equalTo(s);
//
//
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                if (dataSnapshot.exists()) {
//                    for (DataSnapshot jobSnapshot : dataSnapshot.getChildren()) {
//
//
//                        j =(jobSnapshot.getValue(com.course.udacity.android.worldofat.Jobs.class));
//
//                        }
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//
//        });
//        local.add(j);
//        return local;
//
//    }
//
//    public void setQueryData(ArrayList<com.course.udacity.android.worldofat.Jobs> queryJobs){
//        mJobsArrayList = queryJobs;
//        notifyDataSetChanged();
//    }
//
//
//
//}
