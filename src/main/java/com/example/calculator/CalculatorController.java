package com.example.calculator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CalculatorController {

    @GetMapping("/calculate")
    public ResponseEntity<Map<String, Object>> calculate(
            @RequestParam double num1,
            @RequestParam double num2,
            @RequestParam String operator) {

        Map<String, Object> response = new HashMap<>();
        try {
            double result;
            switch (operator) {
                case "add":
                    result = num1 + num2;
                    break;
                case "subtract":
                    result = num1 - num2;
                    break;
                case "multiply":
                    result = num1 * num2;
                    break;
                case "divide":
                    if (num2 == 0) {
                        response.put("error", "Cannot divide by zero");
                        return ResponseEntity.badRequest().body(response);
                    }
                    result = num1 / num2;
                    break;
                default:
                    response.put("error", "Invalid operator");
                    return ResponseEntity.badRequest().body(response);
            }
            response.put("result", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Invalid input: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
