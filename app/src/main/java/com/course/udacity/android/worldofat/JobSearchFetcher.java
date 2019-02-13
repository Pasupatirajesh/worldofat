//package com.course.udacity.android.worldofat;
//
//import android.content.Context;
//import android.widget.Toast;
//
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//
//public class JobSearchFetcher {
//
//    public static final String ACTION_JOB_SEARCH_FETCHER = "jobsearchfetcher";
//    private static FirebaseRecyclerAdapter mCertificateAdapter;
//
//    public static void executeAction(Context context, String action) {
//        if (ACTION_JOB_SEARCH_FETCHER.equals(action)) {
//            fetchJobs(context);
//        }
//    }
//
//    private static void fetchJobs(Context context) {
//
//        Toast.makeText(context, "FirebaseJobDispatcher", Toast.LENGTH_LONG).show();
//        // Shd contain logic to fetch json results from Firebase
////        Query iquery = FirebaseDatabase.getInstance().getReference("wordlofat-35400").child(FIREBASE_PRIMARY_CHILD_NODE)
////                .orderByChild("jobName").equalTo("Shop Assistant").limitToFirst(7);
////
////        if (iquery != null) {
////
////            FirebaseRecyclerOptions<Jobs> options = new FirebaseRecyclerOptions.Builder<Jobs>()
////                    .setQuery(iquery, Jobs.class).build();
////
////            mCertificateAdapter = new FirebaseRecyclerAdapter<Jobs, CertificateViewHolder>(options) {
////
////                @NonNull
////                @Override
////                public CertificateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////                    View v = LayoutInflater.from(context).inflate(R.layout.certifcate_adapter, parent, false);
////
////                    return new CertificateViewHolder(v);
////                }
////
////                @Override
////                protected void onBindViewHolder(@NonNull CertificateViewHolder holder, int position, @NonNull Jobs model) {
////                    holder.mJobName.setText(model.getJobName());
////                    holder.mJobLoction.setText(model.getLocation());
////                }
////
////                @Override
////                public void onDataChanged() {
////                    super.onDataChanged();
//////                    sProgressBar.setVisibility(ProgressBar.INVISIBLE);
//////                    mHintsTexView.setVisibility(TextView.INVISIBLE);
////                }
////            };
////
////        }
//    }
//
////    public static class CertificateViewHolder extends RecyclerView.ViewHolder {
////
////        public TextView mJobName;
////        public TextView mJobLoction;
////        public Button mApplyButton;
////
////
////        public CertificateViewHolder(View itemView) {
////            super(itemView);
////
////            mJobName = itemView.findViewById(jobname_tv);
////            mJobLoction = itemView.findViewById(R.id.jobloc_tv);
////            mApplyButton = itemView.findViewById(R.id.apply_button);
////
////            mApplyButton.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////
////                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
////
////                    emailIntent.setType("plain/text");
////
//////                    .startActivity(Intent.createChooser(emailIntent, getResources().getText(R.string.send_to)));
////
////                }
////            });
////
////        }
////    }
//}
//
