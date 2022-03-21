package telran.students.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import telran.students.entities.StudentDoc;

public interface StudentsRepository extends MongoRepository<StudentDoc, Integer> {

}
