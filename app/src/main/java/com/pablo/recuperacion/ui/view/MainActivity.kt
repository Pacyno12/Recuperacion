package com.pablo.recuperacion.ui.view

import  android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pablo.recuperacion.R
import com.pablo.recuperacion.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var currentFragment : Fragment
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list = ProductListFragment()
        val fav = ProductFavFragment()
        makeCurrentFragment(list)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.bottomNavigation.setOnNavigationItemReselectedListener {
            when (it.itemId){
                R.id.navLista -> makeCurrentFragment(list)
                R.id.navFav -> makeCurrentFragment((fav))
            }
        }
        setContentView(binding.root)


    }
    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fcv_container, fragment)
            commit()
        }

}