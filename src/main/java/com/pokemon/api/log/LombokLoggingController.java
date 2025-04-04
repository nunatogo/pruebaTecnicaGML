package com.pokemon.api.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LombokLoggingController {

    @RequestMapping("/lombok")
    public String index() {
        log.trace("... TRACE ...");
        log.debug("... DEBUG ...");
        log.info("... INFO ...");
        log.warn("... WARN ...");
        log.error("... ERROR ...");

        return "Generation log";
    }
}
