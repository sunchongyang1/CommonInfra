/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Common.session;

import Common.entity.Account;
import Common.entity.Company;
import Common.entity.Department;
import Common.entity.Title;
import java.util.List;
import javax.ejb.Local;
import util.exception.AccountTypeNotExistException;
import util.exception.PasswordNotMatchException;
import util.exception.PasswordTooSimpleException;
import util.exception.TitleExistException;
import util.exception.TitleNotExistException;
import util.exception.UserExistException;
import util.exception.UserNotExistException;

/**
 *
 * @author chongyangsun
 */
@Local
public interface AccountManagementSessionBeanLocal {
    public Account createAccount(String username, String userType, String firstName, String lastName, String email, Department department, Title title, Company company, String companyType);
    public boolean checkLogin(String username, String userType, String password) throws UserNotExistException, PasswordNotMatchException;
    public Account updateAccount(Long id, String accountType, String username, String oldPassword, String newPassword) throws UserExistException, AccountTypeNotExistException, PasswordTooSimpleException;
    public Account deleteAccount(Long accountId, String accountType) throws AccountTypeNotExistException;
    public List<Account> searchAccount(String criteria, String accountType, Company company) throws AccountTypeNotExistException;
    public Account activateAccount(Long id, String accountType) throws AccountTypeNotExistException;
    public Account deactivateAccount(Long id, String accountType) throws AccountTypeNotExistException;
    public boolean resetPassword(Long accountId, String accountType, String email) throws AccountTypeNotExistException;
    public boolean checkPasswordComplexity(String password);
    public Account retrieveAccount(Long accountId, String accountType) throws AccountTypeNotExistException;
    public Title createTitle(String titleName, Department department) throws TitleExistException;
    public Boolean deleteTitle(Long titleId) throws TitleNotExistException;
    //to be added, to return true if the account has been locked or deleted
    public Boolean checkLock(String username, String accountType) throws AccountTypeNotExistException, UserNotExistException;
    public Boolean checkDelete(String username, String accountType) throws AccountTypeNotExistException, UserNotExistException;
    public Account retrieveAccount(String username, String accountType) throws AccountTypeNotExistException, UserNotExistException;
}
