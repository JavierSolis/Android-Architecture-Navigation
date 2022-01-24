package dev.javiersolis.architecture.navigation

import androidx.fragment.app.Fragment
/**
 * Created by Javier J. Solis Flores on 2/01/22.
 * @email solis.unmsm@gmail.com
 * @github https://github.com/JavierSolis
 */
interface IConfigHead {
    fun getSubtitle(fragment:Fragment):String
    fun getMaxStep(fragment:Fragment):Int
    fun getCurrentStep(fragment:Fragment): Int 
    fun getColor(fragment:Fragment):Int
}