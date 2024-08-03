package org.example

import java.time.Duration

object Legs {

    // ?.let
    // null 객체가 들어오면 null 반환
    // 아니면 let 문 실행 결국 let 안에서 수신 객체는 null일 수 없음
    @JvmStatic
    fun findLongestLegOver(
        legs: List<Leg>,
        duration: Duration
    ): Leg? {
        val longestLeg = legs.maxByOrNull(Leg::plannedDuration)
//        return when {
//            longestLeg == null -> null
//            longestLeg.plannedDuration > duration -> longestLeg
//            else -> null
//        }
        // takeIf는 술어가 true면 수신 객체 반환
        // true가 아니면 null 반환
        return longestLeg?.takeIf {
            longestLeg -> longestLeg.plannedDuration > duration
        }
    }

    private fun isLongerThan(
        leg: Leg,
        duration: Duration
    ): Boolean {
        return leg.plannedDuration > duration
    }
}