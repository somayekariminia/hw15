package service;

import Util.UtilDate;
import com.github.eloyzone.jalalicalendar.JalaliDate;
import dao.PersonRepository;
import model.entity.*;
import model.enumes.Degree;
import model.enumes.TypeLoan;
import model.enumes.TypeUniversity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class StudentServiceImplTest {
    private static StudentServiceImpl studentsService;
    private static final PersonRepository<Person> personRepository = new PersonRepository<>();
    LoanServiceImpl loanService = new LoanServiceImpl();
    MortgageLoanServiceImpl mortgageLoanService = new MortgageLoanServiceImpl();
    StudentLoanService studentLoanService = new StudentLoanService();

    @BeforeAll
    static void setInformationStudent() {
    }

    @Test
    void requestLoanForGrantLoan() {
        Student student = studentsService.findById(2);
        GrantLoan loan = new GrantLoan();
        loan.setTypeLoan(TypeLoan.TUITION);
        JalaliDate date=new JalaliDate(1401,7,25);
        loanService.requestForGrandLoan(student, loan,date);
    }

    @Test
    void requestLoanForMortgageLoan() {
        Student student = studentsService.findById(2);
        MortgageLoan loan = new MortgageLoan();
        String lease = "12345";
        JalaliDate jalaliDate=new JalaliDate(1400,6,28);
        mortgageLoanService.requestForMortgageLoan(student, loan, lease,jalaliDate);
    }

    @Test
    void signUp() {
        studentsService = new StudentServiceImpl();
        JalaliDate birthday=new JalaliDate(1368,12,01);
        LocalDate birthdayLocal=UtilDate.changeJalaliDateToMiladi(birthday);
        Date birthdayDate = UtilDate.changeLocalDateToDate(birthdayLocal);
        Person person = new Person(0, "somaye", "kariminia", "ali", "maryam", "31200", "3120046981", birthdayDate, true);
        University university = new University(0, "kerman", "bahonar", TypeUniversity.nonprofitUniversity);
        Address address = new Address(0, "iran", "kerman", "hashtbehesht", "21", "0");
        Person spouse = new Spouse();
        spouse.setFirstName("moreteza");
        spouse.setLastName("karimi");
        spouse.setNationalCode("3120014261");
        JalaliDate exprDate=new JalaliDate(1402,12,01);
        LocalDate exprDateLocal=UtilDate.changeJalaliDateToMiladi(exprDate);
        Date date = UtilDate.changeLocalDateToDate(exprDateLocal);
        CreditCard creditCard = new CreditCard(0, "5894631298123456", "661", "1234567891", 0d, date);
        InfoAccount infoAccount = new InfoAccount(0, "SSoo!!66", person.getNationalCode());
        List<StudentLoan> studentLoanList = new ArrayList<>();
        Student student = new Student(0, "somaye", "kariminia", "ali", "maryam", "31200", "3120046981", birthdayDate, true, "8721843", university, 1396, Degree.DiscontinuousMaster, infoAccount, false, address, null, (Spouse) spouse, creditCard);
        studentsService.signUp(student);
    }
}