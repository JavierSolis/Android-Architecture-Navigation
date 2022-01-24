package dev.javiersolis.architecture.navigation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

open class AppParentNavigation {

    //Para activitys
    private var currentActivity: AppCompatActivity? = null

    fun setCurrentActivity(activity: AppCompatActivity) {
        currentActivity = activity
    }

    private fun verifyActivityInstance() {
        if (currentActivity == null) {
            throw Exception("Current activity no fue instanciado")
        }
    }

    open val TAG = "ARCH.APP_NAV"
    fun startAct(className: String) {
        try {
            Log.i(TAG, "launch:$className")
            val c = Class.forName(className)
            val intent = Intent(currentActivity, c)
            currentActivity?.startActivity(intent)
        } catch (ignored: Exception) {
            ignored.printStackTrace()
            Log.e(TAG, "no existe :$className")
        }
    }

    //Para el menu activity
    private var drawerLayout: DrawerLayout? = null
    fun setDrawerMain(drawer: DrawerLayout) {
        this.drawerLayout = drawer
    }

    fun openMenu() {
        /*
        *  if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            mDrawerLayout.closeDrawer(Gravity.RIGHT);
        }
        else {
            mDrawerLayout.openDrawer(Gravity.RIGHT);
        }*/
        this.drawerLayout?.openDrawer(Gravity.LEFT)
    }

    fun closeMenu() {

        /*
        if (this.drawerLayout?.isDrawerOpen(Gravity.RIGHT) == true) {
            this.drawerLayout?.closeDrawer(Gravity.RIGHT);
        }
        else {
            this.drawerLayout?.openDrawer(Gravity.LEFT);
        }
        */
        this.drawerLayout?.closeDrawer(Gravity.LEFT)
    }


    //region fragment
    private var fragmentManager: FragmentManager? = null
    private var idContent = 0
    var currentFragment: String = ""


    fun setupToFragment(supportFragmentManager: FragmentManager, navHostFragment: Int) {
        fragmentManager = supportFragmentManager
        idContent = navHostFragment
    }

    fun changeContent(newFragment: Fragment, bundle: Bundle = Bundle()) {
        changeContent(idContent,newFragment,bundle)
    }

    fun changeContent(idContent:Int, newFragment: Fragment, bundle: Bundle = Bundle()) {
        newFragment.arguments = bundle
        val ft = fragmentManager!!.beginTransaction()
        //para animas las transiciones
        //ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        ft.replace(idContent, newFragment)
        //ft.addToBackStack(null);
        ft.commitAllowingStateLoss()
        currentFragment = newFragment.javaClass.name
    }

    fun changeContent(className: String){
        changeContent(idContent,className)
    }

    fun changeContent(idContent:Int,className: String){
        val instance = newInstance(className)
        changeContent(idContent,instance)
    }

    fun newInstance(className: String): Fragment {
        Log.i(TAG, "Instancia fragment de:$className")
        return Class.forName(className).newInstance() as Fragment
    }

    fun finish() {
        currentActivity?.finish()
    }
    //endregion fragment


}