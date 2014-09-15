/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Cindylulu
 */
@Entity
public class SystemAdmin extends User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne(cascade={CascadeType.ALL})
    private SystemAdminAccount systemAdminAccount;
    
    public SystemAdmin(){
    }
    
    public SystemAdmin(SystemAdminAccount systemAdminAccount) {
        this.setSystemAdminAccount(systemAdminAccount);
        this.setDeleteOrNot(Boolean.FALSE);
        this.setApprovedOrNot(Boolean.FALSE);
    }
    
    public SystemAdmin(String firstName, String lastName, String email, SystemAdminAccount systemAdminAccount){
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setSystemAdminAccount(systemAdminAccount);
        this.setDeleteOrNot(Boolean.FALSE);
        this.setApprovedOrNot(Boolean.FALSE);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the systemAdminAccount
     */
    public SystemAdminAccount getSystemAdminAccount() {
        return systemAdminAccount;
    }

    /**
     * @param systemAdminAccount the systemAdminAccount to set
     */
    public void setSystemAdminAccount(SystemAdminAccount systemAdminAccount) {
        this.systemAdminAccount = systemAdminAccount;
    }
}
