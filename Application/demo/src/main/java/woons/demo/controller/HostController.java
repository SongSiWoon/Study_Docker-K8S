package woons.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.HashMap;


@RestController
public class HostController {
    public HashMap gethost(){
        URI url = UriComponentsBuilder
                .fromUriString("http://localhost:8081")
                .path("/host")
                .build().toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<HashMap> responseEntity = restTemplate.getForEntity(url, HashMap.class);
        return responseEntity.getBody();
    }

    @GetMapping(value = "/host")
    public HashMap<String, Object> printhost(){
        HashMap response = gethost();
        HashMap<String, Object> map = new HashMap<>();
        try {
            String hostname = InetAddress.getLocalHost().getHostName();
            String hostadd = InetAddress.getLocalHost().getHostAddress();
            map.put("hostname", hostname);
            map.put("hostaddress", hostadd);
            map.putAll(response);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return map;
    }
}
