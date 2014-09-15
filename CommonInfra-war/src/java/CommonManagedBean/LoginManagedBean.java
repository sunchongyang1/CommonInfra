/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CommonManagedBean;

import Common.entity.Account;
import Common.session.AccountManagementSessionBeanLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import util.exception.AccountTypeNotExistException;
import util.exception.PasswordNotMatchException;
import util.exception.UserNotExistException;


/**
 *
 * @author Sun Mingjia
 */
@ManagedBean
@RequestScoped
public class LoginManagedBean {
    
    private String userType;
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
    
    public void login() throws PasswordNotMatchException, IOException, AccountTypeNotExistException {
        System.err.println("login");
        
        //set the session attribute
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userType", userType);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", username);
        
        try {
            if ((userType!=null)&&(username!=null)&&(password!=null)&&(amsbl.checkDelete(username,userType)==false)&&(amsbl.checkLock(username,userType)==false)&&attempts<max_attempts) {
                boolean check=amsbl.checkLogin(username, userType, password);
                if (check==true) {
                    ec.redirect("workspaceURL");
                }
                if (check==false) {
                    attempts++;
                    if (max_attempts-attempts>0) {
                    this.displayFaceMessage("You have "+(max_attempts-attempts)+"try before your account is frozen.");
                    }
                }
            }
            else if (amsbl.checkDelete(username,userType)) {
                this.displayFaceMessage("User "+username+"does not exist");
            }
            else if (amsbl.checkLock(username,userType)) {
                this.displayFaceMessage("Account has been locked. Please approach system admin for activation.");
            }
            else if (attempts==max_attempts) {
                Account account=amsbl.retrieveAccount(username, userType);
                amsbl.deactivateAccount(account.getId(), userType);
            }
            else {
                this.displayFaceMessage("Please enter valid input for all fields.");
            }
        }
        catch (UserNotExistException ex) {
            this.displayFaceMessage("User "+username+"does not exist");
        }
    }

    private void displayFaceMessage(String response) {
        FacesMessage msg = new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String getDomain() {
        return userType;
    }

    public void setDomain(String userType) {
        this.userType = userType;
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