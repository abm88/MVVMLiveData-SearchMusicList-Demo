package net.abm.testApp.screen.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector

import kotlinx.android.synthetic.main.activity_main.*
import net.abm.testApp.R
import javax.inject.Inject

class HomeActivity : AppCompatActivity() , HasSupportFragmentInjector{

    @Inject lateinit var fragmentInjector  : DispatchingAndroidInjector<Fragment>
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        initNavigationController()
    }



    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController , null)

    }
    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }



    private fun initNavigationController() {

        navController = Navigation.findNavController(this,  R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this , navController)
    }

}
