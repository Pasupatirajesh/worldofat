package com.course.udacity.android.worldofat;

import com.infoedge.jrandomizer.annotations.CustomGenerator;
import com.infoedge.jrandomizer.annotations.Number;

import org.parceler.Parcel;



@Parcel
public class Jobs {

    @JobName
    @CustomGenerator(generator = CustomJobObjectGenerator.class)
    String jobName;
    @Number(min = 10, max = 20, decimals = 0)
    int jobId;
    @JobLoc
    @CustomGenerator(generator = CustomJobLocationObjectGenerator.class)
    String location;


    public Jobs() {

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


