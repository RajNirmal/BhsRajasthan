package org.bhs.rajasthan

import android.app.Application
import com.parse.Parse

class BhsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId("zQVwd73opkSoKwxgYt85DFHSTZdwKPsQCH5znk5J")
                .clientKey("VIh7H7lc0aakzX0kve7KrsW1pMpEFiG0KYX3wEVM")
                .server("https://parseapi.back4app.com")
                .enableLocalDataStore()
                .build()
        )
    }

}