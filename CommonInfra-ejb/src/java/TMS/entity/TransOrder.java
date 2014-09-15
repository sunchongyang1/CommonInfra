/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TMS.entity;

import CRM.entity.ServiceOrder;
import Common.entity.Location;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author HanXiangyu
 */
@Entity
public class TransOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transOrderId;    
    private Timestamp recvTime;
    @Embedded
    private Location startPoint;
    @Embedded
    private Location endPoint;   
    @Embedded 
    private TimeSlot deliverTime;    
    private String trackingNumber;    
    private Location currentLocation;
    @OneToMany(cascade={CascadeType.PERSIST})
    private List<Routes> routes;
    @OneToOne
    private ServiceOrder serviceOrder;
    private Boolean fulfilledOrNot;
    
    

    public TransOrder() {
    }

    public TransOrder(ServiceOrder serviceOrder) {
        this.recvTime = new Timestamp(System.currentTimeMillis());
        this.deliverTime.setStart(serviceOrder.getStartTime());
        this.deliverTime.setEnd(serviceOrder.getEndTime());
        this.startPoint = serviceOrder.getSourceLoc();
        this.endPoint = serviceOrder.getDestLoc();
        this.serviceOrder = serviceOrder;
        this.fulfilledOrNot = Boolean.FALSE;
    }
    
    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public TimeSlot getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(TimeSlot deliverTime) {
        this.deliverTime = deliverTime;
    }

    /**
     * @return the routes
     */
    public List<Routes> getRoutes() {
        return routes;
    }

    /**
     * @param routes the routes to set
     */
    public void setRoutes(List<Routes> routes) {
        this.routes = routes;
    }

    public Location getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Location startPoint) {
        this.startPoint = startPoint;
    }

    public Location getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Location endPoint) {
        this.endPoint = endPoint;
    }

    /**
     * @return the transOrderId
     */
    public Long getTransOrderId() {
        return transOrderId;
    }

    /**
     * @param transOrderId the transOrderId to set
     */
    public void setTransOrderId(Long transOrderId) {
        this.transOrderId = transOrderId;
    }

    /**
     * @return the recvTime
     */
    public Timestamp getRecvTime() {
        return recvTime;
    }

    /**
     * @param recvTime the recvTime to set
     */
    public void setRecvTime(Timestamp recvTime) {
        this.recvTime = recvTime;
    }

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public Boolean isFulfilledOrNot() {
        return fulfilledOrNot;
    }

    public void setFulfilledOrNot(Boolean fulfilledOrNot) {
        this.fulfilledOrNot = fulfilledOrNot;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.transOrderId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TransOrder other = (TransOrder) obj;
        if (!Objects.equals(this.transOrderId, other.transOrderId)) {
            return false;
        }
        return true;
    }
    
}
    
