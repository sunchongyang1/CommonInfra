/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CommonManagedBean;

import Common.session.AccountManagementSessionBeanLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Sun Mingjia
 */
@Named(value = "commonFuncManagedBean")
@SessionScoped
public class CommonFuncManagedBean implements Serializable {

    /**
     * Creates a new instance of CommonFuncManagedBean
     */
    public CommonFuncManagedBean() {
    }
    
    @EJB
    AccountManagementSessionBeanLocal amsbl;
    
    private String username;
    private String password;
    FacesContext fc = FacesContext.getCurrentInstance();
    ExternalContext ec = fc.getExternalContext();
    
    public void init() {
        
    }
    public void resetPassword() {
        
    }
    private void displayFaceMessage(String response) {
        FacesMessage msg = new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
