package telran.students.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.students.jpa.entities.MarkJpa;
import telran.students.service.interfaces.StudentSubjectMark;

public interface MarksRepository extends JpaRepository<MarkJpa, Integer> {
List<StudentSubjectMark> findByStudentNameAndSubjectSubject(String name, String subject);
}
