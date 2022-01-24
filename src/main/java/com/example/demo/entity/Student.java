package com.example.demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Document(collection = "student")
public class Student {

    @Id
    private String id;

    private String name;

    @Field(name = "mail") // not necessary, will be the same name as a field
    private String email;

    // @DBRef using for create relationship between current entity and
    // Department entity in dtabase
    @DBRef
    private Department department;

    @DBRef
    private List<Subject> subjects;

    @Transient  // for ignore this field when save entity to database
    private double percentage;

    public Student() {

    }

    // have to use annotation @PersistenceConstructor when have multiple constructors.
    // for creating entity will be use constructor with current annotation
    @PersistenceConstructor
    public Student(String id, String name, String email, Department department, List<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.subjects = subjects;
    }

    public double getPercentage() {
        if (subjects != null && subjects.size() > 0) {
            AtomicInteger total = new AtomicInteger();
            subjects.forEach(s -> total.addAndGet(s.getMarksObtained()));
            return total.get()/subjects.size();
        }
        return 0.00;
    }
}
