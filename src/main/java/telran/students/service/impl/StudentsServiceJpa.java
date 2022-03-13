package telran.students.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.students.dto.Mark;
import telran.students.dto.Student;
import telran.students.dto.Subject;
import telran.students.service.interfaces.*;
import telran.students.jpa.entities.MarkJpa;
import telran.students.jpa.entities.StudentJpa;
import telran.students.jpa.entities.SubjectJpa;
import telran.students.jpa.repo.*;
@Service
public class StudentsServiceJpa implements StudentsService{
StudentsRepository studentsRepository;
SubjectsRepository subjectsRepository;
MarksRepository marksRepository;
@Autowired
	public StudentsServiceJpa(StudentsRepository studentsRepository, SubjectsRepository subjectsRepository,
		MarksRepository marksRepository) {
	
	this.studentsRepository = studentsRepository;
	this.subjectsRepository = subjectsRepository;
	this.marksRepository = marksRepository;
}

	@Override
	public void addStudent(Student student) {
		studentsRepository.save(StudentJpa.build(student));
		
	}

	@Override
	public void addSubject(Subject subject) {
		subjectsRepository.save(SubjectJpa.build(subject));
		
	}

	@Override
	@Transactional 
	public Mark addMark(Mark mark) {
		StudentJpa student = studentsRepository.findById(mark.stid).orElse(null);
		SubjectJpa subject = subjectsRepository.findById(mark.suid).orElse(null);
		if (student != null && subject != null) {
			MarkJpa markJpa = new MarkJpa(mark.mark, student, subject);
			marksRepository.save(markJpa);
			return mark;
		}
		
		return null;
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

}
