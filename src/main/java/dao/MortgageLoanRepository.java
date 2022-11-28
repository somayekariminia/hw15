package dao;

import model.entity.MortgageLoan;

public class MortgageLoanRepository extends LoanRepository<MortgageLoan> {
    private static MortgageLoanRepository mortgageLoanRepository = new MortgageLoanRepository();

    private MortgageLoanRepository() {
    }

    public static MortgageLoanRepository getInstance() {
        return mortgageLoanRepository;
    }
}
