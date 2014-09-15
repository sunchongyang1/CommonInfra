/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CRM.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author HanXiangyu
 */
@Entity
public class CommissionPayment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double price;
    private Timestamp paymentTime;
    @OneToOne
    private ServiceOrder serviceOrder;
    @OneToOne
    private CommissionPaymentReceipt receipt;
    @ManyToOne
    private CommissionSchema schema;

    public CommissionPayment() {
    }

    public CommissionPayment(Double price, Timestamp paymentTime, ServiceOrder serviceOrder) {
        this.price = price;
        this.paymentTime = paymentTime;
        this.serviceOrder = serviceOrder;
    }
    

    public CommissionSchema getSchema() {
        return schema;
    }

    public void setSchema(CommissionSchema schema) {
        this.schema = schema;
    }

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public CommissionPaymentReceipt getReceipt() {
        return receipt;
    }

    public void setReceipt(CommissionPaymentReceipt receipt) {
        this.receipt = receipt;
    }
    
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Timestamp getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Timestamp paymentTime) {
        this.paymentTime = paymentTime;
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
        if (!(object instanceof CommissionPayment)) {
            return false;
        }
        CommissionPayment other = (CommissionPayment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CRM.entity.CommissionPayment[ id=" + id + " ]";
    }
    
}
