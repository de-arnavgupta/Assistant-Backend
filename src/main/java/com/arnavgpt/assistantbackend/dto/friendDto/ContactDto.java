package com.arnavgpt.assistantbackend.dto.friendDto;

import com.arnavgpt.assistantbackend.entities.Contact;

import java.time.LocalDate;
import java.util.UUID;

public class ContactDto {
    private UUID id;
    private String displayName;
    private String givenName;
    private String familyName;
    private String phone;
    private String email;
    private LocalDate birthday; // optional

    public ContactDto() {}

    public ContactDto(UUID id, String displayName, String givenName,
                      String familyName, String phone, String email, LocalDate birthday) {
        this.id = id;
        this.displayName = displayName;
        this.givenName = givenName;
        this.familyName = familyName;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
    }

    public static ContactDto fromEntity(Contact c) {
        return new ContactDto(
                c.getId(),
                c.getDisplayName(),
                c.getGivenName(),
                c.getFamilyName(),
                c.getPhone(),
                c.getEmail(),
                c.getBirthday()
        );
    }

    // getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public String getGivenName() { return givenName; }
    public void setGivenName(String givenName) { this.givenName = givenName; }
    public String getFamilyName() { return familyName; }
    public void setFamilyName(String familyName) { this.familyName = familyName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }
}
