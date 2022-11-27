package model.entity;

import java.util.Date;
import java.util.List;

public class AllocatedLoan {
    Student student;
    Loan loan;
    Date receiveDate;
    List<AllocatedLoan> allocatedLoanList;
    CreditCard creditCard;
}
