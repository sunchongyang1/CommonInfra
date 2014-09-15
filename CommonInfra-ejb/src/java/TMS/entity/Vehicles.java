/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TMS.entity;

import Common.entity.Company;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author HanXiangyu
 */
@Entity
public abstract class Vehicles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vehiclesId;
    private String type;
    private Integer useYear;
    private Boolean avail;
    private Long capacity;
    private Long maxDistance;
    private Double transCost;
    private Boolean frige;
    @ManyToOne
    private Company ownerCompany;
    
    /**
     * @return the vehiclesId
     */
    public Long getVehiclesId() {
        return vehiclesId;
    }

    /**
     * @param vehiclesId the vehiclesId to set
     */
    public void setVehiclesId(Long vehiclesId) {
        this.vehiclesId = vehiclesId;
    }

    /**
     * @return the capacity
     */
    public Long getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the maxDistance
     */
    public Long getMaxDistance() {
        return maxDistance;
    }

    /**
     * @param maxDistance the maxDistance to set
     */
    public void setMaxDistance(Long maxDistance) {
        this.maxDistance = maxDistance;
    }

    /**
     * @return the transCost
     */
    public Double getTransCost() {
        return transCost;
    }

    /**
     * @param transCost the transCost to set
     */
    public void setTransCost(Double transCost) {
        this.transCost = transCost;
    }

    /**
     * @return the frige
     */
    public Boolean getFrige() {
        return frige;
    }

    /**
     * @param frige the frige to set
     */
    public void setFrige(Boolean frige) {
        this.frige = frige;
    }

    /**
     * @return the avail
     */
    public Boolean getAvail() {
        return avail;
    }

    /**
     * @param avail the avail to set
     */
    public void setAvail(Boolean avail) {
        this.avail = avail;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    public Integer getUseYear() {
        return useYear;
    }

    public void setUseYear(Integer useYear) {
        this.useYear = useYear;
    }

    public Company getOwnerCompany() {
        return ownerCompany;
    }

    public void setOwnerCompany(Company ownerCompany) {
        this.ownerCompany = ownerCompany;
    }

}
