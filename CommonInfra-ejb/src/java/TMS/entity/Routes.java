/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TMS.entity;

import Common.entity.Location;
import Common.entity.PartnerCompany;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author HanXiangyu
 */
@Entity
public class Routes implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long routesId;
    
    private Location startOfRoute;
    private Location destOfRoute;
    private String mode;
    private Timestamp startTime;
    private Timestamp endTime;
    
    @OneToMany(cascade={CascadeType.PERSIST})
    private Collection<Vehicles> vehicles = new ArrayList<Vehicles>();
    
    @ManyToOne
    private PartnerCompany pCompany;

    public Routes() {
    }

    public Routes(Location startOfRoute, Location destOfRoute) {
        this.startOfRoute = startOfRoute;
        this.destOfRoute = destOfRoute;
    }

    public PartnerCompany getpCompany() {
        return pCompany;
    }

    public void setpCompany(PartnerCompany pCompany) {
        this.pCompany = pCompany;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getRoutesId() != null ? getRoutesId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Routes)) {
            return false;
        }
        Routes other = (Routes) object;
        if ((this.getRoutesId() == null && other.getRoutesId() != null) || (this.getRoutesId() != null && !this.routesId.equals(other.routesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.RoutesEntity[ routesId=" + getRoutesId() + " ]";
    }

    /**
     * @return the routesId
     */
    public Long getRoutesId() {
        return routesId;
    }

    /**
     * @param routesId the routesId to set
     */
    public void setRoutesId(Long routesId) {
        this.routesId = routesId;
    }

    /**
     * @return the startOfRoute
     */
    public Location getStartOfRoute() {
        return startOfRoute;
    }

    /**
     * @param startOfRoute the startOfRoute to set
     */
    public void setStartOfRoute(Location startOfRoute) {
        this.startOfRoute = startOfRoute;
    }

    /**
     * @return the destOfRoute
     */
    public Location getDestOfRoute() {
        return destOfRoute;
    }

    /**
     * @param destOfRoute the destOfRoute to set
     */
    public void setDestOfRoute(Location destOfRoute) {
        this.destOfRoute = destOfRoute;
    }

    /**
     * @return the mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * @return the startTime
     */
    public Timestamp getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public Timestamp getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the vehicles
     */
    public Collection<Vehicles> getVehicles() {
        return vehicles;
    }

    /**
     * @param vehicles the vehicles to set
     */
    public void setVehicles(Collection<Vehicles> vehicles) {
        this.vehicles = vehicles;
    }    
}
