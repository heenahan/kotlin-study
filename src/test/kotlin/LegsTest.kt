import org.example.Leg
import org.example.Legs.findLongestLegOver
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*
import java.util.concurrent.ThreadLocalRandom


class LegsTest {

    val legs: List<Leg> = listOf(
        leg("one hour", Duration.ofHours(1)),
        leg("one day", Duration.ofDays(1)),
        leg("two hours", Duration.ofHours(2))
    )
    val oneDay: Duration = Duration.ofDays(1)

    @Test
    fun is_absent_when_no_legs() {
        assertNull(findLongestLegOver(emptyList(), Duration.ZERO))
    }

    @Test
    fun is_absent_when_no_legs_long_enough() {
        assertNull(findLongestLegOver(legs, oneDay))
    }

    @Test
    fun is_longest_leg_when_one_match() {
        assertEquals(
            "one day",
            // Optional.orElseThrow와 같음
            // !!는 값이 있으면 return 없으면 npe
            findLongestLegOver(legs, oneDay.minusMillis(1))!!.description
        )
    }

    @Test
    fun throw_null_pointer_exception() {
        assertThrows<NullPointerException> {
            findLongestLegOver(legs, oneDay)!!.description
        }
    }

    @Test
    fun is_longest_leg_when_more_than_one_match() {
        assertEquals(
            "one day",
            // ?는 null이 아닐 경우 진행
            // null일 경우 더이상 진행하지 않고 null 그대로 반환
            findLongestLegOver(legs, Duration.ofMinutes(59))?.description
        )
    }

    @Test
    fun return_null() {
        assertNull(findLongestLegOver(legs, oneDay)?.description)
    }

    private fun leg(description: String, duration: Duration): Leg {
        val start = ZonedDateTime.ofInstant(
            Instant.ofEpochSecond(ThreadLocalRandom.current().nextInt().toLong()),
            ZoneId.of("UTC")
        )
        return Leg(description, start, start.plus(duration))
    }
}