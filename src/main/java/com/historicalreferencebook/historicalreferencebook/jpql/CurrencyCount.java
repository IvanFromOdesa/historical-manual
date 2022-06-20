package com.historicalreferencebook.historicalreferencebook.jpql;

public class CurrencyCount {

    private String curName;
    private Long total;

    public CurrencyCount(String curName, Long total) {
        this.curName = curName;
        this.total = total;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
