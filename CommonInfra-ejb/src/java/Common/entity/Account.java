/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Common.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author chongyangsun
 */
@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String salt;
    private boolean lockedOrNot;
    private boolean deleteOrNot;
    private boolean approvedOrNot;
    
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfIssue;
    
    @OneToMany(cascade={CascadeType.PERSIST},mappedBy = "sender")
    private List<Message> messages = new ArrayList<>();
    @OneToMany(cascade={CascadeType.PERSIST},mappedBy = "account")
    private List<Announcement> announcements = new ArrayList<>();
    @OneToMany(mappedBy = "sender")
    private List<Notification> notifications;
    @OneToMany(mappedBy = "account")
    private List<NotificationRecord> notificationRecords;

    
    @OneToOne(mappedBy="account")
    private User user;
    @ManyToOne
    private Company company;
    @ManyToOne
    private Department department;
    
    public Account(){
    }
    
    public Account(String username, String password, String salt, Company company, Department department) {
        this.setUsername(username);
        this.setPassword(password);
        this.setSalt(salt);
        this.setLockedOrNot(Boolean.FALSE);
        Date dateOfIssue = new Date();
        this.setDateOfIssue(dateOfIssue);
        this.setCompany(company);
        this.setDepartment(department);
        this.setApprovedOrNot(Boolean.FALSE);
// To output the date in formatted manner        
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Date date = new Date();
//        System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the dateOfIssue
     */
    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    /**
     * @param dateOfIssue the dateOfIssue to set
     */
    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    /**
     * @return the messages
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * @param messages the messages to set
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    /**
     * @return the announcements
     */
    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    /**
     * @param announcements the announcements to set
     */
    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    /**
     * @return the notifications
     */
    public List<Notification> getNotifications() {
        return notifications;
    }

    /**
     * @param notifications the notifications to set
     */
    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    /**
     * @return the notificationRecords
     */
    public List<NotificationRecord> getNotificationRecords() {
        return notificationRecords;
    }

    /**
     * @param notificationRecords the notificationRecords to set
     */
    public void setNotificationRecords(List<NotificationRecord> notificationRecords) {
        this.notificationRecords = notificationRecords;
    }

    /**
     * @return the company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * @return the lockedOrNot
     */
    public boolean isLockedOrNot() {
        return lockedOrNot;
    }

    /**
     * @param lockedOrNot the lockedOrNot to set
     */
    public void setLockedOrNot(boolean lockedOrNot) {
        this.lockedOrNot = lockedOrNot;
    }

    /**
     * @return the deleteOrNot
     */
    public boolean isDeleteOrNot() {
        return deleteOrNot;
    }

    /**
     * @param deleteOrNot the deleteOrNot to set
     */
    public void setDeleteOrNot(boolean deleteOrNot) {
        this.deleteOrNot = deleteOrNot;
    }

    /**
     * @return the department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the approvedOrNot
     */
    public boolean isApprovedOrNot() {
        return approvedOrNot;
    }

    /**
     * @param approvedOrNot the approvedOrNot to set
     */
    public void setApprovedOrNot(boolean approvedOrNot) {
        this.approvedOrNot = approvedOrNot;
    }

}
