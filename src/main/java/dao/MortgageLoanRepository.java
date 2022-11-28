package dao;

import model.entity.Loan;
import model.entity.MortgageLoan;

public class MortgageLoanRepository extends LoanRepository<MortgageLoan> {
    private static MortgageLoanRepository mortgageLoanRepository=new MortgageLoanRepository();
    public static MortgageLoanRepository getInstance() {
        return mortgageLoanRepository;
    }
    private MortgageLoanRepository() {
    }
}
