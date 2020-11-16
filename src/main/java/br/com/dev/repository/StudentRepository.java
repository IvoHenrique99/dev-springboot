package br.com.dev.repository;

import br.com.dev.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameIgnoreCaseContaining(String name); //fazer busca por nome ignorando caixa alta ou buscando semelhança.
}
