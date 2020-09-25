package com.sablania.starwargame.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sablania.starwargame.R
import com.sablania.starwargame.fragments.PlayersFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment: Fragment = PlayersFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, fragment, PlayersFragment.TAG).commitAllowingStateLoss()
    }
}