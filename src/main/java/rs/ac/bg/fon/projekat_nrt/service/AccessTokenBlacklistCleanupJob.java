package rs.ac.bg.fon.projekat_nrt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.projekat_nrt.service.security.AccessTokenBlacklistService;

@Component
@RequiredArgsConstructor
public class AccessTokenBlacklistCleanupJob {

    private final AccessTokenBlacklistService blacklistService;

    @Scheduled(fixedDelay = 60 * 1000)
    public void cleanup() {
        int deleted = blacklistService.cleanupExpiredWithLogging();
        if (deleted > 0) {
            System.out.println("Blacklist cleanup: obrisano " + deleted + " isteklih tokena.");
        }
    }
}
