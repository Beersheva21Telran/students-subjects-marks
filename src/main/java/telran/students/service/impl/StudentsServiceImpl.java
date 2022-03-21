package telran.students.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bson.Document;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.BasicQuery;
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
		StudentDoc student =studentsRepository.findByName(name);
		if(student == null) {
			return Collections.emptyList();
		}
		
		return student.getMarks().stream()
				.filter(sm -> sm.getSubject().equals(subject))
				.map(sm -> getStudentSubjectMark(sm, name)).toList();
	}
	private StudentSubjectMark getStudentSubjectMark(SubjectMark sm, String name) {
		return new StudentSubjectMark() {
			
			@Override
			public String getSubjectSubject() {
				return sm.getSubject();
			}
			
			@Override
			public String getStudentName() {
				return name;
			}
			
			@Override
			public int getMark() {
				return sm.getMark();
			}
		};
	}

	@Override
	public List<String> getBestStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getTopBestStudents(int nStudents) {
		List<AggregationOperation> listOperations = getStudentAvgMark();
		LimitOperation limit = Aggregation.limit(nStudents);
		listOperations.add(limit);
		List<Document> documentsRes =
				mongoTemplate.aggregate(Aggregation.newAggregation(listOperations), StudentDoc.class, Document.class)
				.getMappedResults();
		
		return documentsRes.stream().map(doc -> doc.getString("_id") + "," +
		doc.getDouble("avgMark").intValue()).toList();
	}

	private List<AggregationOperation> getStudentAvgMark() {
		UnwindOperation unwindOperation = Aggregation.unwind("marks");
		GroupOperation groupOperation = Aggregation.group("name").avg("marks.mark").as("avgMark");
		SortOperation sortOperation = Aggregation.sort(Direction.ASC, "avgMark");
		return new ArrayList<>(Arrays.asList(unwindOperation, groupOperation, sortOperation));
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
	public List<String> nativeQuery(String queryJson) {
		List<StudentDoc> res;
		try {
			BasicQuery query = new BasicQuery(queryJson);
			res = mongoTemplate.find(query, StudentDoc.class);
			return res.stream().map(StudentDoc::toString).toList();
		} catch (Exception e) {
			ArrayList<String> errorMessage = new ArrayList<>();
			errorMessage.add(e.getMessage());
			return errorMessage;
		}
		
	}

	@Override
	public List<Student> removeStudents(int avgMark, int nMarks) {
		// TODO Auto-generated method stub
		return null;
	}

}
