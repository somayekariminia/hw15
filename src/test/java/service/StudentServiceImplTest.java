package service;

import Util.UtilDate;
import com.github.eloyzone.jalalicalendar.JalaliDate;
import dao.PersonRepository;
import model.entity.*;
import model.enumes.Degree;
import model.enumes.TypeLoan;
import model.enumes.TypeUniversity;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class StudentServiceImplTest {
    private static StudentServiceImpl studentsService = new StudentServiceImpl();
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
        JalaliDate date = new JalaliDate(1401, 8, 2);
        loanService.requestLoan(student, loan, date);
    }

    @Test
    void requestLoanForMortgageLoan() {
        Student student = studentsService.findById(2);
        MortgageLoan loan = new MortgageLoan();
        String lease = "12345";
        JalaliDate jalaliDate = new JalaliDate(1401, 8, 1);
        mortgageLoanService.requestForMortgageLoan(student, loan, lease, jalaliDate);
    }

    @Test
    void signUp() {
        studentsService = new StudentServiceImpl();
        JalaliDate birthday = new JalaliDate(1368, 12, 01);
        LocalDate birthdayLocal = UtilDate.changeJalaliDateToMiladi(birthday);
        Date birthdayDate = UtilDate.changeLocalDateToDate(birthdayLocal);
        Person person = new Person(0, "somaye", "kariminia", "ali", "maryam", "31200", "3120046981", birthdayDate, true);
        University university = new University(0, "kerman", "bahonar", TypeUniversity.nonprofitUniversity);
        Address address = new Address(0, "iran", "kerman", "hashtbehesht", "21", "0");
        Person spouse = new Spouse();
        spouse.setFirstName("moreteza");
        spouse.setLastName("karimi");
        spouse.setNationalCode("3120014261");
        JalaliDate exprDate = new JalaliDate(1402, 12, 01);
        LocalDate exprDateLocal = UtilDate.changeJalaliDateToMiladi(exprDate);
        Date date = UtilDate.changeLocalDateToDate(exprDateLocal);
        CreditCard creditCard = new CreditCard(0, "5894631298123456", "661", "1234567891", 0d, date);
        InfoAccount infoAccount = new InfoAccount(0, "SSoo!!66", person.getNationalCode());
        List<StudentLoan> studentLoanList = new ArrayList<>();
        Student student = new Student(0, "somaye", "kariminia", "ali", "maryam", "31200", "3120046981", birthdayDate, true, "8721843", university, 1396, Degree.DiscontinuousMaster, infoAccount, false, address, null, (Spouse) spouse, creditCard);
        studentsService.signUp(student);
    }

    @Test
    void graduateTest() {
        Student student = studentsService.findById(3);

    }

    @Test
    void TestExceptionsRequestMortgageLoan() {
        Student student = studentsService.findById(2);
        student.setMarried(false);
        studentsService.update(student);
        Student student1 = studentsService.findById(3);
        MortgageLoan loan = new MortgageLoan();
        student.setDorm(true);
        studentsService.update(student);
        Student student2 = studentsService.findById(3);
        String lease = "12345";
        JalaliDate jalaliDate = new JalaliDate(1401, 8, 1);
        Exception exception = Assert.assertThrows(RuntimeException.class, () -> mortgageLoanService.requestForMortgageLoan(student1, loan, lease, jalaliDate));
        Assert.assertEquals("You're not already married.", exception.getMessage());
    }

    @TestFactory
    Stream<DynamicTest> testDifferentMultiplyOperations() {
        Student student = studentsService.findById(3);
        student.setMarried(false);
        studentsService.update(student);
        Student student1 = studentsService.findById(3);
        MortgageLoan loan = new MortgageLoan();
        student.setDorm(true);
        studentsService.update(student);
        Student student2 = studentsService.findById(3);
        List[] data={Arrays.asList(student1,"You're not already married."),Arrays.asList(student2,"You already have a dorm.")};
        return Arrays.stream(data).map(entry -> {
            String lease = "12345";
            JalaliDate jalaliDate = new JalaliDate(1401, 8, 1);
            Exception exception = Assert.assertThrows(RuntimeException.class, () -> mortgageLoanService.requestForMortgageLoan((Student) entry.get(0), loan, lease, jalaliDate));
            return dynamicTest((String) entry.get(1),() -> {
                Assert.assertEquals(entry.get(1), exception.getMessage());
            });
        });
    }
}