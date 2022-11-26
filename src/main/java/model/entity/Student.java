package model.entity;

import model.enumes.Education;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Date;


public class Student {
    String firstName;
    String lastName;
    String nameFather;
    String nameMother;
    String nationalCode;
    String numberIdentity;
    Date birthday;
    String numberStudent;
    University university;
    Integer enterYear;
    Education education;
}
