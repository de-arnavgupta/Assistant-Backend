package com.arnavgpt.assistantbackend.service.impl;

import com.arnavgpt.assistantbackend.entities.Contact;
import com.arnavgpt.assistantbackend.repositories.ContactRepository;
import com.arnavgpt.assistantbackend.service.template.ContactService;
import jakarta.validation.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repo;

    public ContactServiceImpl(ContactRepository repo) {
        this.repo = repo;
    }

    @Override
    public Page<Contact> list(UUID installId, Pageable pageable) {
        return repo.findByInstallId(installId, pageable);
    }

    @Override
    public Contact create(UUID installId, Contact contact) {
        contact.setId(null);
        contact.setInstallId(installId);
        return repo.save(contact);
    }

    @Override
    public Contact get(UUID installId, UUID id) {
        return repo.findByIdAndInstallId(id, installId)
                .orElseThrow(() -> new ValidationException("Not found"));
    }

    @Override
    public Contact update(UUID installId, UUID id, Contact patch) {
        Contact existing = get(installId, id);
        if (patch.getDisplayName() != null) existing.setDisplayName(patch.getDisplayName());
        if (patch.getGivenName() != null) existing.setGivenName(patch.getGivenName());
        if (patch.getFamilyName() != null) existing.setFamilyName(patch.getFamilyName());
        if (patch.getPhone() != null) existing.setPhone(patch.getPhone());
        if (patch.getEmail() != null) existing.setEmail(patch.getEmail());
        existing.setBirthday(patch.getBirthday()); // can be null
        return repo.save(existing);
    }

    @Override
    public void delete(UUID installId, UUID id) {
        repo.deleteByIdAndInstallId(id, installId);
    }
}
