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
public class ApprovalRequestToPhd extends WorkRequest {

    private Order order;
    private SiteEnterprise siteEnterprise;
    private String isOrderApprovedByPhd;

    public ApprovalRequestToPhd() {
        isOrderApprovedByPhd = "Pending";
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public SiteEnterprise getSiteEnterprise() {
        return siteEnterprise;
    }

    public void setSiteEnterprise(SiteEnterprise siteEnterprise) {
        this.siteEnterprise = siteEnterprise;
    }

    public String getIsOrderApprovedByPhd() {
        return isOrderApprovedByPhd;
    }

    public void setIsOrderApprovedByPhd(String isOrderApprovedByPhd) {
        this.isOrderApprovedByPhd = isOrderApprovedByPhd;
    }

}
