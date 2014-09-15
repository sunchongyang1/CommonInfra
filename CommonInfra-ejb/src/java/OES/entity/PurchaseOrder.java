/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OES.entity;

import MRPII.entity.PurchaseQuotation;
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
public class PurchaseOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp purchaseOrderDate;
    @OneToOne(mappedBy = "purchaseOrder")
    private PurchaseQuotation purchaseQuotation;
    private String purchaseType; //is the same as the purchaseType field in its purchase quotation, i.e. only "FinishedGoods" and "RawMaterial" allowed
    private String ownerCmpanyName;
    
    public PurchaseOrder() {
    }

    public PurchaseOrder(PurchaseQuotation purchaseQuotation) {
        String type = purchaseQuotation.getPurchaseType();
        this.purchaseQuotation = purchaseQuotation;
        this.purchaseType = type;
        this.purchaseOrderDate = new Timestamp(System.currentTimeMillis());
        this.ownerCmpanyName = purchaseQuotation.getOwnerCompanyName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getPurchaseOrderDate() {
        return purchaseOrderDate;
    }

    public void setPurchaseOrderDate(Timestamp purchaseOrderDate) {
        this.purchaseOrderDate = purchaseOrderDate;
    }

    public PurchaseQuotation getPurchaseQuotation() {
        return purchaseQuotation;
    }

    public void setPurchaseQuotation(PurchaseQuotation purchaseQuotation) {
        this.purchaseQuotation = purchaseQuotation;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getOwnerCmpanyName() {
        return ownerCmpanyName;
    }

    public void setOwnerCmpanyName(String ownerCmpanyName) {
        this.ownerCmpanyName = ownerCmpanyName;
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
        if (!(object instanceof PurchaseOrder)) {
            return false;
        }
        PurchaseOrder other = (PurchaseOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OES.PurchaseOrder[ id=" + id + " ]";
    }
    
}
