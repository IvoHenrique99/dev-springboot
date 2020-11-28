package br.com.dev.service;

import br.com.dev.model.Student;
import br.com.dev.model.StudentPage;
import br.com.dev.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentDAO;

    public StudentService(StudentRepository studentDAO) { this.studentDAO = studentDAO; }

    public Page<Student> getStudents(StudentPage studentPage){
        Sort sort = Sort.by(studentPage.getSortDirection(), studentPage.getSort());
        Pageable pageable = PageRequest.of(studentPage.getPage(), studentPage.getSize(), sort );
        return studentDAO.findAll(pageable);

    }
}
