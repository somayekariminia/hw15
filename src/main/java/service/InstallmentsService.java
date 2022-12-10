package service;

import Util.UtilDate;
import com.github.eloyzone.jalalicalendar.JalaliDate;
import dao.InstallmentsRepository;
import dao.StudentLoanRepository;
import model.entity.Installments;
import model.entity.StudentLoan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class InstallmentsService {
    private InstallmentsRepository installmentsRepository = InstallmentsRepository.getInstance();
    private StudentLoanRepository studentLoanRepository = StudentLoanRepository.getInstance();
    private StudentServiceImpl studentService = new StudentServiceImpl();

    public List<Installments> calculateInstallments(StudentLoan studentLoan, JalaliDate date) {
        LocalDate localDate = UtilDate.changeJalaliDateToMiladi(date);
        double amount = studentLoan.getLoan().getAmount();
        int yearsPayment = 5;
        double profit = 0.4;
        double installmentFirstYear = calculateInstallmentFirstYear(amount, yearsPayment, profit);
        List<Installments> installmentsList = new ArrayList<>();
        LocalDate localDate1 = localDate;
        for (int i = 1; i <= yearsPayment; i++) {
            for (int j = 1; j <= 12; j++) {
                Installments installments1 = new Installments();
                installments1.setStudentLoan(studentLoan);
                installments1.setPaid(false);
                localDate1 = UtilDate.incrementToMonth(localDate1);
                installments1.setDate(UtilDate.changeLocalDateToDate(localDate1));
                installments1.setAmount(installmentFirstYear + (Math.pow(yearsPayment - 1, 0.2)) * installmentFirstYear);
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
    public void paidInstallments(int id){
        installmentsRepository.getById(id);
    }
}