package service;

import Util.UtilDate;
import dao.MortgageLoanRepository;
import dao.StudentRepository;
import model.entity.MortgageLoan;
import model.entity.Student;
import model.enumes.LargeCity;
import model.enumes.TypeCity;
import model.enumes.TypePayment;

import java.time.LocalDateTime;
import java.util.Arrays;

public class MortgageLoanServiceImpl {
    MortgageLoanRepository mortgageLoanRepository = MortgageLoanRepository.getInstance();

    StudentRepository studentRepository = StudentRepository.getInstance();
    StudentLoanService studentLoanService = new StudentLoanService();

    public void requestForMortgageLoan(Student student, MortgageLoan mortgageLoan) {
        double amount = 0;
        TypeCity typeCity;
        LocalDateTime today = LocalDateTime.now();
        if (UtilDate.timeRegistryLoan(today)) {
            if (!student.isMarried())
                throw new RuntimeException("you arent married");
            boolean anyMatch = student.getStudentLoanList().stream().anyMatch(studentLoan -> {
                studentLoan.getLoan().getTypePayment().equals(null);
                return true;
            });
            if (anyMatch)
                throw new RuntimeException("you is registering  before MortgageLoan");
            String nationalCode = student.getSpouse().getNationalCode();
            Student student1 = studentRepository.getByNationalCode(nationalCode);
            if (student1.getStudentLoanList().stream().anyMatch(studentLoan -> {
                studentLoan.getLoan().getTypePayment().equals(null);
                return true;
            }))
                throw new RuntimeException("your Spouse get MortgageLoan");
            if (student.isDorm())
                throw new RuntimeException("you cant register MortgageLoan becouse you have dorm ");

            if (Arrays.stream(LargeCity.values()).anyMatch(value -> String.valueOf(value).equalsIgnoreCase(student.getAddress().getCity()))) {
                typeCity = TypeCity.LargeCity;
                amount = 26e6;
            } else if (student.getAddress().getCity().equalsIgnoreCase(String.valueOf(TypeCity.TEHRAN))) {
                typeCity = TypeCity.TEHRAN;
                amount = 32e6;
            } else {
                typeCity = TypeCity.REST;
                amount = 195e5;
            }
            mortgageLoan.setTypeCity(typeCity);
            mortgageLoan.setAmount(amount);
            mortgageLoan.setTypePayment(TypePayment.DEGREE);
            mortgageLoanRepository.save(mortgageLoan);
            studentLoanService.registryLoan(student, mortgageLoan, UtilDate.changeLocalDateToDate(today));
        }
    }
}
