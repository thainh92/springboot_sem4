package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Education {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "school")
    private String school;
    @Basic
    @Column(name = "level")
    private String level;
    @Basic
    @Column(name = "course")
    private String course;
    @Basic
    @Column(name = "gpa")
    private double gpa;
    @Basic
    @Column(name = "start_date")
    private Date startDate;
    @Basic
    @Column(name = "end_date")
    private Date endDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Education education = (Education) o;
        return id == education.id && Double.compare(education.gpa, gpa) == 0 && Objects.equals(school, education.school) && Objects.equals(level, education.level) && Objects.equals(course, education.course) && Objects.equals(startDate, education.startDate) && Objects.equals(endDate, education.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, school, level, course, gpa, startDate, endDate);
    }
}
