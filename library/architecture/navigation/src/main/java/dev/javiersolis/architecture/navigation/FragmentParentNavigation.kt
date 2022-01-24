package dev.javiersolis.architecture.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

open class FragmentParentNavigation {
    fun interface ListenerNav {
        fun onNavTo(newFragment: Fragment)
    }

    private fun insertFragment(fragment: Fragment, bundle: Bundle = Bundle()) {
        fragment.arguments = bundle
        fragmentManager
            ?.beginTransaction()
            ?.replace(idContent, fragment)
            ?.commit()
    }


    //Para activitys
    val TAG = "CORE.FRAG_NAV"

    //Para el menu activity

    //region fragment
    private var idContent = 0
    var currentFragment: String = ""
    var fragmentManager: FragmentManager? = null

    private var listenerNavigation: ListenerNav? = null

    fun setListenerNavigation(listenerNav: ListenerNav){
        this.listenerNavigation = listenerNav
    }


    fun setup(supportFragmentManager: FragmentManager, navHostFragment: Int) {
        fragmentManager = supportFragmentManager
        idContent = navHostFragment
    }

    fun changeContent(newFragment: Fragment, bundle: Bundle = Bundle()) {

        newFragment.arguments = bundle

        listenerNavigation?.onNavTo(newFragment)

        val ft = fragmentManager!!.beginTransaction()
        //para animas las transiciones
        //ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        ft.replace(idContent, newFragment)
        //ft.addToBackStack(null);
        ft.commitAllowingStateLoss()
        currentFragment = newFragment.javaClass.name
    }

    /*
    fun launchMainOptions() = newInstance(MAIN_OPTIONS).apply { changeContent(this) }
    */


}