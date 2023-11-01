package com.soksoft.test.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Getter@Setter@AllArgsConstructor@RequiredArgsConstructor@ToString@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;
    @Column(length = 255)
    private String first_name;
    @Column(length = 255)
    private String last_name;
    @Column(name = "Email", unique = true)
    private String email;
    @Column(name = "Age")
    private int age;

}
