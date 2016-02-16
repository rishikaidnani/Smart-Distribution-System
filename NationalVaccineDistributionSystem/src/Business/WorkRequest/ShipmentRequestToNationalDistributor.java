/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkRequest;

import Business.Enterprise.SiteEnterprise;
import Business.Order.Order;

/**
 *
 * @author Rishika Idnani
 */
public class ShipmentRequestToNationalDistributor extends WorkRequest {

    private String orderAttendedByNd;
    private String state;
    private SiteEnterprise siteEnterprise;
    private Order order;

    public ShipmentRequestToNationalDistributor() {
        orderAttendedByNd = "Pending";
    }

    public String getOrderAttendedByNd() {
        return orderAttendedByNd;
    }

    public void setOrderAttendedByNd(String orderAttendedByNd) {
        this.orderAttendedByNd = orderAttendedByNd;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public SiteEnterprise getSiteEnterprise() {
        return siteEnterprise;
    }

    public void setSiteEnterprise(SiteEnterprise siteEnterprise) {
        this.siteEnterprise = siteEnterprise;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
