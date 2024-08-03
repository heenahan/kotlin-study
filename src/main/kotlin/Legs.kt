package org.example

import java.time.Duration

object Legs {

    @JvmStatic
    fun findLongestLegOver(
        legs: List<Leg>,
        duration: Duration
    ): Leg? {
        // 가장 긴 구간을 찾고 그 긴 구간이 주어진 duration 보다 길다면 return
        // maxByOrNull 최댓값 return
        // empty list이면 null return
        val longestLeg: Leg? = legs.maxByOrNull(Leg::plannedDuration)
        if (longestLeg != null && longestLeg.plannedDuration > duration)
            return longestLeg
        return null
    }

    private fun isLongerThan(
        leg: Leg,
        duration: Duration
    ): Boolean {
        return leg.plannedDuration > duration
    }
}