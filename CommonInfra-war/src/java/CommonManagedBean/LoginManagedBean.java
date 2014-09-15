/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CommonManagedBean;

import Common.entity.CompanyAdmin;
import Common.entity.CompanyUser;
import Common.entity.SystemAdmin;
import Common.session.AccountManagementSessionBeanLocal;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


/**
 *
 * @author Sun Mingjia
 */
public class LoginManagedBean {
    
    private String domain;
    private String username;
    private String password;
    private int attempts;
    private final int max_attempts=3;
    FacesContext fc = FacesContext.getCurrentInstance();
    ExternalContext ec = fc.getExternalContext();
    @EJB
    AccountManagementSessionBeanLocal amsbl;
    
    public LoginManagedBean() {
        attempts=0;
    }
    
    public void login() {
        System.err.println("login");
        
        //set the session attribute
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("domain", domain);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", username);
        
        try {
            if (domain.equals("SystemAdmin")) {
                if (username!=null) {
                    
                }
                else {
                    this.displayFaceMessage("Please input username and password.");
                }
            }
            if (domain.equals("CompanyAdmin")) {
                if (username!=null) {
                    
                }
                else {
                    this.displayFaceMessage("Please input username and password.");
                }
            }
            if (domain.equals("CompanyUser")) {
                if (username!=null) {
                    
                }
                else {
                    this.displayFaceMessage("Please input username and password.");
                }
            }
        }
        catch (Exception ex) {
           
        }
    }
    private void displayFaceMessage(String response) {
        FacesMessage msg = new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
