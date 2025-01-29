package com.carlos.witProject.controller;

import com.carlos.witProject.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MessageController {

    private Producer Producer;

    @Autowired
    public MessageController(Producer Producer){
        this.Producer = Producer;
    }

    @GetMapping("/get/sum")
    public ResponseEntity<String> publishSum(@RequestParam("a") String a, @RequestParam("b") String b){
        try{
            double num1 = Double.parseDouble(a);
            double num2 = Double.parseDouble(b);
            Producer.sendMessage(String.format("%1$,.2f", num1 + num2));
        }catch(NumberFormatException e){
            Producer.sendMessage("The values should be numbers only!");
        }

        return ResponseEntity.ok("Message sent successfully");
    }
    @GetMapping("/get/sub")
    public ResponseEntity<String> publishSub(@RequestParam("a") String a, @RequestParam("b") String b){
        try{
            double num1 = Double.parseDouble(a);
            double num2 = Double.parseDouble(b);
            Producer.sendMessage(String.format("%1$,.2f", num1 - num2));
        }catch(NumberFormatException e){
            Producer.sendMessage("The values should be numbers only!");
        }

        return ResponseEntity.ok("Message sent successfully");
    }


    @GetMapping("/get/mult")
    public ResponseEntity<String> publishMult(@RequestParam("a") String a, @RequestParam("b") String b){
        try{
            double num1 = Double.parseDouble(a);
            double num2 = Double.parseDouble(b);
            Producer.sendMessage(String.format("%1$,.2f", num1 * num2));
        }catch(NumberFormatException e){
            Producer.sendMessage("The values should be numbers only!");
        }

        return ResponseEntity.ok("Message sent successfully");
    }

    @GetMapping("/get/div")
    public ResponseEntity<String> publishDiv(@RequestParam("a") String a, @RequestParam("b") String b){
        try{
            double num1 = Double.parseDouble(a);
            double num2 = Double.parseDouble(b);
            Producer.sendMessage(String.format("%1$,.2f", num1 / num2));
        }catch(NumberFormatException e){
            Producer.sendMessage("The values should be numbers only!");
        }

        return ResponseEntity.ok("Message sent successfully");
    }
}
