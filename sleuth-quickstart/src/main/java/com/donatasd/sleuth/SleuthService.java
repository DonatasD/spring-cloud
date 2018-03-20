package com.donatasd.sleuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

@Service
public class SleuthService {

    private static Logger log = LoggerFactory.getLogger(SleuthService.class);

    private Tracer tracer;

    public SleuthService(Tracer tracer) {
        this.tracer = tracer;
    }

    public void singleSpanWork() throws InterruptedException {
        this.emulateWork();
        log.info("Completed single span work");
    }

    public void multipleSpanWork() throws InterruptedException  {
        log.info("Original span");
        Span span = tracer.createSpan("New Span");
        try {
            this.emulateWork();
            log.info("New span");
        } finally {
            tracer.close(span);
        }
        log.info("Original span");
    }

    private void emulateWork() throws InterruptedException {
        Thread.sleep(1000);
    }
}
