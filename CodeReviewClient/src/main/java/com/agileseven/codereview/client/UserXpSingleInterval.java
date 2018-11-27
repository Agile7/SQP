package com.agileseven.codereview.client;

/**
 * @author Mahmoud AL NAJAR
 */
public class UserXpSingleInterval {

    private int xpGained;
    private String dateFrom;
    private String dateTo;

    public UserXpSingleInterval() {
    }

    public UserXpSingleInterval(int xpGained, String dateFrom, String dateTo) {
        this.xpGained = xpGained;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public int getXpGained() {
        return xpGained;
    }

    public void setXpGained(int xpGained) {
        this.xpGained = xpGained;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

}
