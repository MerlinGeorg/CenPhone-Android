package com.example.team11_mapd711_project_milestone2

import android.app.Application

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize the database if needed
        DatabaseProvider.getDatabase(this)
    }
}