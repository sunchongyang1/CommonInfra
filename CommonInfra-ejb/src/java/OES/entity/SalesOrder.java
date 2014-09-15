/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OES.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author songhan
 */
@Entity
public class SalesOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy="salesOrder")
    private SalesQuotation salesQuotation;
    private Boolean isBackOrder;
    private Boolean isFulfilled;
    private Boolean archivedOrNot;
    
    private Timestamp salesOrderDate;
    private String ownerCompanyName;

    public SalesOrder() {
    }

    public SalesOrder(SalesQuotation salesQuotation) {
        this.salesQuotation = salesQuotation;
        this.isBackOrder = false;
        this.isFulfilled = false;
        this.salesOrderDate = new Timestamp(System.currentTimeMillis());
        this.ownerCompanyName = salesQuotation.getOwnerCompanyName();
    }

    public SalesQuotation getSalesQuotation() {
        return salesQuotation;
    }

    public void setSalesQuotation(SalesQuotation salesQuotation) {
        this.salesQuotation = salesQuotation;
    }

    public Boolean isIsBackOrder() {
        return isBackOrder;
    }

    public void setIsBackOrder(Boolean isBackOrder) {
        this.isBackOrder = isBackOrder;
    }

    public Boolean isIsFulfilled() {
        return isFulfilled;
    }

    public void setIsFulfilled(Boolean isFulfilled) {
        this.isFulfilled = isFulfilled;
    }

    public Boolean isArchivedOrNot() {
        return archivedOrNot;
    }

    public void setArchivedOrNot(Boolean archivedOrNot) {
        this.archivedOrNot = archivedOrNot;
    }

    public Timestamp getSalesOrderDate() {
        return salesOrderDate;
    }

    public void setSalesOrderDate(Timestamp salesOrderDate) {
        this.salesOrderDate = salesOrderDate;
    }

    public String getOwnerCompanyName() {
        return ownerCompanyName;
    }

    public void setOwnerCompanyName(String ownerCompanyName) {
        this.ownerCompanyName = ownerCompanyName;
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
        if (!(object instanceof SalesOrder)) {
            return false;
        }
        SalesOrder other = (SalesOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OES.SalesOrder[ id=" + id + " ]";
    }
    
}
