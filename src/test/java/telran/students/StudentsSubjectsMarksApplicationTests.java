package telran.students;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.MethodOrderer;
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

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestMethodOrder(MethodOrderer.MethodName.class)
class StudentsSubjectsMarksApplicationTests {
	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertNotNull(mockMvc);
	}

@Test
	
	void bestTopStudents() throws  Exception {
		String resJSON = mockMvc.perform(MockMvcRequestBuilders.get("/students/best?amount=1"))
				.andReturn().getResponse().getContentAsString();
		String[] res = mapper.readValue(resJSON, String[].class);
		assertEquals(1, res.length);
		assertTrue(res[0].contains("Moshe"));
}
	@Test
	@Sql("testDB.sql")
	void bestStudents() throws  Exception {
		String resJSON = mockMvc.perform(MockMvcRequestBuilders.get("/students/best"))
				.andReturn().getResponse().getContentAsString();
		String[] res = mapper.readValue(resJSON, String[].class);
		assertEquals(2, res.length);
		assertTrue(res[0].contains("Moshe"));
		assertTrue(res[1].contains("Sara"));
		
		
		
	}
	
		
		
		
		
	}

