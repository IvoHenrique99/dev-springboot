package br.com.dev.endpoint;


import br.com.dev.error.ResourceNotFoundException;
import br.com.dev.model.Student;
import br.com.dev.model.StudentPage;
import br.com.dev.repository.StudentRepository;
import br.com.dev.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentEndpoint {
    private final StudentRepository studentDAO;
    private final StudentService studentService;

    public StudentEndpoint(StudentRepository studentDAO, StudentService studentService) {
        this.studentDAO = studentDAO;
        this.studentService = studentService;
    }

    @GetMapping //Fazer um um GET ||@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Student>> getStudents(StudentPage studentPage){
        return new ResponseEntity<>(studentService.getStudents(studentPage), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}") //Fazer um um GET || @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id,
                                            @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails);
        verifyIfStudentExists(id);
        Optional<Student> student = studentDAO.findById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    @GetMapping(path = "/findByName/{name}") //Fazer uma busca no banco "nome" *LEMBRE-SE DO CODIGO NO ROPOSITORY*
    public ResponseEntity<?> findStudentsByName(@PathVariable String name){
        return new ResponseEntity<>(studentDAO.findByNameIgnoreCaseContaining(name),HttpStatus.OK);
    }
    @PostMapping //Fazer um um POST || @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> save(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(studentDAO.save(student), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}") //Fazer um um DELETE || @RequestMapping(method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        verifyIfStudentExists(id);
        studentDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping  //Fazer um um PUT || @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Student student) {
        verifyIfStudentExists(student.getId());
        studentDAO.save(student);
        return new ResponseEntity<>(student, HttpStatus.OK);

    }
    private void verifyIfStudentExists(Long id){
        if(!studentDAO.findById(id).isPresent())
            throw new ResourceNotFoundException("Student not found for ID: "+id);
    }
}
