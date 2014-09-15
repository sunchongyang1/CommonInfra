/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Common.session;

import Common.entity.Account;
import Common.entity.Announcement;
import Common.entity.Department;
import Common.entity.Message;
import Common.entity.Title;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author chongyangsun
 */
@Stateless
public class AnnouncementSessionBean implements AnnouncementSessionBeanLocal {

    @PersistenceContext
    EntityManager em;
    Message message;
    Account account;
    Announcement announcement;
    Department department;

    public AnnouncementSessionBean() {
    }

    @Override
    public Announcement createAnnouncement(String title, String content, Account account, Department department) {
        try {
            announcement = new Announcement();
            account = account;
            department = department;
            announcement = new Announcement(title, content,account,department);

            em.persist(announcement);
            em.flush();
            
            return announcement;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Announcement> getAllAnnouncements(Long accountId) {
        try {
            List<Announcement> announcements = new ArrayList<>();
            account = em.find(Account.class, accountId);
            announcements = account.getAnnouncements();
            return announcements;
        } catch (Exception ex) {
            return null;
        }
    }
    
    @Override
    public List<Announcement> getDepartmentAnnouncements(Long departmentId) {
        try {
            List<Announcement> announcements = new ArrayList<>();
            department = em.find(Department.class, departmentId);
            announcements = department.getAnnouncements();
            return announcements;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Boolean deleteAnnouncement(Long id) {
        try {
            Announcement announcement = em.find(Announcement.class, id);
            System.out.println("Announcement is found, ready to delete.");
            em.remove(announcement);
            System.out.println("Announcemnet is removed.");
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
