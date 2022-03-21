package telran.students.service.impl;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import telran.students.dto.Mark;
import telran.students.dto.Student;
import telran.students.dto.Subject;
import telran.students.entities.StudentDoc;
import telran.students.entities.SubjectDoc;
import telran.students.entities.SubjectMark;
import telran.students.repo.*;
import telran.students.service.interfaces.*;
@Service
public class StudentsServiceImpl implements StudentsService{
StudentsRepository studentsRepository;
SubjectsRepository subjectsRepository;
MongoTemplate mongoTemplate;

	public StudentsServiceImpl(StudentsRepository studentsRepository, SubjectsRepository subjectsRepository,
		MongoTemplate mongoTemplate) {
	this.studentsRepository = studentsRepository;
	this.subjectsRepository = subjectsRepository;
	this.mongoTemplate = mongoTemplate;
}

	@Override
	public void addStudent(Student student) {
		studentsRepository.save(new StudentDoc(student.stid, student.name));
		
	}

	@Override
	public void addSubject(Subject subject) {
		subjectsRepository.insert(new SubjectDoc(subject.suid, subject.subject));
		
	}

	@Override
	public Mark addMark(Mark mark) {
		StudentDoc student = studentsRepository.findById(mark.stid).orElse(null);
		if (student == null) {
			return null;
		}
		SubjectDoc subject = subjectsRepository.findById(mark.suid).orElse(null);
		if (subject == null) {
			return null;
		}
		student.getMarks().add(new SubjectMark(subject.getSubject(), mark.mark));
		studentsRepository.save(student);
		return mark;
	}

	@Override
	public List<StudentSubjectMark> getMarksStudentSubject(String name, String subject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getBestStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getTopBestStudents(int nStudents) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getTopBestStudentsSubject(int nStudents, String subject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentSubjectMark> getMarksOfWorstStudents(int nStudents) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IntervalMarks> marksDistibution(int interval) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> jpqlQuery(String jpql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> nativeQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> removeStudents(int avgMark, int nMarks) {
		// TODO Auto-generated method stub
		return null;
	}

}
