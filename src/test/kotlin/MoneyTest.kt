import org.example.Money
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class MoneyTest {

    @Test
    fun `data class의 copy 메서드는 캡슐화를 깨트린다`() {
        // given
        val money = Money(BigDecimal(1000))

        // when
        val copiedMoney = money.copy(amount = BigDecimal(-1000))

        // then
        assertEquals(copiedMoney.amount, BigDecimal(-1000))
    }
}