/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Common.entity;

import CRM.entity.KeyClient;
import CRM.entity.SalesLead;
import CRM.entity.ServiceCatalog;
import CRM.entity.ServiceContract;
import CRM.entity.ServiceQuotation;
import CRM.entity.YellowPage;
import TMS.entity.Routes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author chongyangsun
 */
@Entity
public class PartnerCompany extends Company implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy="pCompany")
    private List<ServiceCatalog> svcCatalogList; 
    @OneToMany (mappedBy="company")
    private List<KeyClient> keyClientList;   
    @OneToMany(mappedBy="pCompany")
    private List<ServiceQuotation> serviceQuotationList;
    @OneToMany(mappedBy="ownerCompany")
    private List<SalesLead> salesLeadList = new ArrayList<>();
    @OneToMany(mappedBy="pCompany")
    private List<YellowPage> yellowPageList; 
    
    @OneToMany(mappedBy="pCompany")
    private List<Routes> routes;

    @OneToMany(mappedBy = "provider")
    private List<ServiceContract> providingServiceContractList;

    public PartnerCompany() {
    }

    public PartnerCompany(String companyName, String companyType, String contactNo) {
        this.setCompanyName(companyName);
        this.setCompanyType(companyType);
        this.setContactNo(contactNo);
        this.setLockedOrNot(Boolean.FALSE);
        this.setDeleteOrNot(Boolean.FALSE);
        this.setApprovedOrNot(Boolean.FALSE);
    }

    public List<Routes> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Routes> routes) {
        this.routes = routes;
    }

    public List<YellowPage> getYellowPageList() {
        return yellowPageList;
    }

    public void setYellowPageList(List<YellowPage> yellowPageList) {
        this.yellowPageList = yellowPageList;
    }

    public List<ServiceCatalog> getSvcCatalogList() {
        return Collections.unmodifiableList(svcCatalogList);
    }

    public void setSvcCatalogList(List<ServiceCatalog> svcCatalogList) {
        this.svcCatalogList = svcCatalogList;
    }

    public List<SalesLead> getSalesLeadList() {
        return salesLeadList;
    }

    public void setSalesLeadList(List<SalesLead> salesLeadList) {
        this.salesLeadList = salesLeadList;
    }

    public List<KeyClient> getKeyClientList() {
        return keyClientList;
    }

    public void setKeyClientList(List<KeyClient> keyClientList) {
        this.keyClientList = keyClientList;
    }

    public List<ServiceQuotation> getServiceQuotationList() {
        return serviceQuotationList;
    }

    public void setServiceQuotationList(List<ServiceQuotation> serviceQuotationList) {
        this.serviceQuotationList = serviceQuotationList;
    }

    public List<ServiceContract> getProvidingServiceContractList() {
        return providingServiceContractList;
    }

    public void setProvidingServiceContractList(List<ServiceContract> providingServiceContractList) {
        this.providingServiceContractList = providingServiceContractList;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
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
        if (!(object instanceof PartnerCompany)) {
            return false;
        }
        PartnerCompany other = (PartnerCompany) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CRMS.entity.PartnerCompany[ id=" + id + " ]";
    }
    
}
