package view;

import dao.StudentRepository;
import model.entity.Student;
import model.entity.StudentLoan;
import service.StudentLoanService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MenuStudent menuStudent=new MenuStudent();
        menuStudent.menuFirst();
    }


}
