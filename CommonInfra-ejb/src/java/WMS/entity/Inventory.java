/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WMS.entity;

import OES.entity.Product;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author songhan
 * 
 * 
 */
@Entity
public class Inventory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double quantity;
    private Integer neededShelfCapacity;
    private Boolean perishable;
    private Boolean flammable;
    private Boolean pharmaceutical;
    private Boolean highValue;
    @ManyToOne
    private Product product;
    private Timestamp entryTime;
    private String flowType; //Only "Inflow" and "Outflow" are allowed in this field
    private Boolean archivedOrNot;
    @OneToMany
    private Set<Shelf> shelfList = new HashSet<>();
    private String warehouseOwnerName;

    public Inventory() {
    }

    public Inventory(Double quantity, Integer neededShelfCapacity, Boolean perishable, Boolean flammable, Boolean pharmaceutical, Boolean highValue, Product product, String flowType, Set<Shelf> shelfList) {
        this.quantity = quantity;
        this.neededShelfCapacity = neededShelfCapacity;
        this.perishable = perishable;
        this.flammable = flammable;
        this.pharmaceutical = pharmaceutical;
        this.highValue = highValue;
        this.product = product;
        this.flowType = flowType;
        this.archivedOrNot = Boolean.FALSE;
        this.shelfList = shelfList;
        List sl = (List) shelfList;
        Shelf s = (Shelf) sl.get(0);
        this.warehouseOwnerName = s.getWarehouse().getOwnerCompany().getCompanyName();       
        this.entryTime = new Timestamp(System.currentTimeMillis());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Integer getNeededShelfCapacity() {
        return neededShelfCapacity;
    }

    public void setNeededShelfCapacity(Integer neededShelfCapacity) {
        this.neededShelfCapacity = neededShelfCapacity;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    public Timestamp getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Timestamp entryTime) {
        this.entryTime = entryTime;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public Boolean isArchivedOrNot() {
        return archivedOrNot;
    }

    public void setArchivedOrNot(Boolean archivedOrNot) {
        this.archivedOrNot = archivedOrNot;
    }

    public Set<Shelf> getShelfList() {
        return shelfList;
    }

    public void setShelfList(Set<Shelf> shelfList) {
        this.shelfList = shelfList;
    }

    public String getWarehouseOwnerName() {
        return warehouseOwnerName;
    }

    public void setWarehouseOwnerName(String warehouseOwnerName) {
        this.warehouseOwnerName = warehouseOwnerName;
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
        if (!(object instanceof Inventory)) {
            return false;
        }
        Inventory other = (Inventory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WMS.entity.Inventory[ id=" + id + " ]";
    }
    
}
