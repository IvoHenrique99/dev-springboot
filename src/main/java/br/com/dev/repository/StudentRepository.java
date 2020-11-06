package br.com.dev.repository;

import br.com.dev.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findByNameIgnoreCaseContaining(String name); //fazer busca por nome ignorando caixa alta ou buscando semelhança.
}
