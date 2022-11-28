package service;

import model.entity.Student;

public class MortgageLoanServiceImpl<MortgageLoan> extends LoanServiceImpl {
    @Override
    public boolean isRegistryStudentForLoan(Student student) {
        return super.isRegistryStudentForLoan(student);
    }
}
