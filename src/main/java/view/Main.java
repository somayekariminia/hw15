package view;

import dao.StudentRepository;
import model.entity.Student;
import model.entity.StudentLoan;
import service.StudentLoanService;

import java.util.List;

public class Main {
    /*    public static void main(String[] args) {
            StudentRepository studentRepository=StudentRepository.getInstance();
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
            InfoAccount infoAccount = new InfoAccount(0, "somayeKarimi", person.getNationalCode());
            List<StudentLoan> studentLoanList = new ArrayList<>();
            Student student = new Student(0, "somaye", "kariminia", "ali", "maryam", "31200", "3120046981", birthday, true,"8721843", university, 1396, Degree.Master, infoAccount, false, address, null, (Spouse) spouse, creditCard);
            studentRepository.save(student);*/
    public static void main(String[] args) {
        StudentLoanService studentLoanService = new StudentLoanService();
/*        DateConverter dateConverter = new DateConverter();
        LocalDate localDate1 = dateConverter.jalaliToGregorian(1370, 11, 28);
        JalaliDate jalaliDate1 = dateConverter.gregorianToJalali(1992, 2, 17);
        LocalDateTime localDateTime = localDate1.atStartOfDay();
        LocalDate localDate = localDateTime.toLocalDate();*/
        StudentRepository studentRepository = StudentRepository.getInstance();
        Student byId = studentRepository.getById(3);
        List<StudentLoan> loanList = studentLoanService.getAlLoansStudent(byId);
        boolean b = loanList.stream().anyMatch(studentLoan -> {
         return studentLoan.getLease() != null;
        });

    }
}
