/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Common.session;

import Common.entity.Company;
import Common.entity.CustomerCompany;
import Common.entity.Department;
import Common.entity.PartnerCompany;
import Common.entity.Title;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.CompanyExistException;
import util.exception.CompanyNotExistException;
import util.exception.DepartmentExistException;
import util.exception.DepartmentNotExistException;

/**
 *
 * @author chongyangsun
 */
@Stateless
public class CompanyManagementSessionBean implements CompanyManagementSessionBeanLocal {
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Company retrieveCompany(String companyName) throws CompanyNotExistException{
        CustomerCompany ownerCompany = null;
        Query query = em.createQuery("select c from CustomerCompany c where c.companyName=?1 and c.lockedOrNot=FALSE and c.deleteOrNot=FALSE");
        query.setParameter(1, companyName);
        ArrayList<CustomerCompany> ownerCompanyList = new ArrayList(query.getResultList());
        if(ownerCompanyList.isEmpty()){
            throw new CompanyNotExistException("Company "+companyName+" does not exist!");
        }        
        ownerCompany = ownerCompanyList.get(0);  
        return ownerCompany;
    }
    
    @Override
    public Company retrieveCompany(Long companyId) throws CompanyNotExistException {
//        CustomerCompany ownerCompany = null;
//        Query query = em.createQuery("select c from CustomerCompany c where c.companyName=?1 and c.lockedOrnot=FALSE");
//        query.setParameter(1, companyName);
//        ArrayList<CustomerCompany> ownerCompanyList = new ArrayList(query.getResultList());
//        if(ownerCompanyList.isEmpty()){
//            throw new CompanyNotExistException("Company "+companyName+" does not exist!");
//        }        
//        ownerCompany = ownerCompanyList.get(0);  
//        return ownerCompany;
        
        Company company = em.find(Company.class, companyId);
        System.out.println("The retrieved Company is " + company.getCompanyName());
        if(company == null) {
            throw new CompanyNotExistException("Company id: " + companyId + " does not exist!");
        }
        return company;
    }
    
    @Override
    public CustomerCompany createCustomerCompany(String companyName, String companyType, String contactNo) throws CompanyExistException {
        CustomerCompany customerCompany = null;
        Query query = em.createQuery("select c from CustomerCompany c where c.companyName=?1");
        query.setParameter(1, companyName);
        ArrayList<CustomerCompany> customerCompanyList = new ArrayList(query.getResultList());
        if(!customerCompanyList.isEmpty()){
            customerCompany = customerCompanyList.get(0);
            if(customerCompany.getDeleteOrNot()) {
                throw new CompanyExistException("Company "+companyName+" is already exist and customer company is DELETED!");
            }
            throw new CompanyExistException("Company "+companyName+" is already exist!");
        }
        
        customerCompany = new CustomerCompany(companyName, companyType, contactNo);
        em.persist(customerCompany);
        em.flush();
        System.out.println("In creating customer company - " + customerCompany.getCompanyName() + ", account ID: " + customerCompany.getId());
        return customerCompany;
    }
    
    @Override
    public CustomerCompany updateCustomerCompany(Long companyId, String companyName, String companyType, String contactNo) throws CompanyNotExistException {
//        CustomerCompany customerCompany = em.find(CustomerCompany.class, companyId);
        Query query = em.createQuery("select c from CustomerCompany c where c.id=?1 and c.lockedOrNot=FALSE and c.deleteOrNot=FALSE");
        query.setParameter(1, companyId);
        ArrayList<CustomerCompany> customerCompanyList = new ArrayList(query.getResultList());
        if(customerCompanyList.isEmpty()){
            throw new CompanyNotExistException("Company "+companyName+" does not exist!");
        }
        CustomerCompany customerCompany = customerCompanyList.get(0);
        if (!customerCompany.getCompanyName().equals(companyName)) {
            customerCompany.setCompanyName(companyName);
        }
        if (!customerCompany.getCompanyType().equals(companyType)) {
            customerCompany.setCompanyType(companyType);
        }
        if (!customerCompany.getContactNo().equals(contactNo)) {
            customerCompany.setContactNo(contactNo);
        }
        em.merge(customerCompany);
        em.flush();
        System.out.println("In updating customer company - " + customerCompany.getCompanyName() + ", account ID: " + customerCompany.getId());
        return customerCompany;
    }
    
    @Override
    public Boolean deleteCustomerCompany(Long companyId) throws CompanyNotExistException {
//        CustomerCompany customerCompany = em.find(CustomerCompany.class, companyId);
        Query query = em.createQuery("select c from CustomerCompany c where c.id=?1");
        query.setParameter(1, companyId);
        ArrayList<CustomerCompany> customerCompanyList = new ArrayList(query.getResultList());
        if(customerCompanyList.isEmpty()){
            throw new CompanyNotExistException("Company "+companyId+" does not exist!");
        }
        CustomerCompany customerCompany = customerCompanyList.get(0);
        try {
            customerCompany.setDeleteOrNot(Boolean.TRUE);
            em.merge(customerCompany);
            em.flush();
            System.out.println("In deleting customer company - " + customerCompany.getCompanyName() + ", account ID: " + customerCompany.getId());
            return Boolean.TRUE;
        } catch(Exception e) {
            return Boolean.FALSE;
        }
    }
    
    @Override
    public PartnerCompany createPartnerCompany(String companyName, String companyType, String contactNo) throws CompanyExistException {
        PartnerCompany partnerCompany = null;
        Query query = em.createQuery("select c from CustomerCompany c where c.companyName=?1");
        query.setParameter(1, companyName);
        ArrayList<PartnerCompany> partnerCompanyList = new ArrayList(query.getResultList());
        if(!partnerCompanyList.isEmpty()){
            partnerCompany = partnerCompanyList.get(0);
            if(partnerCompany.getDeleteOrNot()) {
                throw new CompanyExistException("Company "+companyName+" is already exist and partner company is DELETED!"); 
            }
            throw new CompanyExistException("Company "+companyName+" is already exist!");
        }
        
        partnerCompany = new PartnerCompany(companyName, companyType, contactNo);
        em.persist(partnerCompany);
        em.flush();
        System.out.println("In creating partner company - " + partnerCompany.getCompanyName() + ", account ID: " + partnerCompany.getId());
        return partnerCompany;
    }
    
    @Override
    public PartnerCompany updatePartnerCompany(Long companyId, String companyName, String companyType, String contactNo) throws CompanyNotExistException {
//        CustomerCompany customerCompany = em.find(CustomerCompany.class, companyId);
        Query query = em.createQuery("select c from CustomerCompany c where c.id=?1 and c.lockedOrNot=FALSE and c.deleteOrNot=FALSE");
        query.setParameter(1, companyId);
        ArrayList<PartnerCompany> partnerCompanyList = new ArrayList(query.getResultList());
        if(partnerCompanyList.isEmpty()){
            throw new CompanyNotExistException("Company "+companyName+" does not exist!");
        }
        PartnerCompany partnerCompany = partnerCompanyList.get(0);
        if (!partnerCompany.getCompanyName().equals(companyName)) {
            partnerCompany.setCompanyName(companyName);
        }
        if (!partnerCompany.getCompanyType().equals(companyType)) {
            partnerCompany.setCompanyType(companyType);
        }
        if (!partnerCompany.getContactNo().equals(contactNo)) {
            partnerCompany.setContactNo(contactNo);
        }
        em.merge(partnerCompany);
        em.flush();
        System.out.println("In updating partner company - " + partnerCompany.getCompanyName() + ", account ID: " + partnerCompany.getId());
        return partnerCompany;
    }
    
    @Override
    public Boolean deletePartnerCompany(Long companyId) throws CompanyNotExistException {
//        CustomerCompany customerCompany = em.find(CustomerCompany.class, companyId);
        Query query = em.createQuery("select c from CustomerCompany c where c.id=?1");
        query.setParameter(1, companyId);
        ArrayList<PartnerCompany> partnerCompanyList = new ArrayList(query.getResultList());
        if(partnerCompanyList.isEmpty()){
            throw new CompanyNotExistException("Company "+companyId+" does not exist!");
        }
        PartnerCompany partnerCompany = partnerCompanyList.get(0);
        try {
            partnerCompany.setDeleteOrNot(Boolean.TRUE);
            em.merge(partnerCompany);
            em.flush();
            System.out.println("In deleting customer company - " + partnerCompany.getCompanyName() + ", account ID: " + partnerCompany.getId());
            return Boolean.TRUE;
        } catch(Exception e) {
            return Boolean.FALSE;
        }
    }
    
    @Override
    public Department createDepartment(String departmentName, Company company) throws DepartmentExistException {
        Query query = em.createQuery("select d from Department d where d.departmentName=?1 and d.company=?2");
        query.setParameter(1, departmentName);
        query.setParameter(2, company);
        ArrayList<Department> departmentList = new ArrayList(query.getResultList());
        if(departmentList.isEmpty()) {
            System.out.println("List is empty");
        }
        if(!departmentList.isEmpty()){
            System.out.println("List is not empty");
            throw new DepartmentExistException("Department " + departmentName + " for company " + company.getCompanyName() + " is already exist");
        }
        Department department = new Department(departmentName, company);
        em.persist(department);
        System.out.println("In creating department - " + department.getDepartmentName() + " for company " + company.getCompanyName());
        return department;
    }
    
    @Override
    public Boolean deleteDepartment(String departmentName, Company company) throws DepartmentNotExistException, CompanyNotExistException {
        if(em.find(Company.class, company.getId()) == null)
            throw new CompanyNotExistException("Company " + company.getCompanyName() + " does not exist!");
        Query query = em.createQuery("select d from Department d where d.departmentName=?1 and d.company=?2");
        query.setParameter(1, departmentName);
        query.setParameter(2, company.getId());
        ArrayList<Department> departmentList = new ArrayList(query.getResultList());
        if(!departmentList.isEmpty()){
            throw new DepartmentNotExistException("Department " + departmentName + " for company " + company.getCompanyName() + " does not already exist");
        }
        Department department = em.find(Department.class, departmentList.get(0).getId());
        try {
        em.remove(department);
        em.flush();
        System.out.println("In deleting department - " + department.getDepartmentName() + " for company " + company.getCompanyName());
        return Boolean.TRUE;
        } catch(Exception e) {
            return Boolean.FALSE;
        }
    }
    
    @Override
    public void approveRequest(Long companyId, String companyType) {
        if(companyType.equals("1PL")) {
            CustomerCompany customerCompany = em.find(CustomerCompany.class, companyId);
            customerCompany.setApprovedOrNot(true);
            List<Department> departmentList = customerCompany.getDepartment();
            for(Department d: departmentList) {
                d.setApprovedOrNot(true);
                List<Title> titleList = d.getTitles();
                for(Title t: titleList) {
                    t.setApprovedOrNot(true);
                    if(!t.getUser().isEmpty()) {
                        t.getUser().get(0).setApprovedOrNot(true);
                    }
                }
            }
        }
    }
}
