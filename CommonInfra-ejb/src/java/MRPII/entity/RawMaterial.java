/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MRPII.entity;

import Common.entity.CustomerCompany;
import OES.entity.Product;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author songhan
 */
@Entity
public class RawMaterial extends Product {
    @ManyToMany
    private Set<Supplier> supplierList = new HashSet<>();
    
    public RawMaterial() {
    }

    public RawMaterial(String name, Double price, String description, CustomerCompany ownerCompany, String productType, String unit, 
            Double quantityInOneUnitCapacity, Double qunatityInOneBatch) {
        this.setProductName(name);
        this.setProductPrice(price);
        this.setProductDescription(description);
        this.setOwnerCompany(ownerCompany);
        this.setOwnerCompanyName(ownerCompany.getCompanyName());
        this.setArchivedOrNot(Boolean.FALSE);
        this.setProductType(productType);
        this.setUnit(unit);
        this.setInventoryLevel(0.0);
        this.setQuantityInOneUnitCapacity(quantityInOneUnitCapacity);
        this.setQuantityInOneBatch(qunatityInOneBatch);
    }

    public Set<Supplier> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(Set<Supplier> supplierList) {
        this.supplierList = supplierList;
    }
    
    
}
