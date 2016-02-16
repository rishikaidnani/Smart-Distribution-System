/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.WorkRequest;

import Business.Order.Order;
import Business.Order.OrderItem;

/**
 *
 * @author Rishika Idnani
 */
public class BadVaccineToCdc extends WorkRequest {

    private OrderItem orderItem;
    private Order order;

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
