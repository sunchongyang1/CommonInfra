/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MRPII.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author songhan
 */
@Entity
public class Supplier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;  //Unique
    @ManyToMany(mappedBy="supplierList")
    private Set<RawMaterial> materialList = new HashSet<>();
    private HashMap<RawMaterial, Integer> lagTimeMap = new HashMap<>();
    private Boolean archivedOrNot;

    public Supplier() {
    }

    public Supplier(String name) {
        this.name = name;
        this.archivedOrNot = Boolean.FALSE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<RawMaterial> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(Set<RawMaterial> materialList) {
        this.materialList = materialList;
    }

    public HashMap<RawMaterial, Integer> getLagTimeMap() {
        return lagTimeMap;
    }

    public void setLagTimeMap(HashMap<RawMaterial, Integer> lagTimeMap) {
        this.lagTimeMap = lagTimeMap;
    }

    public Boolean isArchivedOrNot() {
        return archivedOrNot;
    }

    public void setArchivedOrNot(Boolean archivedOrNot) {
        this.archivedOrNot = archivedOrNot;
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
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MRPII.Supplier[ id=" + id + " ]";
    }
}
