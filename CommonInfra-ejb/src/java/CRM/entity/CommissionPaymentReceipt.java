/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CRM.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author HanXiangyu
 */
@Entity
public class CommissionPaymentReceipt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double price;
    private String description;
    private Timestamp receiveTime;
    @OneToOne
    private CommissionPayment commissionPayment;

    public CommissionPaymentReceipt() {
    }

    public CommissionPaymentReceipt(Double price, String description, Timestamp receiveTime, CommissionPayment commissionPayment) {
        this.price = price;
        this.description = description;
        this.receiveTime = receiveTime;
        this.commissionPayment = commissionPayment;
    }
    
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Timestamp receiveTime) {
        this.receiveTime = receiveTime;
    }

    public CommissionPayment getCommissionPayment() {
        return commissionPayment;
    }

    public void setCommissionPayment(CommissionPayment commissionPayment) {
        this.commissionPayment = commissionPayment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof CommissionPaymentReceipt)) {
            return false;
        }
        CommissionPaymentReceipt other = (CommissionPaymentReceipt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CRM.entity.CommissionPaymentReceipt[ id=" + id + " ]";
    }
    
}
