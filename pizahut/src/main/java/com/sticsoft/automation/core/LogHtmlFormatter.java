package com.sticsoft.automation.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class LogHtmlFormatter extends Formatter {

    public String format(LogRecord rec) {
        StringBuilder buf = new StringBuilder();
        buf.append("<tr>");

        buf.append("<td>");
        buf.append(calcDate(rec.getMillis()));
        buf.append("</td>");

        if (rec.getLevel().intValue() >= Level.WARNING.intValue()) {
            buf.append("<td class=\"text-danger\">");
        } else {
            buf.append("<td>");
        }
        buf.append(rec.getLevel());
        buf.append("</td>");

        buf.append("<td>");
        buf.append(formatMessage(rec));
        buf.append("</td>");

        buf.append("</tr>");
        return buf.toString();
    }

    private String calcDate(long millisecs) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");
        Date resultdate = new Date(millisecs);
        return dateFormat.format(resultdate);
    }

    public String getHead(Handler h) {
        return "<!DOCTYPE html><head>"
                + "<title>Test Automation Logs</title>"
                + "<style>"
                + "table { width: 100% }"
                + "th { font:bold 10pt Tahoma; }"
                + "td { font:normal 10pt Tahoma; }"
                + "h1 {font:normal 15pt Tahoma;}"
                + "</style>"
                + "<link href=\"https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.css\" rel=\"stylesheet\">"
                + "<link href=\"https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap4.min.css\" rel=\"stylesheet\">"
                + "</head>"
                + "<body>"
                + "<script type=\"text/javascript\" src=\"https://code.jquery.com/jquery-3.2.1.min.js\"></script>"
                + "<script src=\"https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js\"></script>"
                + "<script src=\"https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap4.min.js\"></script>"
                + "<h1> Test Automation Logs (" + (new Date()) + ")</h1>"
                + "<table id=\"logs\" border=\"0\" cellpadding=\"5\" cellspacing=\"3\" class=\"table-hover\">"
                + "<thead>"
                + "<tr align=\"left\">"
                + "<th style=\"width:15%\">Time</th>"
                + "<th style=\"width:10%\">Level</th>"
                + "<th style=\"width:75%\">Message</th>"
                + "</tr>"
                + "</thead>"
                + "<tbody>";
    }

    public String getTail(Handler h) {
        return "</tbody>"
                + "</table>"
                + "<script>"
                + "$(document).ready( function () {"
                + "    $('#logs').DataTable();"
                + "} );"
                + "</script>"
                + "</body>"
                + "</html>";
    }
}