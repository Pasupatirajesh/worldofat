package com.course.udacity.android.worldofat;

import com.infoedge.jrandomizer.generators.GenerationRule;
import com.infoedge.jrandomizer.providers.ProviderFactory;

class CustomJobLocationObjectGenerator extends GenerationRule<JobLoc, String> {

    CustomJobLocationProvider mProvider;
    private CustomJobLocationObjectGenerator(JobLoc annotation, ProviderFactory providerFactory) {
        super(annotation, providerFactory);
        mProvider = providerFactory().provider(CustomJobLocationProvider.class, String[].class);
    }

    @Override
    public String generate() {
        String[] jobLocations = mProvider.provide();
        int randomIndex = getRandom().nextInt(jobLocations.length);
        return jobLocations[randomIndex];
    }
}





