package com.course.udacity.android.jrogen;

import net.ugolok.generation.JROFactory;

import java.util.Iterator;

public class MyClass {

    public static Jobs j = new Jobs();
    public static  Jobs getRandomJobsObject(){

        try {
            Iterator<JobGenerator> iterator = JROFactory.create(JobGenerator.class).iterator();
            while (iterator.hasNext()) {
                j = iterator.next().getJobs();
//                System.out.printf(j.getJobName());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return j;
    }

}
