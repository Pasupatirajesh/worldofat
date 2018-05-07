package com.course.udacity.android.jrogen;

import net.ugolok.generation.annotations.Generate;
import net.ugolok.generation.annotations.Generator;
import net.ugolok.generation.providers.FileStringProvider;
import net.ugolok.generation.providers.IntegerProvider;


@Generator
    public class JobGenerator{
        public static final int N_PERSONS = 20;
        public static final int MIN_PERSON_ID = 1, MAX_PERSON_ID = 100;


        @Generate(provider = IntegerProvider.class, min = MIN_PERSON_ID, max = MAX_PERSON_ID, unique = true, quantity = N_PERSONS)
        int id;

        @Generate(provider = FileStringProvider.class, source = "isr-names.dta")
        String name;

        @Generate(provider = FileStringProvider.class, source = "isr-cities.dta")
        String location;

        public Jobs getJobs() {
            return new Jobs(name, id, location);
        }



}


