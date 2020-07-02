package com.mrporter.pomangam.admin.controllers.staff;

import com.mrporter.pomangam.admin.domains.staff.StaffDto;
import com.mrporter.pomangam.admin.services.staff.StaffServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@PreAuthorize("isAuthenticated() and (hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF'))")
@RequestMapping("/admin/staffs")
@AllArgsConstructor
public class StaffController {

    StaffServiceImpl staffService;
   
    @PostAuthorize("( returnObject != null && returnObject.id == principal.username ) or hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public StaffDto searchInfo(
            @PathVariable(value = "id") String id,
            Principal principal
    ) {
        return staffService.findById(id);
    }

    @GetMapping("/search/exist/id")
    public ResponseEntity<?> isExistById(
            @RequestParam(value = "id", required = true) String id
    ) {
        return new ResponseEntity(staffService.isExistById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(
            @RequestBody(required = true) StaffDto dto
    ) {
        return new ResponseEntity<>(staffService.saveStaff(dto.toEntity()), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and #id == principal.username")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(
            @PathVariable(value = "id") String id
    ) {
        return new ResponseEntity<>(staffService.deleteStaff(id), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and #id == principal.username")
    @PatchMapping("/{id}")
    public ResponseEntity patch(
            @PathVariable(value = "id") String id,
            @RequestBody(required = true) StaffDto dto
    ) {
        return new ResponseEntity<>(staffService.patchStaff(id, dto.toEntity()), HttpStatus.OK);
    }
}
