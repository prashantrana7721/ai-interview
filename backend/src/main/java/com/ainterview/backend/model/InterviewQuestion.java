package com.ainterview.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "interview_question")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String category;
}
