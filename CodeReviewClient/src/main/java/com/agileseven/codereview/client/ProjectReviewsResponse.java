package com.agileseven.codereview.client;

/**
 * @author Mahmoud AL NAJAR
 */
public class ProjectReviewsResponse {

    private double min;
    private double max;
    private double total;

    public ProjectReviewsResponse(double min, double max, double total) {
        this.min = min;
        this.max = max;
        this.total = total;
    }

    public ProjectReviewsResponse() {
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}