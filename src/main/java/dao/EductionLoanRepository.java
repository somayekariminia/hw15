package dao;

import model.entity.EducateLoan;

public class EductionLoanRepository extends LoanRepository<EducateLoan> {
    private static EductionLoanRepository eductionLoanRepository = new EductionLoanRepository();

    private EductionLoanRepository() {
    }

    public static EductionLoanRepository getInstance() {
        return eductionLoanRepository;
    }
}
