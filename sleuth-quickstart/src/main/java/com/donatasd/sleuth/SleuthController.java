package com.donatasd.sleuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class SleuthController {

    private static Logger log = LoggerFactory.getLogger(SleuthController.class);

    private SleuthService sleuthService;

    @Autowired
    public SleuthController(SleuthService sleuthService) {
        this.sleuthService = sleuthService;
    }

    @RequestMapping("single-span")
    public String singleSpan() throws InterruptedException {
        log.info("Single span");
        sleuthService.singleSpanWork();
        return "success";
    }

    @RequestMapping("multiple-span")
    public String multipleSpans() throws InterruptedException {
        log.info("Original span");
        sleuthService.multipleSpanWork();
        return "success";
    }
}
