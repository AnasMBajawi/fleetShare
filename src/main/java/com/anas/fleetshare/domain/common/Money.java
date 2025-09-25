package com.anas.fleetshare.domain.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    private final BigDecimal amount;


    public Money(BigDecimal amount) {
        if(amount == null){
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getAmount() {
        return amount;
    }
    public Money plus(Money extra){
        return new Money(this.amount.add(extra.amount));
    }

    public Money minus(Money amountToSubtract){
        if(this.amount.compareTo(amountToSubtract.amount) < 0){
            throw new IllegalArgumentException("Insufficient funds");
        }
        return new Money(this.amount.subtract(amountToSubtract.amount));
    }

    public Money multiplyBy(double multiplier){
        return new Money(this.amount.multiply(BigDecimal.valueOf(multiplier)));
    }

    public  boolean isLessThan(Money comparisonAmount ){
        return this.amount.compareTo(comparisonAmount.amount) < 0;
    }

    @Override
    public String toString() {
        return amount.setScale(2, java.math.RoundingMode.HALF_UP).toPlainString() + "jod";

    }
}
