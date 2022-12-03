package dao;

import model.entity.GrantLoan;

public class GrantLoanRepository extends LoanRepository<GrantLoan> {
    private static GrantLoanRepository eductionLoanRepository = new GrantLoanRepository();

    private GrantLoanRepository() {
    }

    public static GrantLoanRepository getInstance() {
        return eductionLoanRepository;
    }
}
