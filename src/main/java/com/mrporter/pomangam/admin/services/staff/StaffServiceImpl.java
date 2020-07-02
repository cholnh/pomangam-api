package com.mrporter.pomangam.admin.services.staff;

import com.mrporter.pomangam._bases.utils.formatter.PhoneNumberFormatter;
import com.mrporter.pomangam._bases.utils.reflection.ReflectionUtils;
import com.mrporter.pomangam.admin.domains.staff.Staff;
import com.mrporter.pomangam.admin.domains.staff.StaffDto;
import com.mrporter.pomangam.admin.repositories.staff.StaffJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class StaffServiceImpl implements StaffService {

    PasswordEncoder passwordEncoder;
    StaffJpaRepository StaffRepo;

    @Override
    public StaffDto findById(String id) {
        Staff staff = StaffRepo.findByIdAndIsActiveIsTrue(id);
        if(staff == null) {
            return null;
        }
        StaffDto staffDto = StaffDto.fromEntity(staff);
        return staffDto;
    }

    @Override
    public Long findIdxById(String id) {
        return StaffRepo.findIdxByIdAndIsActiveIsTrue(id);
    }

    @Override
    public List<StaffDto> findAll() {
        return StaffDto.fromEntities(StaffRepo.findAll());
    }

    @Override
    public List<StaffDto> findAll(Pageable pageable) {
        return StaffDto.fromEntities(StaffRepo.findAll(pageable).getContent());
    }

    @Override
    @Transactional
    public StaffDto saveStaff(Staff staff) {
        staff.getPassword().setFailedCount(0);
        staff.getPassword().setValue(passwordEncoder.encode(staff.getPassword().getValue()));
        staff.setPhoneNumber(PhoneNumberFormatter.format(staff.getPhoneNumber()));

        StaffDto dto = StaffDto.fromEntity(StaffRepo.save(staff));

        return dto;
    }

    @Override
    public Boolean isExistById(String id) {
        if(id != null) {
            return StaffRepo.existsById(id);
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public StaffDto updateStaffPassword(String id, String password) {
        final Staff fetched = StaffRepo.findByIdAndIsActiveIsTrue(id);
        if (fetched == null) {
            return null;
        }
        fetched.getPassword().setValue(passwordEncoder.encode(password));
        fetched.setModifyDate(LocalDateTime.now());

        StaffRepo.save(fetched);

        return StaffDto.fromEntity(fetched);
    }

    @Override
    @Transactional
    public StaffDto patchStaff(String id, Staff staff) {
        final Staff fetched = StaffRepo.findByIdAndIsActiveIsTrue(id);
        if(fetched == null) {
            return null;
        }

        try {
            ReflectionUtils.oldInstanceByNewInstance(fetched, staff);
            StaffRepo.save(fetched);

            return StaffDto.fromEntity(fetched);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public Boolean deleteStaff(String id) {
        final Staff fetched = StaffRepo.findByIdAndIsActiveIsTrue(id);
        if (fetched == null) {
            return false;
        } else {
            StaffRepo.delete(fetched);
            return true;
        }
    }
}
