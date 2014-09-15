/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OES.entity;

import Common.entity.Location;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Embedded;
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
public class Invoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private SalesOrder salesOrder;
    @Embedded
    private Location warehouseLoc;
    private Timestamp invoiceDate;
    private String ownerCompanyName;

    public Invoice() {
    }

    public Invoice(SalesOrder salesOrder, Location warehouseLoc) {
        this.salesOrder = salesOrder;
        this.warehouseLoc = warehouseLoc;
        this.invoiceDate = new Timestamp(System.currentTimeMillis());
        this.ownerCompanyName = salesOrder.getOwnerCompanyName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public SalesOrder getSalesOrder() {
	return this.salesOrder;
    }
    
    public void setSalesOrder(SalesOrder salesOrder) {
	this.salesOrder = salesOrder;
    }
    
    public Location getWarehouseLoc() {
        return warehouseLoc;
    }

    public void setWarehouseLoc(Location warehouseLoc) {
        this.warehouseLoc = warehouseLoc;
    }

    public Timestamp getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvocieDate(Timestamp InvoiceDate) {
        this.invoiceDate = InvoiceDate;
    }

    public String getOwnerCompanyName() {
        return ownerCompanyName;
    }

    public void setOwnerCompanyName(String ownerCompanyName) {
        this.ownerCompanyName = ownerCompanyName;
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
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OES.Invoice[ id=" + id + " ]";
    }
    
}
