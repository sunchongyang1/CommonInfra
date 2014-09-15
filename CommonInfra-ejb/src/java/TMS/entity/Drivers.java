/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TMS.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author HanXiangyu
 */
@Entity
public class Drivers implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id    
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long driverId;
    
    private String name;
    @OneToMany(cascade={CascadeType.PERSIST}, mappedBy="drivers")
    private List<Trucks> trucks = new ArrayList<>();

    private ArrayList<TimeSlot> timetable = new ArrayList<>();

    public Drivers() {
        //this.name = name;
    }

    public ArrayList<TimeSlot> getTimetable() {
        return timetable;
    }

    public void setTimetable(ArrayList<TimeSlot> timetable) {
        this.timetable = timetable;
    }

    public Boolean addTimeSlot(Timestamp start, Timestamp end){
        if (start.before(end)){
            TimeSlot slot = new TimeSlot(start, end);
            this.timetable.add(slot);
            return true;
        }else {  
            return false;
        }
    }
    /**
     * @return the driverId
     */
    public Long getDriverId() {
        return driverId;
    }

    /**
     * @param driverId the driverId to set
     */
    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the trucks
     */
    public List<Trucks> getTrucks() {
        return trucks;
    }

    /**
     * @param trucks the trucks to set
     */
    public void setTrucks(List<Trucks> trucks) {
        this.trucks = trucks;
    }
    
    
}
