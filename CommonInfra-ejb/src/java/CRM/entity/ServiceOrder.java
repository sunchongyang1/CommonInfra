/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CRM.entity;

import Common.entity.Location;
import OES.entity.Product;
import TMS.entity.TransOrder;
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
 * @author HanXiangyu
 */
@Entity
public class ServiceOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp creationTime;
    private Timestamp startTime;
    private Timestamp endTime;
    private Double price;
    private String description;
    // Boolean 
    @ManyToOne
    private Product product;
    private Double quantity;
    private Location sourceLoc;
    private Location destLoc;
    private Long warehouseId;
    private String serviceType; //Only "Transportation" and "Warehouse" are allowed
    
    private Boolean perishable;
    private Boolean flammable;
    private Boolean pharmaceutical;
    private Boolean highValue;
    
    private Boolean approvedOrNot;
    private Boolean fulfilledOrNot;
    @ManyToOne
    private ServiceContract serviceContract;
    @OneToOne(mappedBy="serviceOrder")
    private ServiceInvoice serviceInvoice;
    @OneToOne(mappedBy="serviceOrder")
    private TransOrder transOrder;
    @OneToOne(mappedBy="serviceOrder")
    private WarehouseOrder warehouseOrder;    
    @OneToOne
    private CommissionPayment commissionPayment;
    @ManyToOne
    private CommissionSchema commissionSchema;

    public ServiceOrder() {
    }

    public ServiceOrder(Timestamp startTime, Timestamp endTime, Double price, String description, Product product, Double quantity, Location sourceLoc, 
            Location destLoc, Long warehouseId, String serviceType, Boolean perishable, Boolean flammable, Boolean pharmaceutical, Boolean highValue, ServiceContract serviceContract) {
        this.creationTime = new Timestamp(System.currentTimeMillis());
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.description = description;
        this.product = product;
        this.quantity = quantity;
        this.sourceLoc = sourceLoc;
        this.destLoc = destLoc;
        this.warehouseId = warehouseId;
        this.serviceType = serviceType;
        this.perishable = perishable;
        this.flammable = flammable;
        this.pharmaceutical = pharmaceutical;
        this.highValue = highValue;
        this.serviceContract = serviceContract;
        
        this.approvedOrNot = Boolean.FALSE;
        this.fulfilledOrNot = Boolean.FALSE;
    }

    
    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
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

    public ServiceContract getServiceContract() {
        return serviceContract;
    }

    public void setServiceContract(ServiceContract serviceContract) {
        this.serviceContract = serviceContract;
    }

    public ServiceInvoice getServiceInvoice() {
        return serviceInvoice;
    }

    public void setServiceInvoice(ServiceInvoice serviceInvoice) {
        this.serviceInvoice = serviceInvoice;
    }

    public TransOrder getTransOrder() {
        return transOrder;
    }

    public void setTransOrder(TransOrder transOrder) {
        this.transOrder = transOrder;
    }

    public WarehouseOrder getWarehouseOrder() {
        return warehouseOrder;
    }

    public void setWarehouseOrder(WarehouseOrder warehouseOrder) {
        this.warehouseOrder = warehouseOrder;
    }

    public CommissionPayment getCommissionPayment() {
        return commissionPayment;
    }

    public void setCommissionPayment(CommissionPayment commissionPayment) {
        this.commissionPayment = commissionPayment;
    }

    public CommissionSchema getCommissionSchema() {
        return commissionSchema;
    }

    public void setCommissionSchema(CommissionSchema commissionSchema) {
        this.commissionSchema = commissionSchema;
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

    public Location getSourceLoc() {
        return sourceLoc;
    }

    public void setSourceLoc(Location sourceLoc) {
        this.sourceLoc = sourceLoc;
    }

    public Location getDestLoc() {
        return destLoc;
    }

    public void setDestLoc(Location destLoc) {
        this.destLoc = destLoc;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Boolean isPerishable() {
        return perishable;
    }

    public void setPerishable(Boolean perishable) {
        this.perishable = perishable;
    }

    public Boolean isFlammable() {
        return flammable;
    }

    public void setFlammable(Boolean flammable) {
        this.flammable = flammable;
    }

    public Boolean isPharmaceutical() {
        return pharmaceutical;
    }

    public void setPharmaceutical(Boolean pharmaceutical) {
        this.pharmaceutical = pharmaceutical;
    }

    public Boolean isHighValue() {
        return highValue;
    }

    public void setHighValue(Boolean highValue) {
        this.highValue = highValue;
    }

    public Boolean isApprovedOrNot() {
        return approvedOrNot;
    }

    public void setApprovedOrNot(Boolean approvedOrNot) {
        this.approvedOrNot = approvedOrNot;
    }

    public Boolean isFulfilledOrNot() {
        return fulfilledOrNot;
    }

    public void setFulfilledOrNot(Boolean fulfilledOrNot) {
        this.fulfilledOrNot = fulfilledOrNot;
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
        if (!(object instanceof ServiceOrder)) {
            return false;
        }
        ServiceOrder other = (ServiceOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CRM.entity.ServiceOrder[ id=" + id + " ]";
    }
    
}
