/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TMS.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author HanXiangyu
 */
@Entity
public class Trucks extends Vehicles implements Serializable {
    private static final long serialVersionUID = 1L;
    @ManyToOne(cascade={CascadeType.PERSIST})
    private Drivers drivers;

    public Trucks() {
    }

    public Trucks(String type, Integer useYear, Long capacity, Long maxDistance, Double transCost, Boolean frige, Boolean avail, Drivers driver) {
        this.setType(type);
        this.setUseYear(useYear);
        this.setCapacity(capacity);
        this.setMaxDistance(maxDistance);
        this.setTransCost(transCost);
        this.setFrige(frige);
        this.setAvail(avail);
        this.setDrivers(driver);
    }

    public Drivers getDrivers() {
        return drivers;
    }

    public void setDrivers(Drivers drivers) {
        this.drivers = drivers;
    }
    
}