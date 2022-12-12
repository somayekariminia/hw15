package view;

import Exeption.ValidationException;
import Util.UtilDate;
import Util.ValidationInfoCreditCard;
import Util.ValidationInputs;
import com.github.eloyzone.jalalicalendar.JalaliDate;
import model.entity.*;
import model.enumes.Degree;
import model.enumes.TypeLoan;
import model.enumes.TypeUniversity;
import service.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MenuStudent {
    private static Scanner scanner = new Scanner(System.in);
    private StudentServiceImpl studentService = new StudentServiceImpl();
    private ValidationInputs validationInputs = ValidationInputs.getInstance();
    private LoanService<GrantLoan> grantLoanLoanService = new LoanServiceImpl();
    private MortgageLoanServiceImpl mortgageLoanLoanService = new MortgageLoanServiceImpl();
    private StudentLoanService studentLoanService = new StudentLoanService();
    private InstallmentsService installmentsService = new InstallmentsService();
    private PersonService personService = new PersonService();

    public void menuFirst() {
        System.out.println("1: signIn\n2: signUp");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                Student student = singIn();
                menuLoan(student);
            case 2:
                Student student1 = signUp();
                menuLoan(student1);
            case 3:
                menuFirst();
            case 4:
                System.exit(1);
        }
    }

    private Student signUp() {
        Student infoStudent = getInfoStudent();
        studentService.signUp(infoStudent);
        return infoStudent;
    }

    private void menuLoan(Student student) {
        System.out.println("1: registry\n2: repayment\n3: back");
        if (student != null) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    registryLoan(student);
                case 2:
                    repaymentOfLoans(student);
                case 3:
                    menuFirst();
            }
        }
    }

    private Student singIn() {
        String userName = enterUserName();
        String password = enterPassword();
        Student student = studentService.signIn(userName, password);
        return student;
    }

    private void repaymentOfLoans(Student student) {
        LocalDate localDate = LocalDate.now();
        Loan loan = null;
        StudentLoan studentLoan = getStudentLoan(student);
        int graduate = studentService.graduate(student);
        if (graduate > localDate.getYear()) {
            System.out.println("1: allinstallments\n2: intstallmentsPaid\n3: intstallmentsisNotPaid\n4:repaymentOfLoan ");
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
                case 4:
                    String numberCard = getNumberCard();
                    int cvv2 = scanner.nextInt();
                    JalaliDate dateXpire = getJalaliDate();
                    int id = scanner.nextInt();
                    installmentsService.paidInstallments(studentLoan, id, numberCard, cvv2, dateXpire);
                case 5:
                    singIn();

            }

        } else
            System.out.println("You haven't graduated");


    }

    private StudentLoan getStudentLoan(Student student) {
        Loan loan;
        List<StudentLoan> alLoansStudent = studentLoanService.getAlLoansStudent(student);
        alLoansStudent.forEach(studentLoan -> System.out.println(studentLoan.getLoan()));
        System.out.println("Please select the id_loan");
        int id = scanner.nextInt();
        System.out.println("Please select the typeLoan grantLoan or mortgageLoan");
        String typeLoan = scanner.nextLine();
        if (typeLoan.equalsIgnoreCase(String.valueOf(TypeLoan.MORTGAGELOAN)))
            loan = mortgageLoanLoanService.getById(id);
        else
            loan = grantLoanLoanService.getById(id);
        StudentLoan studentLoan = studentLoanService.getByStudentLoan(student, loan);
        return studentLoan;
    }

    private void registryLoan(Student student) {
        System.out.println("1: grateLoan(education or TUITION)\n 2:MortgageLoan");
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
        Person spouse = getSpouse();
        personService.save(spouse);
        studentService.update(student);
        System.out.println("enter number lease");
        String lease = scanner.nextLine();
        CreditCard infoCreditCard = getInfoCreditCard();
        mortgageLoanLoanService.requestForMortgageLoan(student, mortgageLoan, lease, dateRequestLoan, infoCreditCard);

    }

    private Student getInfoStudent() {
        Student student = new Student();
        System.out.println("name,lastName,NationalCode,nameFather,nameMother");
        String infoPerson = scanner.nextLine();
        String[] infoPersonArray = infoPerson.split(",");
        student.setFirstName(infoPersonArray[0]);
        student.setLastName(infoPersonArray[1]);
        student.setNationalCode(infoPersonArray[2]);
        student.setNameFather(infoPersonArray[3]);
        student.setNameFather(infoPersonArray[4]);
        System.out.println("enterDateBirthday:");
        JalaliDate dateBirthday = getJalaliDate();
        LocalDate birthday = UtilDate.changeJalaliDateToMiladi(dateBirthday);
        student.setBirthday(UtilDate.changeLocalDateToDate(birthday));
        System.out.println("are you isMarried: ");
        Boolean isMarried = Boolean.valueOf(scanner.nextLine());
        student.setMarried(isMarried);

        System.out.println("enter infoAccount userName");
        InfoAccount infoAccount = new InfoAccount();
        String userName = getUserName();
        infoAccount.setUserName(userName);
        infoAccount.setPassword(infoPersonArray[2]);

        student.setInfoAccount(infoAccount);
        Address address = getAddress();
        student.setAddress(address);

        System.out.println("enter information student: numberStudent ,enterYear,degree,drom");
        String infoStudent = scanner.nextLine();
        String[] infoStudentArray = infoStudent.split(",");
        student.setNumberStudent(infoStudentArray[0]);
        student.setEnterYear(Integer.parseInt(infoStudentArray[1]));
        student.setDegree(Degree.valueOf(infoStudentArray[2]));
        student.setDorm(Boolean.parseBoolean(infoStudentArray[3]));

        University university = getUniversity();
        student.setUniversity(university);
        return student;
    }

    private static University getUniversity() {
        System.out.println("enter info University:city,name,typeUnivercity ");
        String infoUniversity = scanner.nextLine();
        String[] infoUniversityArray = infoUniversity.split(",");
        University university = new University(0, infoUniversityArray[0], infoUniversityArray[1], TypeUniversity.valueOf(infoUniversityArray[2]));
        return university;
    }

    private Address getAddress() {
        Address address = new Address();
        System.out.println("enter address country , city,street");
        String infoAddress = scanner.nextLine();
        String[] infoAddressArray = infoAddress.split(",");
        address.setCountry(infoAddressArray[0]);
        address.setCity(infoAddressArray[1]);
        address.setStreet(infoAddressArray[2]);
        return address;
    }

    private String getUserName() {
        String userName = null;
        while (userName == null) {
            System.out.println("enter numberrCard");
            String usernameInput = scanner.nextLine();
            try {
                userName = ValidationInfoCreditCard.checkAccountNumber(usernameInput);

            } catch (ValidationException e) {
                userName = null;
                System.out.println(e.getMessage());
            }
        }
        return userName;
    }

    private Spouse getSpouse() {
        System.out.println("enter name and lastName and natinalCode");
        String infoSpouse = scanner.nextLine();
        String[] infoSpouseArray = infoSpouse.split(",");
        Spouse spouse = new Spouse();
        spouse.setFirstName(infoSpouseArray[0]);
        spouse.setLastName(infoSpouseArray[1]);
        spouse.setNationalCode(infoSpouseArray[2]);
        return spouse;
    }

    private CreditCard getInfoCreditCard() {
        Random random = new Random();
        CreditCard creditCard = new CreditCard();
        String numberCard = getNumberCard();
        String numberAccount = getNumberAccount();
        JalaliDate jalaliDate = getJalaliDate();
        LocalDate localDate = UtilDate.changeJalaliDateToMiladi(jalaliDate);
        Date date = UtilDate.changeLocalDateToDate(localDate);
        creditCard.setBalance(0);
        creditCard.setCcv2(random.nextInt(100, 1000));
        creditCard.setCardNumber(numberCard);
        creditCard.setAccountNumber(numberAccount);
        creditCard.setExpireDate(date);
        return creditCard;
    }

    private String getNumberCard() {
        String correctNumberAccount = null;
        while (correctNumberAccount == null) {
            System.out.println("enter numberrCard");
            String accountNumber = scanner.nextLine();
            try {
                correctNumberAccount = ValidationInfoCreditCard.checkAccountNumber(accountNumber);

            } catch (ValidationException e) {
                correctNumberAccount = null;
                System.out.println(e.getMessage());
            }
        }
        return correctNumberAccount;
    }

    private String getNumberAccount() {
        String numberAccount = null;
        while (numberAccount == null) {
            System.out.println("enter numberrCard");
            String accountNumber = scanner.nextLine();
            try {
                numberAccount = ValidationInfoCreditCard.checkAccountNumber(accountNumber);

            } catch (ValidationException e) {
                numberAccount = null;
                System.out.println(e.getMessage());
            }
        }
        return numberAccount;
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
        CreditCard infoCreditCard = getInfoCreditCard();
        grantLoanLoanService.requestLoan(student, grantLoan, jalaliDate, infoCreditCard);
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
            try {
                correctUserName = validationInputs.checkUserName(userName);
            } catch (ValidationException e) {
                correctUserName = null;
                System.out.println(e.getMessage());
            }

        }
        return correctUserName;
    }

    private String enterPassword() {
        String correctPassword = null;
        while (correctPassword == null) {
            try {
                System.out.println("enter format natinalcode 10 digit and [0-9]");
                String userName = scanner.nextLine();
                correctPassword = validationInputs.checkUserName(userName);
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }

        }
        return correctPassword;
    }

    private String getInputsString() {
        String correctInput = null;
        while (correctInput == null) {
            System.out.println("enter inputs String [a-zA-Z]");
            String inputsString = scanner.nextLine();
            try {
                correctInput = validationInputs.checkUserName(inputsString);
            } catch (ValidationException e) {
                correctInput = null;
                System.out.println(e.getMessage());
            }
        }
        return correctInput;
    }
}
