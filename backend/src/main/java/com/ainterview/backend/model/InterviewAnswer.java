package com.ainterview.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private InterviewQuestion question;

    @Column(length = 2000)
    private String answer;

    @Column(length = 2000)
    private String feedback;
}
