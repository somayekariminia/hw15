package view;

import Util.ValidationInputs;
import com.github.eloyzone.jalalicalendar.JalaliDate;
import model.entity.*;
import model.enumes.TypeLoan;
import service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuStudent {
    private static Scanner scanner = new Scanner(System.in);
    private StudentServiceImpl studentService = new StudentServiceImpl();
    private ValidationInputs validationInputs = ValidationInputs.getInstance();
    private LoanService<GrantLoan> grantLoanLoanService = new LoanServiceImpl();
    private MortgageLoanServiceImpl mortgageLoanLoanService = new MortgageLoanServiceImpl();
    private StudentLoanService studentLoanService = new StudentLoanService();
    private InstallmentsService installmentsService = new InstallmentsService();


    public void menuFirst() {

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                menuTwo();
            case 2:
                menuethree();
            case 3:
                menuFirst();
            case 4:
                exite;
        }
    }

    private void menuTwo() {
        String userName = enterUserName();
        String password = enterPassword();
        Student student = studentService.signIn(userName, password);
        if (student != null) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    registryLoan(student);
                case 2:
                    viewInstallmentLoan(student);
                case 3:
                    menuFirst();
            }
        }
    }

    private void viewInstallmentLoan(Student student) {
        LocalDate localDate = LocalDate.now();
        Loan loan = null;
        StudentLoan studentLoan = getStudentLoan(student);
        int graduate = studentService.graduate(student);
        if (graduate > localDate.getYear()) {
            System.out.println("enter");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:

                    List<Installments> installments = installmentsService.allInstallment(studentLoan);
                    System.out.println(installments);

                case 2:
                    List<Installments> paidInstallments = installmentsService.isPaidInstallments(studentLoan);
                    paidInstallments.forEach(installments1 -> System.out.println(installments1.getId() + "  date: " + installments1.getDate()));
                case 3:
                    List<Installments> notPaidInstallments = installmentsService.isNotPaidInstallments(studentLoan);
                    notPaidInstallments.forEach(installments1 -> System.out.println(installments1.getId() + "  date: " + installments1.getDate() + " amoant: " + installments1.getAmount()));
            }

        } else
            System.out.println("You haven't graduated");


    }

    private List<Installments> viewAllInstallments(Student student) {


    }

    private StudentLoan getStudentLoan(Student student) {
        Loan loan;
        List<StudentLoan> alLoansStudent = studentLoanService.getAlLoansStudent(student);
        alLoansStudent.forEach(studentLoan -> System.out.println(studentLoan.getLoan()));
        System.out.println("Please select the id_loan");
        int id = scanner.nextInt();
        System.out.println("Please select the typeLoan grantLoan or mortgageLoan");
        String typeLoan = scanner.nextLine();
        if (typeLoan.equals("mortgageLoan"))
            loan = mortgageLoanLoanService.getById(id);
        else
            loan = grantLoanLoanService.getById(id);
        StudentLoan studentLoan = studentLoanService.getByStudentLoan(student, loan);
        return studentLoan;
    }

    private void registryLoan(Student student) {
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                registryEducateLoan(student);
            case 2:
                registryMortGageLoan(student);
        }
    }

    private void registryMortGageLoan(Student student) {
        MortgageLoan mortgageLoan = new MortgageLoan();
        JalaliDate dateRequestLoan = getJalaliDate();
        System.out.println("enter number lease");
        String lease = scanner.nextLine();
        mortgageLoanLoanService.requestForMortgageLoan(student, mortgageLoan, lease, dateRequestLoan);

    }

    private void registryEducateLoan(Student student) {
        TypeLoan typeLoan = null;
        GrantLoan grantLoan = new GrantLoan();
        System.out.println("enter type Educateloan  1: EDUCATION\n2: TUITION");
        int input = scanner.nextInt();
        if (TypeLoan.EDUCATION.ordinal() == input)
            typeLoan = TypeLoan.EDUCATION;
        else
            typeLoan = TypeLoan.TUITION;
        grantLoan.setTypeLoan(typeLoan);
        JalaliDate jalaliDate = getJalaliDate();
        grantLoanLoanService.requestLoan(student, grantLoan, jalaliDate);
    }

    private static JalaliDate getJalaliDate() {
        System.out.println("Enter date Shamsi to Format yyyy-mm-dd");
        String inputDate = scanner.nextLine();
        String[] datArray = inputDate.split("-");
        JalaliDate jalaliDate = new JalaliDate(Integer.parseInt(datArray[0]), Integer.parseInt(datArray[1]), Integer.parseInt(datArray[1]));
        return jalaliDate;
    }

    private String enterUserName() {
        String correctUserName = null;
        while (correctUserName == null) {
            System.out.println("enter UserName containt camelcase uppercase number and [!,@,#,$,%,*] and 8 digit ");
            String userName = scanner.nextLine();
            correctUserName = validationInputs.checkUserName(userName);
        }
        return correctUserName;
    }

    private String enterPassword() {
        String correctPassword = null;
        while (correctPassword == null) {
            System.out.println("enter format natinalcode 10 digit and [0-9]");
            String userName = scanner.nextLine();
            correctPassword = validationInputs.checkUserName(userName);
        }
        return correctPassword;
    }
}
