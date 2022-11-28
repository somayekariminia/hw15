package dao;

import model.entity.EducateLoan;

public class EductionLoanRepository extends LoanRepository<EducateLoan>{
    private static EductionLoanRepository eductionLoanRepository=new EductionLoanRepository();
    public static EductionLoanRepository getInstance() {
        return eductionLoanRepository;
    }
    private EductionLoanRepository() {
    }
}
