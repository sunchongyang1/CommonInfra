/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MRPII.entity;

import OES.entity.FinishedGood;
import OES.entity.Product;
import OES.entity.PurchaseOrder;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author songhan
 */
@Entity
public class PurchaseQuotation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double totalPrice;
    private Timestamp purchaseQuotationDate;
    private Timestamp orderScheduledDate;
    private Integer orderScheduledYear;
    private Integer orderScheduledMonth;
    private Integer orderScheduledWeek;
    @OneToOne
    private ResourcePlan resourcePlan;
    @OneToOne
    private PurchaseOrder purchaseOrder;
    private String purchaseType;
    private String ownerCompanyName;
    @ManyToOne
    private Product product;
    private Double quantity;
    private Integer batchNumber;

    public PurchaseQuotation() {
    }

    public PurchaseQuotation(Double totalPrice,ResourcePlan resourcePlan, String purchaseType, String ownerCompanyName, Timestamp orderScheduledDate) {
        this.totalPrice = totalPrice;
        this.purchaseQuotationDate = new Timestamp(System.currentTimeMillis());
        this.orderScheduledYear = resourcePlan.getPlannedYear();
        this.orderScheduledMonth = resourcePlan.getPlannedMonth();
        this.orderScheduledWeek = resourcePlan.getPlannedWeek();
        this.resourcePlan = resourcePlan;
        this.purchaseType = purchaseType;
        this.ownerCompanyName = ownerCompanyName;
        this.product = resourcePlan.getRawMaterial();
        this.quantity = resourcePlan.getGrossRequirements();
        this.batchNumber = resourcePlan.getBatchNumber();
        this.orderScheduledDate = orderScheduledDate;
    }
    
    public PurchaseQuotation(Double totalPrice, String purchaseType, String ownerCompanyName, FinishedGood finishedGood, Integer batchNumber, Double quantity, Integer orderSentYear, Integer orderSentMonth, 
            Integer orderSentWeek, Timestamp orderScheduledDate){
        this.purchaseQuotationDate = new Timestamp(System.currentTimeMillis());
        this.totalPrice = totalPrice;
        this.orderScheduledYear = orderSentYear;
        this.orderScheduledMonth = orderSentMonth;
        this.orderScheduledWeek = orderSentWeek;
        this.purchaseType = purchaseType;
        this.ownerCompanyName = ownerCompanyName;
        this.product = finishedGood;
        this.batchNumber = batchNumber;
        this.quantity = quantity;
        this.orderScheduledDate = orderScheduledDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getPurchaseQuotationDate() {
        return purchaseQuotationDate;
    }

    public void setPurchaseQuotationDate(Timestamp purchaseQuotationDate) {
        this.purchaseQuotationDate = purchaseQuotationDate;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getOwnerCompanyName() {
        return ownerCompanyName;
    }

    public void setOwnerCompanyName(String ownerCompanyName) {
        this.ownerCompanyName = ownerCompanyName;
    }

    public Timestamp getOrderScheduledDate() {
        return orderScheduledDate;
    }

    public void setOrderScheduledDate(Timestamp orderScheduledDate) {
        this.orderScheduledDate = orderScheduledDate;
    }

    public Integer getOrderScheduledYear() {
        return orderScheduledYear;
    }

    public void setOrderScheduledYear(Integer orderScheduledYear) {
        this.orderScheduledYear = orderScheduledYear;
    }

    public Integer getOrderScheduledMonth() {
        return orderScheduledMonth;
    }

    public void setOrderScheduledMonth(Integer orderScheduledMonth) {
        this.orderScheduledMonth = orderScheduledMonth;
    }

    public Integer getOrderScheduledWeek() {
        return orderScheduledWeek;
    }

    public void setOrderScheduledWeek(Integer orderScheduledWeek) {
        this.orderScheduledWeek = orderScheduledWeek;
    }

    public ResourcePlan getResourcePlan() {
        return resourcePlan;
    }

    public void setResourcePlan(ResourcePlan resourcePlan) {
        this.resourcePlan = resourcePlan;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Integer getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(Integer batchNumber) {
        this.batchNumber = batchNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseQuotation)) {
            return false;
        }
        PurchaseQuotation other = (PurchaseQuotation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OES.PurchaseQuotation[ id=" + id + " ]";
    }
    
}
