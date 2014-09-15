/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CRM.entity;

import Common.entity.Company;
import Common.entity.Location;
import Common.entity.PartnerCompany;
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
public class ServiceQuotation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    @ManyToOne
    private PartnerCompany pCompany;
    @OneToOne
    private Company clientCompany;
    private Timestamp createTime;
    private Timestamp startDate;
    private Timestamp endDate; 
    @OneToOne
    private ServiceContract serviceContract;
    private Boolean approvedOrNot;
    private Boolean archivedOrNot;
    
    private Integer requiredTruckNumber;
    private Integer requiredPlaneNumber;
    private Integer requiredVesselNumber;
    
    private Integer requiredWarehouseCapacity;
    
    private Location source;
    private Location dest;
    private Boolean perishable;
    private Boolean flammable;
    private Boolean pharmaceutical;
    private Boolean highValue;

    public ServiceQuotation() {
    }

    public ServiceQuotation(PartnerCompany pCompany, Company clientCompany, String description, Timestamp startDate, Timestamp endDate, 
            Integer requiredTruckNumber, Integer requiredPlaneNumber, Integer requiredVesselNumber, Integer requiredWarehouseCapacity, Location source, Location dest,
            Boolean perishable, Boolean flammable, Boolean pharmaceutical, Boolean highValue) {
        this.description = description;
        this.pCompany = pCompany;
        this.clientCompany = clientCompany;
        this.startDate = startDate;
        this.endDate = endDate;
        this.requiredTruckNumber = requiredTruckNumber;
        this.requiredPlaneNumber = requiredPlaneNumber;
        this.requiredVesselNumber = requiredVesselNumber;
        this.requiredWarehouseCapacity = requiredWarehouseCapacity;
        this.source = source;
        this.dest = dest;
        this.perishable = perishable;
        this.flammable = flammable;
        this.pharmaceutical = pharmaceutical;
        this.highValue = highValue;
        this.createTime = new Timestamp(System.currentTimeMillis());
        this.approvedOrNot = Boolean.FALSE;
        this.archivedOrNot = Boolean.FALSE;
    }
   
    public ServiceContract getServiceContract() {
        return serviceContract;
    }

    public void setServiceContract(ServiceContract serviceContract) {
        this.serviceContract = serviceContract;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PartnerCompany getpCompany() {
        return pCompany;
    }

    public void setpCompany(PartnerCompany pCompany) {
        this.pCompany = pCompany;
    }

    public Company getClientCompany() {
        return clientCompany;
    }

    public void setClientCompany(Company clientCompany) {
        this.clientCompany = clientCompany;
    }

    public Boolean isApprovedOrNot() {
        return approvedOrNot;
    }

    public void setApprovedOrNot(Boolean approvedOrNot) {
        this.approvedOrNot = approvedOrNot;
    }

    public Boolean isArchivedOrNot() {
        return archivedOrNot;
    }

    public void setArchivedOrNot(Boolean archivedOrNot) {
        this.archivedOrNot = archivedOrNot;
    }

    public Integer getRequiredTruckNumber() {
        return requiredTruckNumber;
    }

    public void setRequiredTruckNumber(Integer requiredTruckNumber) {
        this.requiredTruckNumber = requiredTruckNumber;
    }

    public Integer getRequiredPlaneNumber() {
        return requiredPlaneNumber;
    }

    public void setRequiredPlaneNumber(Integer requiredPlaneNumber) {
        this.requiredPlaneNumber = requiredPlaneNumber;
    }

    public Integer getRequiredVesselNumber() {
        return requiredVesselNumber;
    }

    public void setRequiredVesselNumber(Integer requiredVesselNumber) {
        this.requiredVesselNumber = requiredVesselNumber;
    }

    public Integer getRequiredWarehouseCapacity() {
        return requiredWarehouseCapacity;
    }

    public void setRequiredWarehouseCapacity(Integer requiredWarehouseCapacity) {
        this.requiredWarehouseCapacity = requiredWarehouseCapacity;
    }

    public Location getSource() {
        return source;
    }

    public void setSource(Location source) {
        this.source = source;
    }

    public Location getDest() {
        return dest;
    }

    public void setDest(Location dest) {
        this.dest = dest;
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
        if (!(object instanceof ServiceQuotation)) {
            return false;
        }
        ServiceQuotation other = (ServiceQuotation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CRM.entity.ServiceQuotation[ id=" + id + " ]";
    }
    
}
