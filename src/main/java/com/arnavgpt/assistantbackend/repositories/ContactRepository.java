package com.arnavgpt.assistantbackend.repositories;

import com.arnavgpt.assistantbackend.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID> {
    Page<Contact> findByInstallId(UUID installId, Pageable pageable);
    Optional<Contact> findByIdAndInstallId(UUID id, UUID installId);
    void deleteByIdAndInstallId(UUID id, UUID installId);
}
