package com.arnavgpt.assistantbackend.entities;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "friend",
        indexes = {
                @Index(name = "idx_friend_user", columnList = "user_id"),
                @Index(name = "idx_friend_bday", columnList = "dobMonth,dobDay")
        })
public class Friend {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @Column(nullable = false, length = 200)
    private String fullName;

    @Column(nullable = false)
    private int dobMonth;

    @Column(nullable = false)
    private int dobDay;   // 1-31 (validated with month rules)

    @Column
    private Integer dobYear; // nullable

    @Column(length = 50)
    private String phone;

    @Column(length = 320)
    private String email;

    @Column(length = 500)
    private String notes;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @PrePersist
    void onCreate() { var now = Instant.now(); createdAt = now; updatedAt = now; }
    @PreUpdate
    void onUpdate() { updatedAt = Instant.now(); }

    public UUID getId() { return id; }
    public AppUser getUser() { return user; }
    public void setUser(AppUser user) { this.user = user; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public int getDobMonth() { return dobMonth; }
    public void setDobMonth(int dobMonth) { this.dobMonth = dobMonth; }
    public int getDobDay() { return dobDay; }
    public void setDobDay(int dobDay) { this.dobDay = dobDay; }
    public Integer getDobYear() { return dobYear; }
    public void setDobYear(Integer dobYear) { this.dobYear = dobYear; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}
