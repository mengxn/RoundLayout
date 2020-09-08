package me.codego.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.codego.example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mViewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)

        mViewBinding.firstLayout.setOnClickListener {
            mViewBinding.firstLayout.setRadius(70f, 70f, 70f, 70f)
        }
    }
}