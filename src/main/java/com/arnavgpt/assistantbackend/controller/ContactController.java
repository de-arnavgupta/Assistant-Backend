package com.arnavgpt.assistantbackend.controller;

import com.arnavgpt.assistantbackend.dto.friendDto.ContactDto;
import com.arnavgpt.assistantbackend.dto.friendDto.CreateContactCommand;
import com.arnavgpt.assistantbackend.dto.friendDto.UpdateContactCommand;
import com.arnavgpt.assistantbackend.entities.Contact;
import com.arnavgpt.assistantbackend.service.template.ContactService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/contacts")
public class ContactController {

    private final ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }

    private UUID parseInstallId(String header) {
        return UUID.fromString(header);
    }

    @GetMapping
    public Page<ContactDto> list(@RequestHeader("X-Install-Id") String header, Pageable pageable) {
        return service.list(parseInstallId(header), pageable).map(ContactDto::fromEntity);
    }

    @PostMapping
    public ContactDto create(@RequestHeader("X-Install-Id") String header,
                             @RequestBody CreateContactCommand cmd) {
        Contact contact = cmd.toEntity();
        return ContactDto.fromEntity(service.create(parseInstallId(header), contact));
    }

    @GetMapping("/{id}")
    public ContactDto get(@RequestHeader("X-Install-Id") String header,
                          @PathVariable UUID id) {
        return ContactDto.fromEntity(service.get(parseInstallId(header), id));
    }

    @PatchMapping("/{id}")
    public ContactDto update(@RequestHeader("X-Install-Id") String header,
                             @PathVariable UUID id,
                             @RequestBody UpdateContactCommand cmd) {
        Contact existing = service.get(parseInstallId(header), id);
        cmd.applyTo(existing);
        return ContactDto.fromEntity(service.update(parseInstallId(header), id, existing));
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestHeader("X-Install-Id") String header,
                       @PathVariable UUID id) {
        service.delete(parseInstallId(header), id);
    }
}
