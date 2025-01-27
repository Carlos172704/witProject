package com.carlos.witProject.controller;

import com.carlos.witProject.Rest;
import org.apache.kafka.common.protocol.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MessageController {

    private Rest Rest;

    @Autowired
    public MessageController(Rest Rest){
        this.Rest = Rest;
    }

    @GetMapping("/get/sum")
    public ResponseEntity<String> publish(@RequestParam("a") String a, @RequestParam("b")String b){
        Rest.sendMessage(a);
        return ResponseEntity.ok("Message sent successfully");
    }
}
