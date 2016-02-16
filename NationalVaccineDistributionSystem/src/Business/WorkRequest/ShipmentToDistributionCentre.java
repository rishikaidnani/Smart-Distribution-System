/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkRequest;

import Business.Enterprise.SiteEnterprise;
import Business.Order.Order;
import Business.Order.OrderItem;

/**
 *
 * @author Rishika Idnani
 */
public class ShipmentToDistributionCentre extends WorkRequest {

    private String stateName;
    private OrderItem orderItem;
    private SiteEnterprise siteEnterprise;
    private Order order;

    public SiteEnterprise getSiteEnterprise() {
        return siteEnterprise;
    }

    public void setSiteEnterprise(SiteEnterprise siteEnterprise) {
        this.siteEnterprise = siteEnterprise;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
