package dev.javiersolis.architecture.navigation

import androidx.fragment.app.Fragment

/**
 * Created by Javier J. Solis Flores on 2/01/22.
 * @email solis.unmsm@gmail.com
 * @github https://github.com/JavierSolis
 */
open class ParentConfigHeader: IConfigHead
{
    override fun getSubtitle(fragment: Fragment) = ""

    override fun getMaxStep(fragment: Fragment) = 0

    override fun getCurrentStep(fragment: Fragment)= 0

    override fun getColor(fragment: Fragment): Int {
        return android.R.color.darker_gray
    }

}