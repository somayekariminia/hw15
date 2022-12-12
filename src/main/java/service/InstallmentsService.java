package service;

import Util.UtilDate;
import com.github.eloyzone.jalalicalendar.JalaliDate;
import dao.InstallmentsRepository;
import dao.StudentLoanRepository;
import model.entity.CreditCard;
import model.entity.Installments;
import model.entity.StudentLoan;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class InstallmentsService {
    private final InstallmentsRepository installmentsRepository = InstallmentsRepository.getInstance();
    private final StudentLoanRepository studentLoanRepository = StudentLoanRepository.getInstance();
    private final StudentServiceImpl studentService = new StudentServiceImpl();

    public Set<Installments> calculateInstallments(StudentLoan studentLoan, JalaliDate date) {
        LocalDate localDate = UtilDate.changeJalaliDateToMiladi(date);
        double amount = studentLoan.getLoan().getAmount();
        int yearsPayment = 5;
        double profit = 0.4;
        double installmentFirstYear = calculateInstallmentFirstYear(amount, yearsPayment, profit);
        Set<Installments> installmentsList = new HashSet<>();
        LocalDate localDate1 = localDate;
        double installmentFirstValue=0;
        for (int i = 1; i <= yearsPayment; i++) {
            for (int j = 1; j <= 12; j++) {
                Installments installments1 = new Installments();
                installments1.setStudentLoan(studentLoan);
                installments1.setPaid(false);
                localDate1 = UtilDate.incrementToMonth(localDate1);
                installments1.setDate(UtilDate.changeLocalDateToDate(localDate1));
                double value=installmentFirstYear + (Math.pow(yearsPayment , 0.2)) * installmentFirstValue;
                installments1.setAmount(value );
                installmentFirstValue=installmentFirstYear;
                installmentsList.add(installments1);
            }
        }
        return installmentsList;
    }

    private double calculateInstallmentFirstYear(double amount, int yearsPayment, double profit) {
        double profitLoan = amount * (4) * (yearsPayment * 12 + 1) / (2400);
        double generalAmount = amount + profitLoan;
        double eachInstallment = generalAmount / yearsPayment * 12;
        return eachInstallment;
    }

    public List<Installments> allInstallment(StudentLoan studentLoan) {
        List<Installments> byStudentLoan = installmentsRepository.getByStudentLoan(studentLoan);
        if (byStudentLoan != null)
            return byStudentLoan;
        else
            throw new RuntimeException("list is empty");
    }

    public Installments paidInstallments(StudentLoan studentLoan, int id, String cardNumber, int cvv2, JalaliDate date) {
        CreditCard creditCard = studentLoan.getCreditCard();
        Installments installment=null;
        Installments updateInstallments=null;
        if (creditCard.getCardNumber().equals(cardNumber) && creditCard.getCcv2()==cvv2) {
            LocalDate expireDate = UtilDate.changeJalaliDateToMiladi(date);
            LocalDate today = LocalDate.now();
            if (expireDate.getYear() >= today.getYear() && expireDate.getMonthValue() >= today.getMonthValue())
              installment = installmentsRepository.getById(id);
            installment.setPaid(true);
            updateInstallments = installmentsRepository.update(installment);
        }
        return updateInstallments;
    }

    public List<Installments> isPaidInstallments(StudentLoan studentLoan) {
        List<Installments> installments = allInstallment(studentLoan);
        return installments.stream().filter(Installments::isPaid).sorted(Comparator.comparing(Installments::getDate)).collect(Collectors.toList());

    }

    public List<Installments> isNotPaidInstallments(StudentLoan studentLoan) {
        List<Installments> installments = allInstallment(studentLoan);
        return installments.stream().filter(installments1 -> !installments1.isPaid()).sorted(Comparator.comparing(Installments::getDate)).toList();

    }
}
