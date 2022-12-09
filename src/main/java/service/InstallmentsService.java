package service;

import dao.InstallmentsRepository;
import model.entity.Loan;
import model.entity.Student;

public class InstallmentsService {
    private InstallmentsRepository installmentsRepository = InstallmentsRepository.getInstance();

    public void calculateInstallments(Loan loan) {
        double amount = loan.getAmount();
        int month = 12 + 24 + 48 + 96 + 192;
        double profit = amount * (4) * (month + 1) / (2400);
        double generalAmount = amount + profit;
        double eachInstallment = generalAmount / month;

        double firstYear = Math.round(eachInstallment);
        double secondYear = Math.round(firstYear * 2);
        double thirdYear = Math.round(secondYear * 2);
        double fourthYear = Math.round(thirdYear * 2);
        double fifthYear = Math.round(fourthYear * 2);
        double v = (fifthYear * 12) + (firstYear * 12) + (fourthYear * 12) + (secondYear * 12) + (thirdYear * 12);
    }

    public void save(Student student, Loan Loan) {

    }
}
