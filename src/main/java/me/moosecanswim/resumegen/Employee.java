package me.moosecanswim.resumegen;

import com.sun.istack.internal.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Entity
public class Employee {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long ID;

    @NotNull(message = "you need to have a name")
    private String name;
    private String company;
    private String email;
    @NotNull(message ="you need to have a start date")
    private String startDate;
    @Nullable
    private String endDate;

    private int daysWorking;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        SimpleDateFormat dateformat2 = new SimpleDateFormat("MM/dd/yyyy");
        String output = null;
        try {
            Date newDate = dateformat2.parse(startDate);

            System.out.println(newDate);
            output= dateformat2.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.startDate = output;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {

        if(endDate.isEmpty()){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US);
            LocalDateTime today = LocalDateTime.now();
            String output = dtf.format(today);

            this.endDate=output;
        }
        else {
            this.endDate = endDate;
        }
       //this.endDate = endDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }


    public int getDaysWorking() {
        return daysWorking;
    }

    public void setDaysWorking(String inStart, String inEnd) {
        Date start = stringToDate(inStart);
        Date end = stringToDate(inEnd);
        int output = 0;
        output =  daysBetween(start,end);

        System.out.println("worked for " + output +" days");


        this.daysWorking = output;
    }
    public static Date stringToDate(String input){
        SimpleDateFormat dtf = new SimpleDateFormat("MM/dd/yyyy");
        Date output = new Date();
        try {
            output = dtf.parse(input);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return output;
    }
    public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }
}
