package com.essoft.recipemaker

import android.app.Application
import com.essoft.recipemaker.db.ObjectBox
import com.essoft.recipemaker.di.databaseModule
import com.essoft.recipemaker.di.repositoryModule
import com.essoft.recipemaker.di.utilsModule
import com.essoft.recipemaker.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this)
        startKoin {
            androidContext(this@App)
            modules(databaseModule, repositoryModule, utilsModule, viewModelModule)
        }
    }
}