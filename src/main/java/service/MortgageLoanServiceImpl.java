package service;

import Util.UtilDate;
import com.github.eloyzone.jalalicalendar.JalaliDate;
import dao.MortgageLoanRepository;
import dao.PersonRepository;
import dao.StudentRepository;
import model.entity.*;
import model.enumes.LargeCity;
import model.enumes.TypeCity;
import model.enumes.TypePayment;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MortgageLoanServiceImpl {
    MortgageLoanRepository mortgageLoanRepository = MortgageLoanRepository.getInstance();

    StudentRepository studentRepository = StudentRepository.getInstance();
    StudentLoanService studentLoanService = new StudentLoanService();
    PersonRepository<Person> personRepository = new PersonRepository<>();

    public void requestForMortgageLoan(Student student, MortgageLoan mortgageLoan, String lease, JalaliDate dateRegistry) {
        double amount = 0;
        TypeCity typeCity;
        if (UtilDate.timeRegistryLoan(dateRegistry)) {
            LocalDate today = UtilDate.changeJalaliDateToMiladi(dateRegistry);
            if (!student.isMarried())
                throw new RuntimeException("You're not already married.");
            if (student.isDorm())
                throw new RuntimeException("You already have a dorm.");
            if (isGetMortgageLoan(student))
                throw new RuntimeException("you've already taken out this loan");
            Student student1 = studentRepository.getByNationalCode( student.getSpouse().getNationalCode());
            if (student1 != null) {
                if (isGetMortgageLoan(student1))
                    throw new RuntimeException("your Spouse has already taken out this loan ");
            }
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
            studentLoanService.registryLoan(student, mortgageLoan, UtilDate.changeLocalDateToDate(today), lease);
        }
    }

    private  boolean isGetMortgageLoan(Student student) {
        List<StudentLoan> loanList= studentLoanService.getAlLoansStudent(student);
   return loanList.stream().anyMatch(studentLoan -> {
        return studentLoan.getLease() != null;
        });
    }
    public Loan getById(int id){
       return mortgageLoanRepository.getById(id);
    }
}
