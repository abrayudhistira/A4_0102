package abrayudhistira.cobafinal.application

import abrayudhistira.cobafinal.dependeciesinjection.PropertyContainer
import android.app.Application

class ManajemenPropertyApplication : Application() {
    lateinit var container: PropertyContainer

    override fun onCreate() {
        super.onCreate()
        container = PropertyContainer()
    }
}