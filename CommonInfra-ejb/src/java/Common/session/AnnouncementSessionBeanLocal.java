/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Common.session;

import Common.entity.Account;
import Common.entity.Announcement;
import Common.entity.Department;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author chongyangsun
 */
@Local
public interface AnnouncementSessionBeanLocal {
    public Announcement createAnnouncement(String title, String content, Account account, Department department);
    public List<Announcement> getAllAnnouncements(Long accountId);
    public List<Announcement> getDepartmentAnnouncements(Long departmentId);
    public Boolean deleteAnnouncement(Long id);
}
