/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CommonManagedBean;

import Common.entity.Company;
import Common.entity.Department;
import Common.entity.Title;
import Common.session.AccountManagementSessionBeanLocal;
import Common.session.CompanyManagementSessionBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import util.exception.CompanyExistException;
import util.exception.DepartmentExistException;
import util.exception.TitleExistException;

/**
 *
 * @author Sun Mingjia
 */
@Named(value ="systemAdminManagedBean")
@SessionScoped
public class SystemAdminManagedBean {
    
    public SystemAdminManagedBean() {       
    }
    
    @EJB
    AccountManagementSessionBeanLocal amsbl;
    @EJB
    CompanyManagementSessionBeanLocal cmsbl;
    
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String companyName;
    private String companyType;
    private String contactNo;
    private Company company;
    private Department department;
    private Title title;
    private List<Department> departmentList = new ArrayList<>();
    private List<Title> titleList = new ArrayList<>();
    FacesContext fc = FacesContext.getCurrentInstance();
    ExternalContext ec = fc.getExternalContext();
    
    public void init() {
    }
    
    public void saveCompany() throws CompanyExistException, DepartmentExistException, TitleExistException {
        System.err.println("Error in save company");
        String response = null;
        
        //create customer company
        if (companyType.equals("1PL")) {
            company = cmsbl.createCustomerCompany(companyName, companyType, contactNo);
            response = "Customer Company successfully added";
        }
        //create partner company
        if ((companyType.equals("2PL")|(companyType.equals("3PL"))|companyType.equals("4PL"))|(companyType.equals("5PL"))) {
            company = cmsbl.createPartnerCompany(companyName, companyType, contactNo);
            response = "Partner Company successfully added";
        }
        //trigger creation of department
        if (response !=null) {
            //create default department
            departmentList.add(cmsbl.createDepartment("Sales and Marketing Department", company));
            departmentList.add(cmsbl.createDepartment("Transportation Department", company));
            departmentList.add(cmsbl.createDepartment("Warehouse Department", company));
            departmentList.add(cmsbl.createDepartment("Manufacturing Department", company));
            departmentList.add(cmsbl.createDepartment("Account Management", company));
            
            //create default title
            this.saveTitle(departmentList);
        }
        else {
            this.displayFaceMessage("No response from server. Company not added");
        }
        this.saveCompanyAdmin();
    }
    
    private void saveTitle(List departmentList) throws TitleExistException, DepartmentExistException {
        System.err.println("Error in save title");
        
        for (int i=0; i<departmentList.size();i++) {
            amsbl.createTitle("Manager", (Department)departmentList.get(i));
            amsbl.createTitle("Staff", (Department)departmentList.get(i));
        }
    }
    
    private void saveCompanyAdmin() throws DepartmentExistException {
        System.err.println("Error in save companyAdmin");
        
        department=departmentList.get(4);
        String userType = "Admin";   
        amsbl.createAccount(username, userType, firstName, lastName, email, department, null, company, companyType);
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
    
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }    
}
