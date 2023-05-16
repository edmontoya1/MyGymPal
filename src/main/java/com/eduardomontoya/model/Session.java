package com.eduardomontoya.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @SequenceGenerator(
            name = "session_id_sequence",
            sequenceName = "session_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "session_id_sequence"
    )
    private Long id;

    @Column
    private Date date;
    @Column
    private String excercise;

    public Session(Date date, String excercise) {
        this.date = date;
        this.excercise = excercise;
    }

    public Session() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getExcercise() {
        return excercise;
    }

    public void setExcercise(String excercise) {
        this.excercise = excercise;
    }
}
