package com.course.udacity.android.worldofat;

import org.parceler.Parcel;

@Parcel
public class Jobs {

    String jobName;
    int jobId;
    String location;

    public Jobs(){

    }

    public Jobs(String jobName, int id, String loc) {
        this.jobName = jobName;
        this.jobId = id;
        this.location = loc;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}


