package com.course.udacity.android.worldofat;

import net.ugolok.generation.annotations.Generate;
import net.ugolok.generation.annotations.Generator;
import net.ugolok.generation.providers.FileStringProvider;
import net.ugolok.generation.providers.IntegerProvider;


@Generator
public final class JobGenerator{
    public static final int N_PERSONS = 20;
    public static final int MIN_PERSON_ID = 1, MAX_PERSON_ID = 100;

//    public static final Stream myStream = (Stream) JobGenerator.class.getResourceAsStream("isr-cities.dta");
//
//    public static final File file = new File("main/java/isr-cities.dta");
//
//    public static final String fileName = file.toString();

    @Generate(provider = IntegerProvider.class, min = MIN_PERSON_ID, max = MAX_PERSON_ID, unique = true, quantity = N_PERSONS)
    int id;

    @Generate(provider = FileStringProvider.class, source = "isr-names.txt")
    String name;

    @Generate(provider = FileStringProvider.class, source = "isr-cities.txt")
    String location;

    public com.course.udacity.android.worldofat.Jobs getJobs() {
        return new com.course.udacity.android.worldofat.Jobs(name, id, location);
    }


}



