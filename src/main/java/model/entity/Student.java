package model.entity;

import model.enumes.Education;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Date;


public class Student extends Person{
    String numberStudent;
    University university;
    Integer enterYear;
    Education education;
}
