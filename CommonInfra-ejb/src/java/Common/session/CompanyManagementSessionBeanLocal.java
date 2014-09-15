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
import javax.ejb.Local;
import util.exception.CompanyExistException;
import util.exception.CompanyNotExistException;
import util.exception.DepartmentExistException;
import util.exception.DepartmentNotExistException;
/**
 *
 * @author chongyangsun
 */
@Local
public interface CompanyManagementSessionBeanLocal {
    public Company retrieveCompany(String companyName) throws CompanyNotExistException;
    public Company retrieveCompany(Long companyId) throws CompanyNotExistException;
    public CustomerCompany createCustomerCompany(String companyName, String companyType, String contactNo) throws CompanyExistException;
    public CustomerCompany updateCustomerCompany(Long companyId, String companyName, String companyType, String contactNo) throws CompanyNotExistException;
    public Boolean deleteCustomerCompany(Long companyId) throws CompanyNotExistException;
    public PartnerCompany createPartnerCompany(String companyName, String companyType, String contactNo) throws CompanyExistException;
    public PartnerCompany updatePartnerCompany(Long companyId, String companyName, String companyType, String contactNo) throws CompanyNotExistException;
    public Boolean deletePartnerCompany(Long companyId) throws CompanyNotExistException;
    public Department createDepartment(String departmentName, Company company) throws DepartmentExistException;
    public Boolean deleteDepartment(String departmentName, Company company) throws DepartmentNotExistException, CompanyNotExistException;
    public void approveRequest(Long companyId, String companyType);
}
