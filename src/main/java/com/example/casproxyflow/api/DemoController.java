package com.example.casproxyflow.api;

import com.kakawait.spring.security.cas.client.ticket.ProxyTicketProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController(value = "/api")
public class DemoController {
    private final ProxyTicketProvider proxyTicketProvider;

    public DemoController(ProxyTicketProvider proxyTicketProvider) {
        this.proxyTicketProvider = proxyTicketProvider;
    }

    @GetMapping(path = "proxy-ticket", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getProxyTicket() {
        final String proxyTicket = proxyTicketProvider.getProxyTicket("http://example.com/app");
        log.debug("Proxy ticket: {}", proxyTicket);
        return proxyTicket;
    }
}
