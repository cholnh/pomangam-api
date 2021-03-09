package com.mrporter.pomangam._bases.scheduler;

import com.mrporter.pomangam.client.services.vbank.VBankServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VBankScheduleTask {

    VBankServiceImpl vBankService;

    @Scheduled(cron = "0 */2 0-10,14-15,20-23 * * *")
    public void task1() {
        vBankService.autoCheckDeposit(false);
    }

    @Scheduled(cron = "*/30 * 11-13,16-19 * * *")
    public void task2() {
        vBankService.autoCheckDeposit(false);
    }

    @Scheduled(cron = "0 */10 * * * *")
    public void task3() {
        vBankService.autoCheckDeposit(true);
    }

    @Scheduled(cron = "0 0 23 * * *")
    public void task4() {
        vBankService.clearOldReady();
    }

}
