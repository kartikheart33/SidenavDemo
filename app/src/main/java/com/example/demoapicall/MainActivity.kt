package com.example.demoapicall

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.demoapicall.adapter.ViewPagerAdapter
import com.example.demoapicall.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.colorsecond)


        val drawerContent = layoutInflater.inflate(R.layout.drawer_content, binding.drawerContentContainer, false)
        binding.drawerContentContainer.addView(drawerContent)
        binding.btnOpenDrawer.setOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }


        val adapter = ViewPagerAdapter(this)
        binding.viewPager2.adapter = adapter

        // Link TabLayout and ViewPager
        val tabTitles = listOf(
            "Tab 1", "Tab 2", "Tab 3"
        )

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()


        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> binding.viewPager2.currentItem = 0
                R.id.nav_dashboard -> binding.viewPager2.currentItem = 1
                R.id.nav_notifications -> binding.viewPager2.currentItem = 2
            }
            true
        }

        // Sync ViewPager page changes with BottomNavigationView
        binding. viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 ->  binding.bottomNavigationView.selectedItemId = R.id.nav_home
                    1 ->  binding.bottomNavigationView.selectedItemId = R.id.nav_dashboard
                    2 ->  binding.bottomNavigationView.selectedItemId = R.id.nav_notifications
                }
            }
        })
        drawerContent.findViewById<LinearLayout>(R.id.profileSection).setOnClickListener {
            Toast.makeText(this, "Profile selected", Toast.LENGTH_SHORT).show()
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        drawerContent.findViewById<LinearLayout>(R.id.settingsSection).setOnClickListener {
            Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show()
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }
        drawerContent.findViewById<LinearLayout>(R.id.logoutSection).setOnClickListener {
            Toast.makeText(this, "Logout selected", Toast.LENGTH_SHORT).show()
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }

    }
}