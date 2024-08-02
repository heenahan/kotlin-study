package org.example

import java.math.BigDecimal

data class Money(
    val amount: BigDecimal
) {

    companion object {
        @JvmStatic
        fun from(
            amount: BigDecimal
        ): Money {
            require(amount < BigDecimal.ZERO) {
                "값은 음수여선 안됩니다"
            }
            return Money(amount)
        }
    }

    fun add(
        money: Money
    ): Money {
        require(money.amount < BigDecimal.ZERO) {
            "값은 음수여선 안됩니다"
        }
        val result = this.amount.add(money.amount)
        return Money(result)
    }
}
