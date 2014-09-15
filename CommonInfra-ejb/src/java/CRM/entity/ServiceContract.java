/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CRM.entity;

import Common.entity.Company;
import Common.entity.PartnerCompany;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author HanXiangyu
 */
@Entity
public class ServiceContract implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp creationTime;
    @OneToOne
    private ServiceQuotation serviceQuotation;
    @OneToMany(mappedBy="serviceContract")
    private List<ServiceOrder> serviceOrder;
    @ManyToOne
    private Company client;
    @ManyToOne
    private PartnerCompany provider;

    public ServiceContract() {
    }

    public ServiceContract(ServiceQuotation serviceQuotation) {
        this.serviceQuotation = serviceQuotation;
        this.client = serviceQuotation.getClientCompany();
        this.provider = serviceQuotation.getpCompany();
    }

    
    public Company getClient() {
        return client;
    }

    public void setClient(Company Client) {
        this.client = Client;
    }

    public PartnerCompany getProvider() {
        return provider;
    }

    public void setProvider(PartnerCompany provider) {
        this.provider = provider;
    }


    public List<ServiceOrder> getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(List<ServiceOrder> serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public ServiceQuotation getServiceQuotation() {
        return serviceQuotation;
    }

    public void setServiceQuotation(ServiceQuotation serviceQuotation) {
        this.serviceQuotation = serviceQuotation;
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
        if (!(object instanceof ServiceContract)) {
            return false;
        }
        ServiceContract other = (ServiceContract) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CRM.entity.ServiceContract[ id=" + id + " ]";
    }
    
}
