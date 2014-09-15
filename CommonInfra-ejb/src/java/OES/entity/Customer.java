/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OES.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Embeddable;

/**
 *
 * @author songhan
 */
@Embeddable
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private Set<SalesQuotation> salesQuotations = new HashSet<>();

    public Customer() {
    }

    public Customer(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<SalesQuotation> getSalesQuotations() {
        return salesQuotations;
    }

    public void setSalesQuotations(Set<SalesQuotation> salesQuotations) {
        this.salesQuotations = salesQuotations;
    }

}
