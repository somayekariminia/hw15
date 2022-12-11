package service;

import Util.UtilDate;
import com.github.eloyzone.jalalicalendar.JalaliDate;
import dao.GrantLoanRepository;
import dao.LoanRepository;
import model.entity.GrantLoan;
import model.entity.Loan;
import model.entity.Student;
import model.entity.StudentLoan;
import model.enumes.Degree;
import model.enumes.TypeLoan;
import model.enumes.TypePayment;
import model.enumes.TypeUniversity;

import java.time.LocalDate;
import java.util.List;

public class LoanServiceImpl implements LoanService<GrantLoan> {
    LoanRepository<GrantLoan> grantLoanRepository = GrantLoanRepository.getInstance();
    StudentLoanService studentLoanService = new StudentLoanService();
    StudentServiceImpl studentService = new StudentServiceImpl();

    @Override
    public void requestLoan(Student student, GrantLoan grantLoan, JalaliDate dateRegistry) {
        if (UtilDate.timeRegistryLoan(dateRegistry)) {
            LocalDate today = UtilDate.changeJalaliDateToMiladi(dateRegistry);
            List<StudentLoan> studentLoans = studentLoanService.getAlLoansStudent(student);

            if (grantLoan.getTypeLoan().equals(TypeLoan.EDUCATION)) {
                if (!isGetLoan(today, studentLoans)) throw new RuntimeException("you registry this loan before");
                registryEducationLoan(student, grantLoan, today);
            } else if (grantLoan.getTypeLoan().equals(TypeLoan.TUITION)) {
                if (!isGetLoan(today, studentLoans)) throw new RuntimeException("you registry this loan before");
                registryTuitionLoan(student, grantLoan, today);
            } else throw new RuntimeException("dont give loan to student StateUniversityDail");
        }
    }

    private boolean isGetLoan(LocalDate today, List<StudentLoan> studentLoans) {
        return studentLoans.stream().noneMatch(studentLoan -> {
            return (studentLoan.getReceiveDate().getYear() == today.getYear() && (studentLoan.getReceiveDate().getMonth() == today.getMonthValue()));
        });
    }

    private void registryTuitionLoan(Student student, GrantLoan grantLoan, LocalDate today) {
        double amount = 0;
        if (!student.getUniversity().getTypeUniversity().equals(TypeUniversity.StateUniversityِDaily)) {
            if (student.getDegree().equals(Degree.ContinueBachelor) || student.getDegree().equals(Degree.PostDiploma) || student.getDegree().equals(Degree.DiscontinuousBachelor))
                amount = 13e5;
            else if (student.getDegree().equals(Degree.ContinueMaster) || student.getDegree().equals(Degree.DiscontinuousMaster) || equals(Degree.ContinuePHD))
                amount = 26e5;
            else if (student.getDegree().equals(Degree.DiscontinuousPHD)) amount = 65e5;
            grantLoan.setAmount(amount);
            grantLoan.setTypePayment(TypePayment.HALFYEAR);
            grantLoan.setDegree(student.getDegree());
            grantLoan.setTypeLoan(TypeLoan.EDUCATION);
            grantLoanRepository.save(grantLoan);
            studentLoanService.registryLoan(student, grantLoan, UtilDate.changeLocalDateToDate(today), null);
        }
    }

    private void registryEducationLoan(Student student, GrantLoan grantLoan, LocalDate today) {
        double amount = 0;
        if (student.getStudentLoanList().stream().anyMatch(studentLoan -> studentLoan.getReceiveDate().getYear() == today.getYear()))
            throw new RuntimeException("registry before loanEducation");
        else {
            if (student.getDegree().equals(Degree.ContinueBachelor) || student.getDegree().equals(Degree.PostDiploma) || student.getDegree().equals(Degree.DiscontinuousBachelor))
                amount = 19e5;
            else if (student.getDegree().equals(Degree.ContinueMaster) || student.getDegree().equals(Degree.DiscontinuousMaster) || equals(Degree.ContinuePHD))
                amount = 225e4;
            else if (student.getDegree().equals(Degree.DiscontinuousPHD))
                amount = 26e5;
            grantLoan.setAmount(amount);
            grantLoan.setTypePayment(TypePayment.HALFYEAR);
            grantLoan.setDegree(student.getDegree());
            grantLoan.setTypeLoan(TypeLoan.EDUCATION);
            grantLoanRepository.save(grantLoan);
            studentLoanService.registryLoan(student, grantLoan, UtilDate.changeLocalDateToDate(today), null);
        }
    }

    @Override
    public Loan getById(int id) {
        return grantLoanRepository.getById(id);
    }
}
