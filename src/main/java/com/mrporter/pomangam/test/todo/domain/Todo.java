package com.mrporter.pomangam.test.todo.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "todo_tbl")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Todo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idx;

    String username;

    String title;

    String contents;

    Byte completed;


    @Builder
    public Todo(String username, String title, String contents, Byte completed) {
        this.username = username;
        this.title = title;
        this.contents = contents;
        this.completed = completed;
    }
}
