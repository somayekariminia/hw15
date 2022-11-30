package dao;

import model.entity.GrantLoan;

public class EductionLoanRepository extends LoanRepository<GrantLoan> {
    private static EductionLoanRepository eductionLoanRepository = new EductionLoanRepository();

    private EductionLoanRepository() {
    }

    public static EductionLoanRepository getInstance() {
        return eductionLoanRepository;
    }
}
