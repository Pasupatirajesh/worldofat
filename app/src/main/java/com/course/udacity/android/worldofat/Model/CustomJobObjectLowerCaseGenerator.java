package com.course.udacity.android.worldofat.Model;

import com.infoedge.jrandomizer.generators.GenerationRule;
import com.infoedge.jrandomizer.providers.ProviderFactory;

class CustomJobObjectLowerCaseGenerator extends GenerationRule<JobName, String> {


    private CustomJobObjectLowerCaseProvider mProvider;

    private CustomJobObjectLowerCaseGenerator(JobName annotation, ProviderFactory providerFactory) {
        super(annotation, providerFactory);
        mProvider = providerFactory().provider(CustomJobObjectLowerCaseProvider.class, String[].class);
    }

    @Override
    public String generate() {
        String[] jobTitles = mProvider.provide();
        int randomIndex = getRandom().nextInt(jobTitles.length);
        return jobTitles[randomIndex];
    }
}
