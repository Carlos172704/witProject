package com.carlos.witProject.controller;

import com.carlos.witProject.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//controls requests from client
@RestController
@RequestMapping("/api")
public class MessageController {

    private Producer Producer;

    @Autowired
    public MessageController(Producer Producer){
        this.Producer = Producer;
    }


    //handles sum operation, returns result value to client and sends it to kafka
    @GetMapping("/get/sum")
    public ResponseEntity<String> publishSum(@RequestParam("a") String a, @RequestParam("b") String b){
        try{
            double num1 = Double.parseDouble(a);
            double num2 = Double.parseDouble(b);
            double result = num1 + num2;
            Producer.sendMessage(String.format("%1$,.2f", result));
            return ResponseEntity.status(200).body(String.format("%1$,.2f", result));
        }catch(NumberFormatException e){
            Producer.sendMessage("The values should be numbers only!");
        }
        return ResponseEntity.status(400).body("The values should be numbers only!");

    }

    //handles subtraction operation, returns result value to client and sends it to kafka
    @GetMapping("/get/sub")
    public ResponseEntity<String> publishSub(@RequestParam("a") String a, @RequestParam("b") String b){
        try{
            double num1 = Double.parseDouble(a);
            double num2 = Double.parseDouble(b);
            double result = num1 - num2;
            Producer.sendMessage(String.format("%1$,.2f", result));
            return ResponseEntity.status(200).body(String.format("%1$,.2f", result));
        }catch(NumberFormatException e){
            Producer.sendMessage("The values should be numbers only!");
        }

        return ResponseEntity.status(400).body("The values should be numbers only!");
    }

    //handles multiplication operation, returns result value to client and sends it to kafka
    @GetMapping("/get/mult")
    public ResponseEntity<String> publishMult(@RequestParam("a") String a, @RequestParam("b") String b){
        try{
            double num1 = Double.parseDouble(a);
            double num2 = Double.parseDouble(b);
            double result = num1 * num2;
            Producer.sendMessage(String.format("%1$,.2f", result));
            return ResponseEntity.status(200).body(String.format("%1$,.2f", result));
        }catch(NumberFormatException e){
            Producer.sendMessage("The values should be numbers only!");
        }

        return ResponseEntity.status(400).body("The values should be numbers only!");
    }

    //handles division operation, returns result value to client and sends it to kafka
    @GetMapping("/get/div")
    public ResponseEntity<String> publishDiv(@RequestParam("a") String a, @RequestParam("b") String b){
        try{
            double num1 = Double.parseDouble(a);
            double num2 = Double.parseDouble(b);
            double result = num1 / num2;
            Producer.sendMessage(String.format("%1$,.2f", result));
            return ResponseEntity.status(200).body(String.format("%1$,.2f", result));
        }catch(NumberFormatException e){
            Producer.sendMessage("The values should be numbers only!");
        }

        return ResponseEntity.status(400).body("The values should be numbers only!");
    }
}
