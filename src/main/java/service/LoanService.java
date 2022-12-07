package service;

import com.github.eloyzone.jalalicalendar.JalaliDate;
import model.entity.CreditCard;
import model.entity.Student;

import java.util.Date;
import java.util.List;

public interface LoanService<T> {


    void requestLoan(Student student, T loan, JalaliDate date);


}
