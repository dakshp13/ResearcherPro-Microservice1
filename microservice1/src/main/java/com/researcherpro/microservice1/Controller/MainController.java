package com.researcherpro.microservice1.Controller;

import com.researcherpro.microservice1.Model.LoginCreds;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class MainController {

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> loginAuth(@RequestBody LoginCreds loginCreds){
        if(loginCreds.getUsername().equals("guest123") &&
                loginCreds.getPassword().equals("guest123")){
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed Login");
        }
    }


    @GetMapping("/analytics")
    public String analyticsPage(){
        return "analytics";
    }

}
