/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Order;

import Business.Enterprise.SiteEnterprise;
import Business.Person.Person;
import Business.Vaccine.Vaccine;
import java.util.ArrayList;

/**
 *
 * @author Rishika Idnani
 */
public class Order {

    private ArrayList<OrderItem> orderItemList;
    private SiteEnterprise site;
    private String timeStamp;
    private Person provider;

    public Order() {
        this.orderItemList = new ArrayList<>();
    }

    public ArrayList<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(ArrayList<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public OrderItem addAndCreateOrderItem(Vaccine vaccine, int totalQuantity) {
        OrderItem orderItem = new OrderItem();
        orderItemList.add(orderItem);
        orderItem.setVaccine(vaccine);
        orderItem.setTotalQuantity(totalQuantity);
        orderItem.calculateTotalPrice();
        return orderItem;
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItemList.remove(orderItem);
    }

    public SiteEnterprise getSite() {
        return site;
    }

    public void setSite(SiteEnterprise site) {
        this.site = site;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Person getProvider() {
        return provider;
    }

    public void setProvider(Person provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return timeStamp;
    }

}
