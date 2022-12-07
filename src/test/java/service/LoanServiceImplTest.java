package service;

import dao.GrantLoanRepository;
import dao.LoanRepository;
import model.entity.GrantLoan;
import model.entity.Loan;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanServiceImplTest {
    InstallmentsService installmentsService=new InstallmentsService();
    LoanRepository<GrantLoan>loanRepository=GrantLoanRepository.getInstance();
@Test
    void calculateInstallmentsTest(){
    Loan loan=loanRepository.getById(2);
    installmentsService.calculateInstallments(loan);
}
}