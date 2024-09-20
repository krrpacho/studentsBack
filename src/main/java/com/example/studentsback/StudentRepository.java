package com.example.studentsback;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addStudent(Student student) {
        String sql = "INSERT INTO students (first_name, last_name, middle_name, date_of_birth, group_name) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getMiddleName(), student.getDateOfBirth(), student.getGroupName());
    }

    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM students";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Student student = new Student();
            student.setId(rs.getLong("id"));
            student.setFirstName(rs.getString("first_name"));
            student.setLastName(rs.getString("last_name"));
            student.setMiddleName(rs.getString("middle_name"));
            student.setDateOfBirth(rs.getString("date_of_birth"));
            student.setGroupName(rs.getString("group_name"));
            return student;
        });
    }

    public int deleteStudentById(Long id) {
        String sql = "DELETE FROM students WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
