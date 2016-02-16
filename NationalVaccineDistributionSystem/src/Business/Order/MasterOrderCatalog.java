/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Order;

import java.util.ArrayList;

/**
 *
 * @author Rishika Idnani
 */
public class MasterOrderCatalog {

    private ArrayList<Order> orderList;

    public MasterOrderCatalog() {
        this.orderList = new ArrayList<>();
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }
    
    public Order addOrder(Order order) {
        orderList.add(order);
        return order;
    }
}
