package com.josancamon19.unittesting2cdm.di.app;

import android.app.Application;

import com.josancamon19.unittesting2cdm.BaseApplication;
import com.josancamon19.unittesting2cdm.di.viewmodel.ViewModelFactoryModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivitiesBuilderModule.class,
                AppModule.class,
                ViewModelFactoryModule.class
        }
)
public interface AppComponent  extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
