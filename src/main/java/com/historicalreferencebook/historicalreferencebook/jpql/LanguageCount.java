package com.historicalreferencebook.historicalreferencebook.jpql;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class LanguageCount {

    private String langName;
    private Long total;
}
