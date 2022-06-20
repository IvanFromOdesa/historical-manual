package com.historicalreferencebook.historicalreferencebook.jpql;

public class LanguageCount {
    private String langName;
    private Long total;

    public LanguageCount(String langName, Long total) {
        this.langName = langName;
        this.total = total;
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
