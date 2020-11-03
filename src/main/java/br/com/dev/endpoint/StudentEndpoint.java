package br.com.dev.endpoint;


import br.com.dev.error.CustomErrorType;
import br.com.dev.model.Student;
import br.com.dev.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("students")
public class StudentEndpoint {
    private final StudentRepository studentDAO;

    @Autowired
    public StudentEndpoint(StudentRepository studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping //Fazer um um GET ||@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listALL() {
        return new ResponseEntity<>(studentDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}") //Fazer um um GET || @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id) {
        Student student = studentDAO.findById(id).get();
        if (student == null)
            return new ResponseEntity<>(new CustomErrorType("Student not found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping //Fazer um um POST || @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody Student student) {
        return new ResponseEntity<>(studentDAO.save(student), HttpStatus.OK);

    }

    @DeleteMapping(path = "/{id}") //Fazer um um DELETE || @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        studentDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping  //Fazer um um PUT || @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Student student) {
        studentDAO.save(student);
        return new ResponseEntity<>(student, HttpStatus.OK);

    }
}
