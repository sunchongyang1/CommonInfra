/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Common.session;

import Common.entity.Account;
import Common.entity.Department;
import Common.entity.Message;
import Common.entity.Notification;
import Common.entity.NotificationRecord;
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
public class NotificationSessionBean implements NotificationSessionBeanLocal {

    @PersistenceContext
    EntityManager em;
    Message message;
    Account account;
    Notification notification;
    Department department;
    NotificationRecord notificationRecord;
    
    public NotificationSessionBean() {
    }
    
    @Override
    public Notification createNotification(Long senderId, Long departmentId, String content) {
        try {
            notification = new Notification();
            Date currentTime = new Date();
            account = em.find(Account.class, senderId);
            department = em.find(Department.class, Long.valueOf("3"));
            notification = new Notification(account, departmentId, content, currentTime);

            em.persist(notification);
            em.flush();
            return notification;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<NotificationRecord> getNotficationRecords(Long userId) {
        try {
            List<NotificationRecord> results = new ArrayList<>();
            NotificationRecord notificationRecord;
            Long departmentId = Long.valueOf("3");
            department = em.find(Department.class, departmentId);
            
            Query q = em.createQuery("SELECT n FROM Notification n WHERE n.receiver=:department");
            q.setParameter("department", department);
            System.out.println(q.getResultList());
            
            List<Notification> notifications = q.getResultList();
            for (Notification nf : notifications) {
                System.err.println("Notification is found. ID = " + nf.getId());
                notificationRecord = createRecord(userId, nf.getId());
                results.add(notificationRecord);
            }
            return results;
        } catch (Exception ex) {
            return null;
        }
    }
    
    private NotificationRecord createRecord(Long userId, Long notificationId) {
        notificationRecord = new NotificationRecord();
        notification = em.find(Notification.class, notificationId);
        account = em.find(Account.class, userId);

        Date thisTime = notification.getNotifyTime();
        System.out.println("Get time = " + thisTime);
        Long sender = notification.getSender().getId();
        System.out.println("Get sender ID= " + sender);
        String senderName = notification.getSender().getUsername();
        System.out.println(senderName);
        String content = notification.getContent();
        System.out.println(content);
        Long departmentId = notification.getDepartmentId();
        System.out.println("department ID is " + departmentId);
//        Department department = em.find(Department.class, departmentId);
        notificationRecord.createNotificationRecord(sender, content, thisTime, departmentId);
        System.err.println("A notification reocord is created!!!!ID is " + notificationRecord.getId());

        notificationRecord.setAccount(account);

        em.persist(notificationRecord);
        return notificationRecord;

    }

    @Override
    public boolean deleteNotificationRecord(Long id) {
        try {
            notificationRecord = em.find(NotificationRecord.class, id);
            em.remove(notificationRecord);
            em.flush();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
