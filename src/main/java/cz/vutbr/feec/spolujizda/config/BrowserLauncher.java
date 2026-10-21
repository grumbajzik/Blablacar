package cz.vutbr.feec.spolujizda.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.Desktop;
import java.net.URI;

@Component
public class BrowserLauncher {

    private static final Logger log = LoggerFactory.getLogger(BrowserLauncher.class);

    @Value("${server.port:8080}")
    private int port;

    @Value("${app.open-browser-on-startup:true}")
    private boolean openBrowser;

    @EventListener(ApplicationReadyEvent.class)
    public void openSwaggerInBrowser() {
        if (!openBrowser) {
            return;
        }

        String swaggerUrl = "http://localhost:" + port + "/swagger-ui/index.html";
        log.info("Aplikace úspěšně nastartovala. Otevírám Swagger UI v prohlížeči: {}", swaggerUrl);

        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "start", swaggerUrl).start();
            } else if (os.contains("mac")) {
                new ProcessBuilder("open", swaggerUrl).start();
            } else if (os.contains("nix") || os.contains("nux")) {
                new ProcessBuilder("xdg-open", swaggerUrl).start();
            } else if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(swaggerUrl));
            }
        } catch (Exception e) {
            log.warn("Nepodařilo se automaticky otevřít prohlížeč: {}", e.getMessage());
        }
    }
}
