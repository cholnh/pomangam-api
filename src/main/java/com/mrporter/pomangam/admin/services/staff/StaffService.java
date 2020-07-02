package com.mrporter.pomangam.admin.services.staff;

import com.mrporter.pomangam.admin.domains.staff.Staff;
import com.mrporter.pomangam.admin.domains.staff.StaffDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffService {

    StaffDto findById(String id);

    Long findIdxById(String id);

    List<StaffDto> findAll();

    List<StaffDto> findAll(Pageable pageable);

    StaffDto saveStaff(Staff Staff);

    StaffDto updateStaffPassword(String id, String password);

    Boolean isExistById(String id);

    StaffDto patchStaff(String id, Staff Staff);

    Boolean deleteStaff(String id);
}
