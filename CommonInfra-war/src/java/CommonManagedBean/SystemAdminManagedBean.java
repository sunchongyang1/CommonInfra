/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CommonManagedBean;

import Common.entity.Account;
import Common.entity.Company;
import Common.entity.CompanyAdmin;
import Common.entity.CompanyAdminAccount;
import Common.entity.CustomerCompany;
import Common.entity.Department;
import Common.entity.PartnerCompany;
import Common.entity.Title;
import Common.session.AccountManagementSessionBeanLocal;
import Common.session.CompanyManagementSessionBeanLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
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
@Named(value = "systemAdminManagedBean")
@SessionScoped
public class SystemAdminManagedBean implements Serializable{
    
    
    public SystemAdminManagedBean() {
        
    }
    
    @EJB
    AccountManagementSessionBeanLocal amsbl;
    @EJB
    CompanyManagementSessionBeanLocal cmsbl;
    
    private String welcome="Have a nice day at work!";
    private String contactNo;
    private Company company;
    private String companyType;
    private CompanyAdmin companyAdmin;
    private CompanyAdminAccount companyAdminAccount;
    private Department department;
    private Title title;
    private List<Department> departmentList = new ArrayList<>();
    private List<Title> titleList = new ArrayList<>();
    FacesContext fc = FacesContext.getCurrentInstance();
    ExternalContext ec = fc.getExternalContext();
    
    @PostConstruct
    public void init() {    
        setCompanyAdmin(new CompanyAdmin());
        companyAdminAccount=new CompanyAdminAccount();
        company=new Company();
        companyType=null;
        department=new Department();
        title=new Title();
    }
    
    /**
     *
     * @throws CompanyExistException
     * @throws DepartmentExistException
     * @throws TitleExistException
     */
    
    public void saveCompany() {
        System.out.println("Hello world");
        System.err.println("Error in save company");
        String response = null;
        
        try {
        //create customer company
        if (getCompanyType().equals("1PL")) {
            setCompany(cmsbl.createCustomerCompany(getCompany().getCompanyName(), getCompanyType(), contactNo));
            response = "Customer Company successfully added";
            this.displayFaceMessage(response);
        }
        //create partner company
        if ((getCompanyType().equals("2PL")|(getCompanyType().equals("3PL"))|getCompanyType().equals("4PL"))|(getCompanyType().equals("5PL"))) {
            setCompany(cmsbl.createPartnerCompany(getCompany().getCompanyName(), getCompanyType(), contactNo));
            response = "Partner Company successfully added";
            this.displayFaceMessage(response);
        }
        //trigger creation of department
        if (response !=null) {
            //create default department
            departmentList.add(cmsbl.createDepartment("Sales and Marketing Department", getCompany()));
            departmentList.add(cmsbl.createDepartment("Transportation Department", getCompany()));
            departmentList.add(cmsbl.createDepartment("Warehouse Department", getCompany()));
            departmentList.add(cmsbl.createDepartment("Manufacturing Department", getCompany()));
            departmentList.add(cmsbl.createDepartment("Account Management", getCompany()));
            
            //create default title
            this.saveTitle(departmentList);
        }
        else {
            this.displayFaceMessage("No response from server. Company not added");
        }
        this.saveCompanyAdmin();
        } catch (CompanyExistException | DepartmentExistException | TitleExistException ex) {
            Logger.getLogger(SystemAdminManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        amsbl.createAccount(getCompanyAdminAccount().getUsername(), userType, getCompanyAdmin().getFirstName(), getCompanyAdmin().getLastName(), getCompanyAdmin().getEmail(), department, null, getCompany(), getCompany().getCompanyType());
    }
    
    private void displayFaceMessage(String response) {
        FacesMessage msg = new FacesMessage(response);
        FacesContext.getCurrentInstance().addMessage(null, msg);
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * @return the companyAdminAccount
     */
    public CompanyAdminAccount getCompanyAdminAccount() {
        return companyAdminAccount;
    }

    /**
     * @param companyAdminAccount the companyAdminAccount to set
     */
    public void setCompanyAdminAccount(CompanyAdminAccount companyAdminAccount) {
        this.companyAdminAccount = companyAdminAccount;
    }

    /**
     * @return the companyAdmin
     */
    public CompanyAdmin getCompanyAdmin() {
        return companyAdmin;
    }

    /**
     * @param companyAdmin the companyAdmin to set
     */
    public void setCompanyAdmin(CompanyAdmin companyAdmin) {
        this.companyAdmin = companyAdmin;
    }

    /**
     * @return the companyType
     */
    public String getCompanyType() {
        return companyType;
    }

    /**
     * @param companyType the companyType to set
     */
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }
}
