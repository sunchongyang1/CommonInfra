/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Common.entity;

import CRM.entity.WarehouseOrder;
import MRPII.entity.RawMaterial;
import OES.entity.FinishedGood;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author chongyangsun
 */
@Entity
public class CustomerCompany extends Company implements Serializable {
    @OneToMany(mappedBy="ownerCompany")
    private Set<FinishedGood> productList = new HashSet<>();
    @OneToMany(mappedBy="ownerCompany")
    private Set<RawMaterial> rawMaterialList = new HashSet<>();

    public CustomerCompany() {
    }

    public CustomerCompany(String companyName, String companyType, String contactNo) {
        this.setCompanyName(companyName);
        this.setCompanyType(companyType);
        this.setContactNo(contactNo);
        this.setLockedOrNot(Boolean.FALSE);
        this.setDeleteOrNot(Boolean.FALSE);
        this.setApprovedOrNot(Boolean.FALSE);
    }

    public Set<FinishedGood> getProductList() {
        return productList;
    }
    
    public void setProductList(Set<FinishedGood> productList) {
        this.productList = productList;
    }

    public Set<RawMaterial> getRawMaterialList() {
        return rawMaterialList;
    }

    public void setRawMaterialList(Set<RawMaterial> rawMaterialList) {
        this.rawMaterialList = rawMaterialList;
    }            
}
