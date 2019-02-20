package com.mrporter.pomangam.view.main.domain;

import com.mrporter.pomangam.advertiseEntry.imageForMain.domain.ImageForMainDto;
import com.mrporter.pomangam.advertiseEntry.subAdvertiseForMain.domain.SubAdvertiseForMainDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class MainSecondViewDto {
    private List<ImageForMainDto> imageForMainDtoList;

    private List<SubAdvertiseForMainDto> subAdvertiseForMainDtoList;
}
