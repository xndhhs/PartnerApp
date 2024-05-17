package ro.partnerapp.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ro.partnerapp.injected.appModules

class PartnerApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PartnerApp)
            modules(appModules)
        }
    }
}