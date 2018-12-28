package com.course.udacity.android.worldofat;


import com.infoedge.jrandomizer.adapters.StringToStringAdapter;
import com.infoedge.jrandomizer.annotations.GenerateUsing;
import com.infoedge.jrandomizer.annotations.Mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@GenerateUsing(generator = CustomJobObjectLowerCaseGenerator.class, mapping= {
        @Mapping(fieldType = String.class, adapter = StringToStringAdapter.class)
})

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

public @interface JobNameLowerCase {


}
