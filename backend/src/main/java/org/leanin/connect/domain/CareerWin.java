package org.leanin.connect.domain;

import jakarta.persistence.*;
import java.time.Instant;

/** Persisted career milestone shared by a Lean In member. */
@Entity
@Table(name = "career_wins")
public class CareerWin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 80)
    private String author;
    @Column(nullable = false, length = 360)
    private String message;
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    protected CareerWin() { }
    public CareerWin(String author, String message) { this.author = author; this.message = message; this.createdAt = Instant.now(); }
    public Long getId() { return id; }
    public String getAuthor() { return author; }
    public String getMessage() { return message; }
    public Instant getCreatedAt() { return createdAt; }
}
