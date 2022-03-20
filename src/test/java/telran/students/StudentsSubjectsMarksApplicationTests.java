package telran.students;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import telran.students.dto.Mark;
import telran.students.dto.Student;
import telran.students.dto.Subject;
import telran.students.service.interfaces.StudentsService;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentsSubjectsMarksApplicationTests {
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	MockMvc mockMvc;
	@Autowired
	StudentsService studentsService;

	@Test
	void contextLoads() {
		assertNotNull(mockMvc);
	}
	@Test
	@Order(1)
	void dbLoad() {
//		insert into students (stid, name) VALUES (1, 'Moshe');
//		insert into students (stid, name) VALUES (2, 'Sara');
//		insert into students (stid, name) VALUES (3, 'Vasya');
//		insert into subjects (suid, subject) VALUES (1,'React');
//		insert into subjects (suid, subject) VALUES (2, 'Java');
//		insert into marks (id, mark, student_stid, subject_suid) VALUES (1, 90, 1, 1);
//		insert into marks (id, mark, student_stid, subject_suid) VALUES (2, 90, 1, 2);
//		insert into marks (id, mark, student_stid, subject_suid) VALUES (3, 80, 2, 1);
//		insert into marks (id, mark, student_stid, subject_suid) VALUES (4, 80, 2, 2);
//		insert into marks (id, mark, student_stid, subject_suid) VALUES (5, 40, 3, 2);
		studentsService.addStudent(new Student(1, "Moshe"));
		studentsService.addStudent(new Student(2, "Sara"));
		studentsService.addStudent(new Student(3, "Vasya"));
		studentsService.addSubject(new Subject(1, "React"));
		studentsService.addSubject(new Subject(2, "Java"));
		studentsService.addMark(new Mark(1, 1, 90));
		studentsService.addMark(new Mark(1, 2, 90));
		studentsService.addMark(new Mark(2, 1, 80));
		studentsService.addMark(new Mark(2, 2, 80));
		studentsService.addMark(new Mark(3, 2, 40));
		
		
	}

	@Test
	
	void bestTopStudents() throws Exception {
		String resJSON = mockMvc.perform(MockMvcRequestBuilders.get("/students/best?amount=1")).andReturn()
				.getResponse().getContentAsString();
		String[] res = mapper.readValue(resJSON, String[].class);
		assertEquals(1, res.length);
		assertTrue(res[0].contains("Moshe"));

	}

	@Test

	void bestStudents() throws Exception {
		String resJSON = mockMvc.perform(MockMvcRequestBuilders.get("/students/best")).andReturn().getResponse()
				.getContentAsString();
		String[] res = mapper.readValue(resJSON, String[].class);
		assertEquals(2, res.length);
		assertTrue(res[0].contains("Moshe"));
		assertTrue(res[1].contains("Sara"));

	}

}
