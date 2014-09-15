/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CommonManagedBean;

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
    private final int max_attempts=5;
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
            if ((domain!=null)&&(username!=null)&&(password!=null)) {
                boolean check=amsbl.checkLogin(username, domain, password);
                if (check==true) {
                    ec.redirect("workspaceURL");
                }
                if (check==false) {
                    this.displayFaceMessage("User "+username+" does not exist!");
                }
            }
            else {
                this.displayFaceMessage("Please enter valid input for all fields.");
            }
        }
        catch (Exception ex) {          
        }
    }

    private void displayFaceMessage(String response) {
        FacesMessage msg = new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
    
    
}
