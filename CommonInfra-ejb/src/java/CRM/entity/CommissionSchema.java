/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CRM.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author HanXiangyu
 */
@Entity
public class CommissionSchema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private List<Double> boundaryPrice;
    private List<Double> commissionRate;
    @OneToOne
    private ServiceOrder serviceOrder;
    @OneToMany(mappedBy="schema")
    private List<CommissionPayment> commissionPayment;

    public CommissionSchema() {
    }

    public CommissionSchema(List<Double> boundaryPrice, List<Double> commissionRate) {
        this.boundaryPrice = boundaryPrice;
        this.commissionRate = commissionRate;
    }

    public Double assignPriceToRate(Double price){
        for(int i=0; i<this.boundaryPrice.size(); i++){
            if(price <= boundaryPrice.get(i)){
                return this.commissionRate.get(i);
            }
        }
        return this.commissionRate.get(this.commissionRate.size()-1);
    }
    public List<Double> getBoundaryPrice() {
        return boundaryPrice;
    }

    public void setBoundaryPrice(List<Double> boundaryPrice) {
        this.boundaryPrice = boundaryPrice;
    }

    public List<Double> getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(List<Double> commissionRate) {
        this.commissionRate = commissionRate;
    }

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public List<CommissionPayment> getCommissionPayment() {
        return commissionPayment;
    }

    public void setCommissionPayment(List<CommissionPayment> commissionPayment) {
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
        if (!(object instanceof CommissionSchema)) {
            return false;
        }
        CommissionSchema other = (CommissionSchema) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CRM.entity.CommissionSchema[ id=" + id + " ]";
    }
    
}
