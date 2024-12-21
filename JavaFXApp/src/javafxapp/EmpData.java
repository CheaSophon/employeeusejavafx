/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapp;

import java.util.Date;

/**
 *
 * @author MSI
 */
public class EmpData {
    private Integer empid;
    private String firstname;
    private String lastname;
    private String gender;
    private String phone;
    private String address;
    private String position;
    private String image;
    private Date date;
    private Double salary;

    public EmpData(Integer empid, String firstname, String lastname, String gender, String phone, String address, String position, String image, Date date, Double salary) {
        this(empid, firstname, lastname, gender, phone, address, position, image, date);
    }

    public EmpData(int empid, String firstname, String lastname, String gender, String phone, String address, String position, String image, Date date) {
        this.empid = empid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.position = position;
        this.image = image;
        this.date = date;
        
    }

    
    
    public  EmpData(int empid, String firstname, String lastname, String position,  Double salary){
         this.salary = salary;
         this.empid = empid;
         this.firstname = firstname;
         this.lastname = lastname;
          this.position = position;
    }
       

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmpData{" + "empid=" + empid + ", firstname=" + firstname + ", lastname=" + lastname + ", gender=" + gender + ", phone=" + phone + ", address=" + address + ", position=" + position + ", image=" + image + ", date=" + date + ", salary=" + salary + '}';
    }

    
}
