package telran.students.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import telran.students.service.interfaces.*;

@RestController
@RequestMapping("/students")
public class StudentsRestController {
	@Autowired
	StudentsService studentsService;
	@
public List<StudentSubjectMark> getStudentSubjectMark(String name, String subject) {
	return studentsService.getMarksStudentSubject(name, subject);
}
}
