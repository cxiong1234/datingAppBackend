package org.example.datingapp.Application;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ApplicationRESTController {

    @RequestMapping("/application/status")
    public String getApplicationStatus() {
        return "Application is running smoothly";
    }
}
