package abrayudhistira.cobafinal.application

import abrayudhistira.cobafinal.dependeciesinjection.ManajemenPropertyContainer
import android.app.Application

class ManajemenPropertyApplication : Application() {
    lateinit var container: ManajemenPropertyContainer

    override fun onCreate() {
        super.onCreate()
        container = ManajemenPropertyContainer()
    }
}