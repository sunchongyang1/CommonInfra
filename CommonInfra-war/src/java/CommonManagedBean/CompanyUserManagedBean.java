/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CommonManagedBean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Sun Mingjia
 */
@Named(value = "companyUserManagedBean")
@SessionScoped
public class CompanyUserManagedBean implements Serializable {

    /**
     * Creates a new instance of CompanyUserManagedBean
     */
    private String welcome="Welcome to MerLION Platform!";
    
    public CompanyUserManagedBean() {
    }
    public void logout() {
        
    }
}
