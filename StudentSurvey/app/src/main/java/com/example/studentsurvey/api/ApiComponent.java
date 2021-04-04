package com.example.studentsurvey.api;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent {
    Api getApi();
}
