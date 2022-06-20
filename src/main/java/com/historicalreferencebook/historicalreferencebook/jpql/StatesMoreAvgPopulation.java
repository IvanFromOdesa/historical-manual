package com.historicalreferencebook.historicalreferencebook.jpql;

import java.math.BigDecimal;

public class StatesMoreAvgPopulation {

    private String name;
    private BigDecimal result;

    public StatesMoreAvgPopulation(String name, BigDecimal result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }
}
