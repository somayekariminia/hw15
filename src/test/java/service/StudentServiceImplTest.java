package service;

import Util.UtilDate;
import dao.PersonRepository;
import model.entity.*;
import model.enumes.Degree;
import model.enumes.TypeLoan;
import model.enumes.TypeUniversity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class StudentServiceImplTest {
    LoanServiceImpl loanService = new LoanServiceImpl();
    MortgageLoanServiceImpl mortgageLoanService = new MortgageLoanServiceImpl();
   private static StudentServiceImpl studentsService ;
    StudentLoanService studentLoanService = new StudentLoanService();
   private static PersonRepository<Person>personRepository=new PersonRepository<>();

    @BeforeAll
    static void setInformationStudent() {
        studentsService = new StudentServiceImpl();
        Date birthday = UtilDate.changeLocalDateToDate(LocalDateTime.now());
        Person person = new Person(0, "somaye", "kariminia", "ali", "maryam", "31200", "3120046981", birthday, true);
        //personRepository.save(person);*/
        University university = new University(0, "kerman", "bahonar", TypeUniversity.nonprofitUniversity);
        Address address = new Address(0, "iran", "kerman", "hashtbehesht", "21", "0");
        Person spouse = new Spouse();
        spouse.setFirstName("moreteza");
        spouse.setLastName("karimi");
        spouse.setNationalCode("3120014261");
        Date date = UtilDate.changeLocalDateToDate(LocalDateTime.of(2023, 10, 30, 0, 0));
        CreditCard creditCard = new CreditCard(0, "5894631298123456", "661", "1234567891", 0d, date);
        InfoAccount infoAccount = new InfoAccount(0, "somayeKarimi", person.getNationalCode());
        List<StudentLoan> studentLoanList = new ArrayList<>();
        Student student = new Student(0, "somaye", "kariminia", "ali", "maryam", "31200", "3120046981", birthday, true,"8721843", university, 1396, Degree.Master, infoAccount, false, address, null, (Spouse) spouse, creditCard);
       studentsService.signUp(student);
    }

    @Test
    void requestLoanForGrantLoan() {
        Student student = studentsService.findById(2);
        GrantLoan loan = new GrantLoan();
        loan.setTypeLoan(TypeLoan.TUITION);
        loanService.requestForGrandLoan(student, loan);
    }

    @Test
    void requestLoanForMortgageLoan() {

    }
/*
    @Test
    void signUp(){
        studentsService = new StudentServiceImpl();
        Date birthday = UtilDate.changeLocalDateToDate(LocalDateTime.now());
        Person person = new Person(0, "somaye", "kariminia", "ali", "maryam", "31200", "3120046981", birthday, true);
        University university = new University(0, "kerman", "bahonar", TypeUniversity.nonprofitUniversity);
        Address address = new Address(0, "iran", "kerman", "hashtbehesht", "21", "0");
        Person spouse = new Spouse();
        spouse.setFirstName("moreteza");
        spouse.setLastName("karimi");
        spouse.setNationalCode("3120014261");
        Date date = UtilDate.changeLocalDateToDate(LocalDateTime.of(2023, 10, 30, 0, 0));
        CreditCard creditCard = new CreditCard(0, "5894631298123456", "661", "1234567891", 0d, date);
        InfoAccount infoAccount = new InfoAccount(0, "SSoo66!!", person.getNationalCode());
        List<StudentLoan> studentLoanList = new ArrayList<>();
        Student student = new Student("8721843", university, 1396, Degree.Master, infoAccount, false, address, null, (Spouse) spouse, creditCard);
        studentsService.signUp(student);
    }
*/

}