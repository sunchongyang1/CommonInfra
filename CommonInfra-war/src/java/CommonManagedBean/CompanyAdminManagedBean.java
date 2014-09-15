/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CommonManagedBean;

import Common.entity.Account;
import Common.entity.Company;
import Common.entity.Department;
import Common.entity.Title;
import Common.session.AccountManagementSessionBeanLocal;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Sun Mingjia
 */
@Named(value = "companyAdminManagedBean")
@SessionScoped
public class CompanyAdminManagedBean {
    
    public CompanyAdminManagedBean() {
        
    }
    @EJB
    AccountManagementSessionBeanLocal amsbl;
    
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String userType="User";
    private Department department;
    private Title title;
    private Company company;
    private String companyType;
    FacesContext fc = FacesContext.getCurrentInstance();
    ExternalContext ec = fc.getExternalContext();
    
    public void init() {
        
    }
    
    public void saveUser() {
        System.err.println("Error in saveUser");
        
        Account account=amsbl.createAccount(username, userType, firstName, lastName, email, department, title, company, companyType);
        if (account!=null) {
            this.displayFaceMessage("Account successfully added");
        }
        else {
            this.displayFaceMessage("Account not added");
        }
    }
    
    private void displayFaceMessage(String response) {
        FacesMessage msg = new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }
    
    
}
