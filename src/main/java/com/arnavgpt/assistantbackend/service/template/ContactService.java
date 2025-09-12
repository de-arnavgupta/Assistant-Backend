package com.arnavgpt.assistantbackend.service.template;

import com.arnavgpt.assistantbackend.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ContactService {
    Page<Contact> list(UUID installId, Pageable pageable);
    Contact create(UUID installId, Contact contact);
    Contact get(UUID installId, UUID id);
    Contact update(UUID installId, UUID id, Contact patch);
    void delete(UUID installId, UUID id);
}
