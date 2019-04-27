package com.mrporter.pomangam.common.updateChecker;

import com.mrporter.pomangam.common.util.time.CustomTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/updateCheckers")
@Slf4j
public class UpdateCheckController {

    @GetMapping
    public ResponseEntity updateCheckers() {
        final String update = "2019-04-27 20:00:00";
        log.info("[updateCheckers] update : {}, cur : {}", update, CustomTime.curDateTime());
        return new ResponseEntity(HttpStatus.OK);
    }
}
