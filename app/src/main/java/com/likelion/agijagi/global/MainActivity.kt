package com.likelion.agijagi.global

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.likelion.agijagi.R
import com.likelion.agijagi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        onSetUpNavigation()
    }

    private fun onSetUpNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        activityMainBinding.bottomNavigation.apply {
            setupWithNavController(navController)

            setOnItemSelectedListener { item ->
//                if (UserModel.uid == "" && (item.itemId == R.id.orderFragment ||
//                            item.itemId == R.id.wishListFragment ||
//                            item.itemId == R.id.buyerMypageFragment)
//                ) {
//                    displayDialogUserNotLogin(
//                        this@MainActivity,
//                        navController,
//                        R.id.loginFragment
//                    )
//                    return@setOnItemSelectedListener false
//                }

                NavigationUI.onNavDestinationSelected(item, navController)
                navController.popBackStack(item.itemId, inclusive = false)
                true
            }
            navController.addOnDestinationChangedListener { _, destination, _ ->
                visibility = when (destination.id) {
                    R.id.homeFragment,
                    R.id.categoryFragment,
                    R.id.orderFragment,
                    R.id.wishListFragment,
                    R.id.buyerMypageFragment -> {
                        View.VISIBLE
                    }

                    else -> {
                        View.GONE
                    }
                }
            }
        }
    }
}