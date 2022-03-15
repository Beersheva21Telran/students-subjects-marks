package telran.students.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.students.jpa.entities.MarkJpa;
import telran.students.service.interfaces.StudentSubjectMark;

public interface MarksRepository extends JpaRepository<MarkJpa, Integer> {
List<StudentSubjectMark> findByStudentNameAndSubjectSubject(String name, String subject);

@Query("select s.name, round(avg(m.mark)) from MarkJpa m join m.student s group by"
		+ " s.name having avg(m.mark) > (select avg(mark) from MarkJpa) order by"
		+ " avg(m.mark) desc")
List<String> findBestStudents();
@Query(value="select name, round(avg(mark)) from marks join students "
		+ "on student_stid = stid group by name order by avg(mark) desc limit :nStudents",
		nativeQuery = true)
List<String> findTopBestStudents(@Param("nStudents") int nStudents);
}
