/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Common.session;

import Common.entity.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.AccountTypeNotExistException;
import util.exception.PasswordNotMatchException;
import util.exception.PasswordTooSimpleException;
import util.exception.TitleExistException;
import util.exception.TitleNotExistException;
import util.exception.UserExistException;
import util.exception.UserNotExistException;
import util.session.GeneratePassword;
import util.session.GoogleMail;

/**
 *
 * @author chongyangsun
 */
@Stateless
public class AccountManagementSessionBean implements AccountManagementSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private static final Random RANDOM = new SecureRandom();
    /**
     * Length of password.
     *
     * @see #passwordAssign()
     */
    public static final int SALT_LENGTH = 8;
    @PersistenceContext
    private EntityManager em;
    private GoogleMail gm;

    @Override
    public Account createAccount(String username, String userType, String firstName, String lastName, String email, Department department, Title title, Company company, String companyType) {
        String salt = "";
        String letters = "0123456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789＃＄@";

        for (int i = 0; i < SALT_LENGTH; i++) {
            int index = (int) (RANDOM.nextDouble() * letters.length());
            salt += letters.substring(index, index + 1);
        }
        String password = GeneratePassword.createPassword();
        
        //send password to user through email
        try {
            SendEmail(firstName, lastName, username, email, password, company.getCompanyName());
        } catch (MessagingException ex) {
            System.out.println("Error sending email.");
            Logger.getLogger(AccountManagementSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        password = passwordHash(password + salt);

        if(companyType.equals("1PL") || companyType.equals("2PL") || companyType.equals("3PL") || companyType.equals("4PL")) {
            //customer company account creation
            if(userType.equals("Admin")) {
                //admin account creation --by system admin
                Query query = em.createQuery("SELECT c FROM CompanyAdminAccount c WHERE c.username = :user");
                query.setParameter("user", username);
                List<CompanyAdminAccount> companyAdminAccountList = query.getResultList();
                if(!companyAdminAccountList.isEmpty()) {
                    System.out.println("User " + username + " exists!");
                    return null;//user exist, please change username
                }
                CompanyAdminAccount companyAdminAccount = new CompanyAdminAccount(username, password, salt, department, company);
                CompanyAdmin companyAdmin = new CompanyAdmin(firstName, lastName, email, companyAdminAccount);
                title.getUser().add(companyAdmin);
                companyAdmin.getTitles().add(title);
                em.persist(companyAdminAccount);
                em.persist(companyAdmin);
                em.persist(title);
                System.out.println("In creating company admin account, account ID: " + companyAdminAccount.getId());
                return companyAdminAccount;
            } else {
                //user account creation --by system admin or company admin
                Query query = em.createQuery("SELECT c FROM CompanyUserAccount c WHERE c.username = :user");
                query.setParameter("user", username);
                List<CompanyUserAccount> companyUserAccountList = query.getResultList();
                if(!companyUserAccountList.isEmpty()) {
                    System.out.println("User " + username + " exists!");
                    return null;//user exist, please change username
                }
                CompanyUserAccount companyUserAccount = new CompanyUserAccount(username, password, salt, department, company);
                CompanyUser companyUser = new CompanyUser(companyUserAccount);
                title.getUser().add(companyUser);
                companyUser.getTitles().add(title);
                em.persist(companyUserAccount);
                em.persist(companyUser);
                em.persist(title);
                System.out.println("In creating company user account, account ID: " + companyUserAccount.getId());
                return companyUserAccount;
            }
        } else if(companyType.equals("5PL")) {
            //merlion user account creation
            if(userType.equals("Admin")) {
                //system admin account creation --by system admin
                Query query = em.createQuery("SELECT c FROM SystemAdminAccount c WHERE c.username = :user");
                query.setParameter("user", username);
                List<SystemAdminAccount> systemAdminAccountList = query.getResultList();
                if(!systemAdminAccountList.isEmpty()) {
                    System.out.println("User " + username + " exists!");
                    return null;//user exist, please change username
                }
                SystemAdminAccount systemAdminAccount = new SystemAdminAccount(username, password, salt, department, company);
                SystemAdmin systemAdmin = new SystemAdmin(systemAdminAccount);
                title.getUser().add(systemAdmin);
                systemAdmin.getTitles().add(title);
                em.persist(systemAdminAccount);
                em.persist(systemAdmin);
                em.persist(title);
                System.out.println("In creating company admin account, account ID: " + systemAdminAccount.getId());
                return systemAdminAccount;
            } else {
                //merlion user account creation --by system admin
                Query query = em.createQuery("SELECT c FROM CompanyUserAccount c WHERE c.username = :user");
                query.setParameter("user", username);
                List<CompanyUserAccount> companyUserAccountList = query.getResultList();
                if(!companyUserAccountList.isEmpty()) {
                    System.out.println("User " + username + " exists!");
                    return null;//user exist, please change username
                }
                CompanyUserAccount companyUserAccount = new CompanyUserAccount(username, password, salt, department, company);
                CompanyUser companyUser = new CompanyUser(companyUserAccount);
                title.getUser().add(companyUser);
                companyUser.getTitles().add(title);
                em.persist(companyUserAccount);
                em.persist(companyUser);
                em.persist(title);
                System.out.println("In creating company admin account, account ID: " + companyUserAccount.getId());
                return companyUserAccount;
            }
        } else {
            return null;//company type does not exist!
        }
    }
    
    @Override
    public boolean checkLogin(String username, String userType, String password) throws UserNotExistException, PasswordNotMatchException {
        if(userType.equals("Admin")) {
            Query query = em.createQuery("SELECT c FROM CompanyAdminAccount c WHERE c.username = :user AND c.lockedOrNot = :lock AND c.deleteOrNot = :delete");
            query.setParameter("user", username);
            query.setParameter("lock", false);
            query.setParameter("delete", false);
            List<CompanyAdminAccount> companyAdminAccountList = query.getResultList();
            if(companyAdminAccountList.isEmpty()) {
                System.out.println("User " + username + " does not exist!");
                throw new UserNotExistException("User " + username + " does not exist, please try again");
//                return false;//user does not exist, please try again
            }
            CompanyAdminAccount companyAdminAccount = companyAdminAccountList.get(0);
            String salt = companyAdminAccount.getSalt();
            password = passwordHash(password + salt);
            if(password.equals(companyAdminAccount.getPassword())) {
                return true;//login successful
            } else {
                throw new PasswordNotMatchException("Username and password do not match, please enter again");
//                return false;//password does not match, enter again
            }
        } else if(userType.equals("User")) {
            Query query = em.createQuery("SELECT c FROM CompanyUserAccount c WHERE c.username = :user AND c.lockedOrNot = :lock AND c.deleteOrNot = :delete");
            query.setParameter("user", username);
            query.setParameter("lock", false);
            query.setParameter("delete", false);
            List<CompanyUserAccount> companyUserAccountList = query.getResultList();
            if(companyUserAccountList.isEmpty()) {
                System.out.println("User " + username + " does not exist!");
                throw new UserNotExistException("User " + username + " does not exist, please try again");
//                return false;//user does not exist, please try again
            }
            CompanyUserAccount companyUserAccount = companyUserAccountList.get(0);
            String salt = companyUserAccount.getSalt();
            password = passwordHash(password + salt);
            if(password.equals(companyUserAccount.getPassword())) {
                return true;//login successful
            } else {
                throw new PasswordNotMatchException("Username and password do not match, please enter again");
//                return false;//password does not match, enter again
            }
        } else if(userType.equals("SystemAdmin")) {
            Query query = em.createQuery("SELECT s FROM SystemAdminAccount s WHERE s.username = :user AND c.lockedOrNot = :lock AND c.deleteOrNot = :delete");
            query.setParameter("user", username);
            query.setParameter("lock", false);
            query.setParameter("delete", false);
            List<SystemAdminAccount> systemAdminAccountList = query.getResultList();
            if(systemAdminAccountList.isEmpty()) {
                System.out.println("User " + username + " does not exist!");
                throw new UserNotExistException("User " + username + " does not exist, please try again");
//                return false;//user does not exist, please try again
            }
            SystemAdminAccount systemAdminAccount = systemAdminAccountList.get(0);
            String salt = systemAdminAccount.getSalt();
            password = passwordHash(password + salt);
            if(password.equals(systemAdminAccount.getPassword())) {
                return true;//login successful
            } else {
                throw new PasswordNotMatchException("Username and password do not match, please enter again");
//                return false;//password does not match, enter again
            }
        } else {
            return false;//user type invalid
        }
    }
    
    // Update accounts
    @Override
    public Account updateAccount(Long id, String accountType, String username, String oldPassword, String newPassword) throws UserExistException, AccountTypeNotExistException, PasswordTooSimpleException {
        try {
            if(accountType.equals("Admin")) {
                CompanyAdminAccount companyAdminAccount = em.find(CompanyAdminAccount.class, id);
                if(companyAdminAccount == null)
                    return null;//cannot find account
                if (username != null && !username.equals(companyAdminAccount.getUsername())) {
                    Query query = em.createQuery("SELECT c FROM CompanyAdminAccount c WHERE c.username = :user");
                    query.setParameter("user", username);
                    List<CompanyAdminAccount> companyAdminAccountList = query.getResultList();
                    if(!companyAdminAccountList.isEmpty()) {
                        throw new UserExistException("Username already exists!");
                    }
                    companyAdminAccount.setUsername(username);
                }
                if(oldPassword != null && newPassword != null && passwordHash(oldPassword+companyAdminAccount.getSalt()).equals(companyAdminAccount.getPassword())) {
                    if (!checkPasswordComplexity(newPassword)) {
                        throw new PasswordTooSimpleException("password is too simple");
                    }
                    newPassword = passwordHash(newPassword + companyAdminAccount.getSalt());
                    companyAdminAccount.setPassword(newPassword);
                } else {
                    return null;//invalid input
                }

                em.merge(companyAdminAccount);
                em.flush();
                return companyAdminAccount;
            } else if(accountType.equals("User")) {
                CompanyUserAccount companyUserAccount = em.find(CompanyUserAccount.class, id);
                if(companyUserAccount == null)
                    return null;//cannot find account
                if (username != null && !username.equals(companyUserAccount.getUsername())) {
                    Query query = em.createQuery("SELECT c FROM CompanyUserAccount c WHERE c.username = :user");
                    query.setParameter("user", username);
                    List<CompanyUserAccount> companyUserAccountList = query.getResultList();
                    if(!companyUserAccountList.isEmpty()) {
                        throw new UserExistException("Username already exists!");
                    }
                    companyUserAccount.setUsername(username);
                }
                if(oldPassword != null && newPassword != null && passwordHash(oldPassword+companyUserAccount.getSalt()).equals(companyUserAccount.getPassword())) {
                    if (!checkPasswordComplexity(newPassword)) {
                        throw new PasswordTooSimpleException("password is too simple");
                    }
                    newPassword = passwordHash(newPassword + companyUserAccount.getSalt());
                    companyUserAccount.setPassword(newPassword);
                } else {
                    return null;//invalid input
                }

                em.merge(companyUserAccount);
                em.flush();
                return companyUserAccount;
            } else if(accountType.equals("System")) {
                SystemAdminAccount systemAdminAccount = em.find(SystemAdminAccount.class, id);
                if(systemAdminAccount == null)
                    return null;//cannot find account
                if (username != null && !username.equals(systemAdminAccount.getUsername())) {
                    Query query = em.createQuery("SELECT c FROM SystemAdminAccount c WHERE c.username = :user");
                    query.setParameter("user", username);
                    List<SystemAdminAccount> systemAdminAccountList = query.getResultList();
                    if(!systemAdminAccountList.isEmpty()) {
                        throw new UserExistException("Username already exists!");
                    }
                    systemAdminAccount.setUsername(username);
                }
                if(oldPassword != null && newPassword != null && passwordHash(oldPassword+systemAdminAccount.getSalt()).equals(systemAdminAccount.getPassword())) {
                    if (!checkPasswordComplexity(newPassword)) {
                        throw new PasswordTooSimpleException("password is too simple");
                    }
                    newPassword = passwordHash(newPassword + systemAdminAccount.getSalt());
                    systemAdminAccount.setPassword(newPassword);
                } else {
                    return null;//invalid input
                }

                em.merge(systemAdminAccount);
                em.flush();
                return systemAdminAccount;
            } else {
                throw new AccountTypeNotExistException("Account type " + accountType + " is incorrect, please check");
            }
        } catch (Exception e) {
            return null;
        }
    }
    
    // To delete account
    @Override
    public Account deleteAccount(Long accountId, String accountType) throws AccountTypeNotExistException {
        if(accountType.equals("Admin")) {
            CompanyAdminAccount companyAdminAccount = em.find(CompanyAdminAccount.class, accountId);
            if(companyAdminAccount == null){
                return null;
            }
            companyAdminAccount.setDeleteOrNot(Boolean.TRUE);
            CompanyAdmin companyAdmin = companyAdminAccount.getCompanyAdmin();
            companyAdmin.setDeleteOrNot(Boolean.TRUE);
            em.merge(companyAdminAccount);
            em.merge(companyAdmin);
            em.flush();
            return companyAdminAccount;
        } else if(accountType.equals("User")) {
            CompanyUserAccount companyUserAccount = em.find(CompanyUserAccount.class, accountId);
            if(companyUserAccount == null){
                return null;
            }
            companyUserAccount.setDeleteOrNot(Boolean.TRUE);
            CompanyUser companyUser = companyUserAccount.getCompanyUser();
            companyUser.setDeleteOrNot(Boolean.TRUE);
            em.merge(companyUserAccount);
            em.merge(companyUser);
            em.flush();
            return companyUserAccount;
        } else if(accountType.equals("Admin")) {
            SystemAdminAccount systemAdminAccount = em.find(SystemAdminAccount.class, accountId);
            if(systemAdminAccount == null){
                return null;
            }
            systemAdminAccount.setDeleteOrNot(Boolean.TRUE);
            SystemAdmin systemAdmin = systemAdminAccount.getSystemAdmin();
            systemAdmin.setDeleteOrNot(Boolean.TRUE);
            em.merge(systemAdminAccount);
            em.merge(systemAdmin);
            em.flush();
            return systemAdminAccount;
        } else {
            throw new AccountTypeNotExistException("Account type does not exist!");
        }
    }
    
    // Search accounts
    @Override
    public List<Account> searchAccount(String criteria, String accountType, Company company) throws AccountTypeNotExistException {
        if(accountType.equals("Admin") || accountType.equals("User")) {
            Query query = em.createQuery("SELECT c FROM CompanyUserAccount c WHERE c.company=:company1 and c.username LIKE '%" + criteria + "%' UNION SELECT d FROM CompanyAdminAccount d WHERE d.company=:company2 and d.username LIKE '%" + criteria + "%'");
            query.setParameter("company1", company);
            query.setParameter("company2", company);
            List<Account> userAccountList = query.getResultList();
            return userAccountList;
        } else if(accountType.equals("System")) {
            Query query = em.createQuery("SELECT c FROM CompanyUserAccount c WHERE c.username LIKE '%" + criteria + "%' UNION SELECT c FROM CompanyAdminAccount c WHERE c.username LIKE '%" + criteria + "%' UNION SELECT c FROM SystemAdminAccount c WHERE c.username LIKE '%" + criteria + "%'");
            List<Account> userAccountList = query.getResultList();
            return userAccountList;
        } else {
            throw new AccountTypeNotExistException("Account type does not exist!");
        }
    }
    
    // Unlock accounts
    @Override
    public Account activateAccount(Long id, String accountType) throws AccountTypeNotExistException {
        if(accountType.equals("Admin")) {
            CompanyAdminAccount companyAdminAccount = em.find(CompanyAdminAccount.class, id);
            if (companyAdminAccount!= null && companyAdminAccount.isLockedOrNot()) {
                companyAdminAccount.setLockedOrNot(false);
                em.merge(companyAdminAccount);
                em.flush();
                return companyAdminAccount;
            } else {
                return null;//cannot find account
            }
        } else if(accountType.equals("User")) {
            CompanyUserAccount companyUserAccount = em.find(CompanyUserAccount.class, id);
            if (companyUserAccount!= null && companyUserAccount.isLockedOrNot()) {
                companyUserAccount.setLockedOrNot(false);
                em.merge(companyUserAccount);
                em.flush();
                return companyUserAccount;
            } else {
                return null;//cannot find account
            }
        } else if(accountType.equals("System")) {
            SystemAdminAccount systemAdminAccount = em.find(SystemAdminAccount.class, id);
            if (systemAdminAccount!= null && systemAdminAccount.isLockedOrNot()) {
                systemAdminAccount.setLockedOrNot(false);
                em.merge(systemAdminAccount);
                em.flush();
                return systemAdminAccount;
            } else {
                return null;//cannot find account
            }
        } else {
            throw new AccountTypeNotExistException("Account type does not exist!");
        }
    }
    
    // Lock account
    @Override
    public Account deactivateAccount(Long id, String accountType) throws AccountTypeNotExistException {
        if(accountType.equals("Admin")) {
            CompanyAdminAccount companyAdminAccount = em.find(CompanyAdminAccount.class, id);
            if (companyAdminAccount!= null && !companyAdminAccount.isLockedOrNot()) {
                companyAdminAccount.setLockedOrNot(Boolean.TRUE);
                em.merge(companyAdminAccount);
                em.flush();
                return companyAdminAccount;
            } else {
                return null;//cannot find account
            }
        } else if(accountType.equals("User")) {
            CompanyUserAccount companyUserAccount = em.find(CompanyUserAccount.class, id);
            if (companyUserAccount!= null && !companyUserAccount.isLockedOrNot()) {
                companyUserAccount.setLockedOrNot(Boolean.TRUE);
                em.merge(companyUserAccount);
                em.flush();
                return companyUserAccount;
            } else {
                return null;//cannot find account
            }
        } else if(accountType.equals("System")) {
            SystemAdminAccount systemAdminAccount = em.find(SystemAdminAccount.class, id);
            if (systemAdminAccount!= null && !systemAdminAccount.isLockedOrNot()) {
                systemAdminAccount.setLockedOrNot(Boolean.TRUE);
                em.merge(systemAdminAccount);
                em.flush();
                return systemAdminAccount;
            } else {
                return null;//cannot find account
            }
        } else {
            throw new AccountTypeNotExistException("Account type does not exist!");
        }
    }
    
    // Reset password
    @Override
    public boolean resetPassword(Long accountId, String accountType, String email) throws AccountTypeNotExistException {
        if(accountType.equals("Admin")) {
            CompanyAdminAccount companyAdminAccount = em.find(CompanyAdminAccount.class, accountId);
            String password = GeneratePassword.createPassword();
            String salt = companyAdminAccount.getSalt();
            //send password to user through email
            try {
                SendEmail(companyAdminAccount.getCompanyAdmin().getFirstName(), companyAdminAccount.getCompanyAdmin().getLastName(), companyAdminAccount.getUsername(), companyAdminAccount.getCompanyAdmin().getEmail(), password, companyAdminAccount.getCompany().getCompanyName());
            } catch (MessagingException ex) {
                System.out.println("Error sending email.");
                Logger.getLogger(AccountManagementSessionBean.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            password = passwordHash(password + salt);
            companyAdminAccount.setPassword(password);
            em.merge(companyAdminAccount);
            em.flush();
            return true;
        } else if(accountType.equals("User")) {
            CompanyUserAccount companyUserAccount = em.find(CompanyUserAccount.class, accountId);
            String password = GeneratePassword.createPassword();
            String salt = companyUserAccount.getSalt();
            //send password to user through email
            try {
                SendEmail(companyUserAccount.getCompanyUser().getFirstName(), companyUserAccount.getCompanyUser().getLastName(), companyUserAccount.getUsername(), companyUserAccount.getCompanyUser().getEmail(), password, companyUserAccount.getCompany().getCompanyName());
            } catch (MessagingException ex) {
                System.out.println("Error sending email.");
                Logger.getLogger(AccountManagementSessionBean.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            password = passwordHash(password + salt);
            companyUserAccount.setPassword(password);
            em.merge(companyUserAccount);
            em.flush();
            return true;
        } else if(accountType.equals("System")) {
            SystemAdminAccount systemAdminAccount = em.find(SystemAdminAccount.class, accountId);
            String password = GeneratePassword.createPassword();
            String salt = systemAdminAccount.getSalt();
            //send password to user through email
            try {
                SendEmail(systemAdminAccount.getSystemAdmin().getFirstName(), systemAdminAccount.getSystemAdmin().getLastName(), systemAdminAccount.getUsername(), systemAdminAccount.getSystemAdmin().getEmail(), password, systemAdminAccount.getCompany().getCompanyName());
            } catch (MessagingException ex) {
                System.out.println("Error sending email.");
                Logger.getLogger(AccountManagementSessionBean.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            password = passwordHash(password + salt);
            systemAdminAccount.setPassword(password);
            em.merge(systemAdminAccount);
            em.flush();
            return true;
        } else {
            throw new AccountTypeNotExistException("Account type does not exist!");
        }
    }
    
    // Check password complexity
    @Override
    public boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) {
            return false;
        }

        char pw[] = password.toCharArray();
        boolean alphabet = false;
        boolean digit = false;
        for (int i = 0; i < pw.length; i++) {
            if (Character.isLetter(pw[i])) {
                alphabet = true;
            }
            if (Character.isDigit(pw[i])) {
                digit = true;
            }
            if (alphabet && digit) {
                return true;
            }
        }
        return false;
    }
    
    // Hash password function
    private String passwordHash(String pass) {
        String md5 = null;

        try {
            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(pass.getBytes(), 0, pass.length());

            //Converts message digest value in base 16 (hex) 
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }
    
    // Retrieve account
    @Override
    public Account retrieveAccount(Long accountId, String accountType) throws AccountTypeNotExistException {
        if(accountType.equals("Admin")) {
            CompanyAdminAccount companyAdminAccount = em.find(CompanyAdminAccount.class, accountId);
            System.out.println("The retrieved Account is " + companyAdminAccount.getUsername());
            return companyAdminAccount;
        } else if(accountType.equals("User")) {
            CompanyUserAccount companyUserAccount = em.find(CompanyUserAccount.class, accountId);
            System.out.println("The retrieved Account is " + companyUserAccount.getUsername());
            return companyUserAccount;
        } else if(accountType.equals("System")) {
            SystemAdminAccount systemAdminAccount = em.find(SystemAdminAccount.class, accountId);
            System.out.println("The retrieved Account is " + systemAdminAccount.getUsername());
            return systemAdminAccount;
        } else {
            throw new AccountTypeNotExistException("Account type does not exist!");
        }
    }
    
    @Override
    public Title createTitle(String titleName, Department department) throws TitleExistException {
        Query query = em.createQuery("select t from Title t where t.titleName=?1 and t.department=?2");
        query.setParameter(1, titleName);
        query.setParameter(2, department);
        ArrayList<Title> titleList = new ArrayList(query.getResultList());
        if(!titleList.isEmpty()){
            throw new TitleExistException("Title "+titleName+" already exists!");
        }
        Title title = new Title(titleName, department);
        em.persist(title);
        em.flush();
        return title;
    }
    
    @Override
    public Boolean deleteTitle(Long titleId) throws TitleNotExistException {
        Query query = em.createQuery("select t from Title t where t.id=?1");
        query.setParameter(1, titleId);
        ArrayList<Title> titleList = new ArrayList(query.getResultList());
        if(titleList.isEmpty()){
            throw new TitleNotExistException("Title "+titleId+" does not exists!");
        }
        try {
            Title title = em.find(Title.class, titleId);
            em.remove(title);
            em.flush();
            return Boolean.TRUE;
        } catch(Exception e) {
            return Boolean.FALSE;
        }
    }
    
    private void SendEmail(String firstName, String lastName, String username, String email, String password, String companyName) throws MessagingException {
//            infoMsg("User account - " + username + " have been created.");
            String title = "Merlion Platform - Account \"" + username + "\" Created";
            String msg = "Dear " + firstName + " " + lastName + ", \n\nCongratulations! You have passed the verification process.\nWelcome to Merlion Platform. Your login details is as follows \n\nUsername: " + username
                    + "\nPassword: " + password + "\nCompany: " + companyName + "\n\nPlease remember to change your password on your first login. Thank you.\n\nRegards,\nAdmin";
            gm.Send("merlion.no.reply", "IS3102JiaYou", email, "", title, msg);
    }
    
    @Override
    public Boolean checkLock(String username, String accountType) throws AccountTypeNotExistException, UserNotExistException {
        if(accountType.equals("Admin")) {
            Query query = em.createQuery("select c from CompanyAdminAccount c where c.username= :user");
            query.setParameter("user", username);
            ArrayList<CompanyAdminAccount> companyAdminAccountList = new ArrayList(query.getResultList());
            if(companyAdminAccountList.isEmpty()){
                throw new UserNotExistException("Username: " + username + " does not exists!");
            }
            CompanyAdminAccount companyAdminAccount = companyAdminAccountList.get(0);
            if(companyAdminAccount.isLockedOrNot()) {
                return Boolean.TRUE;
            } else
                return Boolean.FALSE;
        } else if(accountType.equals("User")) {
            Query query = em.createQuery("select c from CompanyUserAccount c where c.username= :user");
            query.setParameter("user", username);
            ArrayList<CompanyUserAccount> companyUserAccountList = new ArrayList(query.getResultList());
            if(companyUserAccountList.isEmpty()){
                throw new UserNotExistException("Username: " + username + " does not exists!");
            }
            CompanyUserAccount companyUserAccount = companyUserAccountList.get(0);
            if(companyUserAccount.isLockedOrNot()) {
                return Boolean.TRUE;
            } else
                return Boolean.FALSE;
        } else if(accountType.equals("SystemAdmin")) {
            Query query = em.createQuery("select c from SystemAdminAccount c where c.username= :user");
            query.setParameter("user", username);
            ArrayList<SystemAdminAccount> systemAdminAccountList = new ArrayList(query.getResultList());
            if(systemAdminAccountList.isEmpty()){
                throw new UserNotExistException("Username: " + username + " does not exists!");
            }
            SystemAdminAccount systemAdminAccount = systemAdminAccountList.get(0);
            if(systemAdminAccount.isLockedOrNot()) {
                return Boolean.TRUE;
            } else
                return Boolean.FALSE;
        } else {
            throw new AccountTypeNotExistException("Username: "+username+" does not exist!");
        }
    }
    
    @Override
    public Boolean checkDelete(String username, String accountType) throws AccountTypeNotExistException, UserNotExistException {
        if(accountType.equals("Admin")) {
            Query query = em.createQuery("select c from CompanyAdminAccount c where c.username= :user");
            query.setParameter("user", username);
            ArrayList<CompanyAdminAccount> companyAdminAccountList = new ArrayList(query.getResultList());
            if(companyAdminAccountList.isEmpty()){
                throw new UserNotExistException("Username: " + username + " does not exists!");
            }
            CompanyAdminAccount companyAdminAccount = companyAdminAccountList.get(0);
            if(companyAdminAccount.isDeleteOrNot()) {
                return Boolean.TRUE;
            } else
                return Boolean.FALSE;
        } else if(accountType.equals("User")) {
            Query query = em.createQuery("select c from CompanyUserAccount c where c.username= :user");
            query.setParameter("user", username);
            ArrayList<CompanyUserAccount> companyUserAccountList = new ArrayList(query.getResultList());
            if(companyUserAccountList.isEmpty()){
                throw new UserNotExistException("Username: " + username + " does not exists!");
            }
            CompanyUserAccount companyUserAccount = companyUserAccountList.get(0);
            if(companyUserAccount.isDeleteOrNot()) {
                return Boolean.TRUE;
            } else
                return Boolean.FALSE;
        } else if(accountType.equals("SystemAdmin")) {
            Query query = em.createQuery("select c from SystemAdminAccount c where c.username= :user");
            query.setParameter("user", username);
            ArrayList<SystemAdminAccount> systemAdminAccountList = new ArrayList(query.getResultList());
            if(systemAdminAccountList.isEmpty()){
                throw new UserNotExistException("Username: " + username + " does not exists!");
            }
            SystemAdminAccount systemAdminAccount = systemAdminAccountList.get(0);
            if(systemAdminAccount.isDeleteOrNot()) {
                return Boolean.TRUE;
            } else
                return Boolean.FALSE;
        } else {
            throw new AccountTypeNotExistException("Username: "+username+" does not exist!");
        }
    }
    
    @Override
    public Account retrieveAccount(String username, String accountType) throws AccountTypeNotExistException, UserNotExistException {
        if(accountType.equals("Admin")) {
            Query query = em.createQuery("select c from CompanyAdminAccount c where c.username= :user");
            query.setParameter("user", username);
            ArrayList<CompanyAdminAccount> companyAdminAccountList = new ArrayList(query.getResultList());
            if(companyAdminAccountList.isEmpty()){
                throw new UserNotExistException("Username: " + username + " does not exists!");
            }
            CompanyAdminAccount companyAdminAccount = companyAdminAccountList.get(0);
            return companyAdminAccount;
        } else if(accountType.equals("User")) {
            Query query = em.createQuery("select c from CompanyUserAccount c where c.username= :user");
            query.setParameter("user", username);
            ArrayList<CompanyUserAccount> companyUserAccountList = new ArrayList(query.getResultList());
            if(companyUserAccountList.isEmpty()){
                throw new UserNotExistException("Username: " + username + " does not exists!");
            }
            CompanyUserAccount companyUserAccount = companyUserAccountList.get(0);
            return companyUserAccount;
        } else if(accountType.equals("SystemAdmin")) {
            Query query = em.createQuery("select c from SystemAdminAccount c where c.username= :user");
            query.setParameter("user", username);
            ArrayList<SystemAdminAccount> systemAdminAccountList = new ArrayList(query.getResultList());
            if(systemAdminAccountList.isEmpty()){
                throw new UserNotExistException("Username: " + username + " does not exists!");
            }
            SystemAdminAccount systemAdminAccount = systemAdminAccountList.get(0);
            return systemAdminAccount;
        } else {
            throw new AccountTypeNotExistException("Username: "+username+" does not exist!");
        }
    }
}
