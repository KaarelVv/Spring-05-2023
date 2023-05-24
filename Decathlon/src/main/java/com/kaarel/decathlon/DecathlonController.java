package com.kaarel.decathlon;

import org.springframework.web.bind.annotation.*;

@RestController
    public class DecathlonController {
        private Fields[] fields = Fields.values();
        private int currentIndex = 0;

        Athlete athlete1 = new Athlete("HUNDRED_METERS",23);


        @GetMapping("/print-field")
        public String printField() {
            if (currentIndex >= fields.length) {
                return "End of fields";
            }else {
                Fields field = fields[currentIndex];
                currentIndex++;

                return field.toString();
            }
        }
    @GetMapping("/add-result")
    public String addResult(@RequestParam("field") String field, @RequestParam("result") double result) {
        Fields targetField = null;

        // Find the field event based on the provided field name
        for (Fields f : Fields.values()) {
            if (f.toString().equalsIgnoreCase(field)) {
                targetField = f;
                break;
            }
        }
        if (targetField != null) {
            Calculation calc = new Calculation(athlete1, targetField);
            calc.addAthleteResult(athlete1, result);
        } else {
            throw new IllegalArgumentException("Invalid field event specified.");
        }

        return "added";
    }

    @GetMapping("/result")
    public int printResult() {
        Calculation calc = new Calculation(athlete1, Fields.HUNDRED_METERS);
        return calc.getValue();
    }
}

