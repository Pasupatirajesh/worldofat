package com.course.udacity.android.worldofat.Model;

import com.infoedge.jrandomizer.generators.GenerationRule;
import com.infoedge.jrandomizer.providers.ProviderFactory;

class CustomJobObjectGenerator extends GenerationRule<JobName, String> {

    CustomJobObjectProvider mProvider;

//        public JobTitleGenerator(JobTitle annotation, ProviderFactory providerFactory) {
//            super(annotation, providerFactory);
//            mProvider = providerFactory().provider(JobTitleProvider.class, String[].class);
//        }

    private CustomJobObjectGenerator(JobName annotation, ProviderFactory providerFactory) {
        super(annotation, providerFactory);
            mProvider = providerFactory().provider(CustomJobObjectProvider.class, String[].class);
    }

    @Override
    public String generate() {

        String[] jobTitles = mProvider.provide();
        int randomIndex = getRandom().nextInt(jobTitles.length);
        return jobTitles[randomIndex];
    }
}
