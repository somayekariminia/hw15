package service;

import Exeption.ValidationException;
import Util.UtilDate;
import com.github.eloyzone.jalalicalendar.JalaliDate;
import dao.PersonRepository;
import dao.StudentRepository;
import model.entity.*;
import model.enumes.Degree;
import model.enumes.TypeLoan;
import model.enumes.TypeUniversity;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

class StudentServiceImplTest {
    private static StudentServiceImpl studentsService = new StudentServiceImpl();
    private static final PersonRepository<Person> personRepository = new PersonRepository<>();
    LoanServiceImpl loanService = new LoanServiceImpl();
    MortgageLoanServiceImpl mortgageLoanService = new MortgageLoanServiceImpl();
    StudentLoanService studentLoanService = new StudentLoanService();
    StudentRepository studentRepository = StudentRepository.getInstance();

    @BeforeAll
    static void setInformationStudent() {
    }


    @Test
    void requestLoanForGrantLoan() {
        Student student = studentsService.findById(2);
        GrantLoan loan = new GrantLoan();
        loan.setTypeLoan(TypeLoan.TUITION);
        JalaliDate date = new JalaliDate(1401, 8, 2);
        JalaliDate exprDate = new JalaliDate(1402, 12, 01);
        LocalDate exprDateLocal = UtilDate.changeJalaliDateToMiladi(exprDate);
        Date date1 = UtilDate.changeLocalDateToDate(exprDateLocal);
        CreditCard creditCard = new CreditCard(0, "5894631298123456", 661, "1234567891", 0d, date1);
        loanService.requestLoan(student, loan, date, creditCard);
        Loan loan1 = loanService.getById(1);
        Assertions.assertEquals(1, loan1.getId());
    }

    @Test
    void requestLoanForMortgageLoan() {
        Student student = studentsService.findById(2);
        MortgageLoan loan = new MortgageLoan();
        JalaliDate exprDate = new JalaliDate(1402, 12, 01);
        LocalDate exprDateLocal = UtilDate.changeJalaliDateToMiladi(exprDate);
        Date date = UtilDate.changeLocalDateToDate(exprDateLocal);
        CreditCard creditCard = new CreditCard(0, "5894631298123456", 661, "1234567891", 0d, date);
        String lease = "12345";
        JalaliDate jalaliDate = new JalaliDate(1401, 8, 1);
        Student byId = studentsService.findById(1);
        Person spouse = new Spouse();
        spouse.setFirstName("moreteza");
        spouse.setLastName("karimi");
        spouse.setNationalCode("3120014261");
        personRepository.save(spouse);
        byId.setSpouse((Spouse) spouse);
        studentRepository.update(byId);
        mortgageLoanService.requestForMortgageLoan(student, loan, lease, jalaliDate, creditCard);
        Loan loanMortgageLoan = mortgageLoanService.getById(2);
        Assertions.assertEquals(2, loanMortgageLoan.getId());

    }

    @Test
    void signUp() {
        studentsService = new StudentServiceImpl();
        JalaliDate birthday = new JalaliDate(1368, 12, 01);
        LocalDate birthdayLocal = UtilDate.changeJalaliDateToMiladi(birthday);
        Date birthdayDate = UtilDate.changeLocalDateToDate(birthdayLocal);
        Person person = new Person(0, "somaye", "kariminia", "ali", "maryam", "31200", "3120046981", birthdayDate, true);
        University university = new University(0, "kerman", "bahonar", TypeUniversity.nonprofitUniversity);
        Address address = new Address(0, "iran", "kerman", "hashtbehesht");
        InfoAccount infoAccount = new InfoAccount(0, "SSoo!!66", person.getNationalCode());
        List<StudentLoan> studentLoanList = new ArrayList<>();
        Student student = new Student(0, "somaye", "kariminia", "ali", "maryam", "31200", "3120046981", birthdayDate, true, "8721843", university, 1396, Degree.DiscontinuousMaster, infoAccount, false, address, null, null);
        studentsService.signUp(student);
        Student studentResult = studentsService.findById(1);
        Assertions.assertEquals(1, studentResult.getId());
    }

    @Test
    void graduateTest() {
        Student student = studentsService.findById(3);
        Assertions.assertNotNull(student);
    }

    @Test
    void getStudentByNationalCode() {
        studentRepository.getByNationalCode("31200");
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
        CreditCard creditCard = new CreditCard();
        Exception exception = Assert.assertThrows(RuntimeException.class, () -> mortgageLoanService.requestForMortgageLoan(student1, loan, lease, jalaliDate, creditCard));
        Assert.assertEquals("You're not already married.", exception.getMessage());
    }

   @Test
    void testExceptionsLoan() {
       Student student1=studentsService.findById(1);
       student1.setDegree(Degree.ContinueMaster);
       student1.setDorm(true);
        String lease = "12345";
        JalaliDate jalaliDate = new JalaliDate(1401, 8, 1);
        JalaliDate exprDate = new JalaliDate(1402, 12, 01);
        LocalDate exprDateLocal = UtilDate.changeJalaliDateToMiladi(exprDate);
       MortgageLoan loan = new MortgageLoan();
        Date date1 = UtilDate.changeLocalDateToDate(exprDateLocal);
        CreditCard creditCard = new CreditCard(0, "5894631298123456", 661, "1234567891",0 , date1);
        Exception exception = assertThrows(ValidationException.class, () -> mortgageLoanService.requestForMortgageLoan(student1, loan, lease, jalaliDate, creditCard) );
        assertTrue(exception.getMessage().equals("You already have a dorm."));
    }
}