package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    StudentRepository studentRepository;
    DepartmentRepository departmentRepository;
    SubjectRepository subjectRepository;

    public Student createStudent(Student student) {
        // create entity if ID is empty
        // otherwise will update current entity by ID
//        return studentRepository.save(student);

        // create entity with relationship with another entities
        // so before create each entity in relationship
        if (student.getDepartment() != null) {
            departmentRepository.save(student.getDepartment());
        }
        if (student.getSubjects() != null && student.getSubjects().size() > 0) {
            subjectRepository.saveAll(student.getSubjects());
        }

        return studentRepository.save(student);
    }

    public Student getStudentById(String id) {
        // find entity by ID
        return studentRepository.findById(id).orElseThrow();
    }

    public List<Student> getAllStudents() {
        // find all entities
        return studentRepository.findAll();
    }

    public Student updateStudent(Student student) {
        // if student have id field current method will update entity,
        // otherwise will create new entity
        return studentRepository.save(student);
    }

    public String deleteStudent(String id) {
        // delete entity by ID
        studentRepository.deleteById(id);
        return "Student has been deleted";
    }

    public List<Student> getStudentsByName(String name) {
        // get document by field 'name'
//        return studentRepository.findByName(name);
        return studentRepository.getByName(name); // with native query
    }

    public List<Student> getStudentsByNameAndMail(String name, String email) {
        // get document by field 'name' AND field 'email'
        return studentRepository.findByNameAndEmail(name, email);
    }

    public List<Student> getStudentsByNameOrMail(String name, String email) {
        // get document by field 'name' OR field 'email'
        return studentRepository.findByNameOrEmail(name, email);
    }

    public List<Student> getAllWithPagination(int pageSize, int pageNo) {
        // find all entities with pagination result and
        // ascending sorting by field 'name'
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return studentRepository.findAll(pageable).getContent();
    }

    public List<Student> byDepartmentName(String deptName) {
        // find all entities by sub document field 'departmentName'
        return studentRepository.findByDepartmentDepartmentName(deptName);
    }

    public List<Student> bySubjectName(String subjectName) {
        // find all entities by array value 'subjectName' in array 'Subjects'
        return studentRepository.findBySubjectsSubjectName(subjectName);
    }

    public List<Student> emailLike(String email) {
        // find all entities by like query for 'email' field
        return studentRepository.findByEmailIsLike(email);
    }

    public List<Student> nameStartsWith(String name) {
        // find all entities by 'Starts with' query for 'name' field
        return studentRepository.findByNameStartsWith(name);
    }

    public List<Student> byDepartmentId(String deptId) {
        // find all 'Student' entities by field 'id'
        // in OneToOne entity 'Department'
        return studentRepository.findByDepartmentId(deptId);
    }
}
