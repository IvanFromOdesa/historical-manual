package com.historicalreferencebook.historicalreferencebook.service;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface GeneralContent {

    default void setPdfParams(HttpServletResponse response) {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateFormat = dateFormatter.format(new java.util.Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=states_" + currentDateFormat + ".pdf";
        response.setHeader(headerKey, headerValue);
    }
}
