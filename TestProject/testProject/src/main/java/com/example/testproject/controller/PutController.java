package com.example.testproject.controller;

import com.example.testproject.dto.MemberDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {

    // http://localhost:8080/api/v1/put-api/default
    @PutMapping(value = "/default")
    public String putMethod() {
        return "Hello World!";
        // Hello World!
    }

    // http://localhost:8080/api/v1/put-api/member
    /*
        [body]
        {
            "name":"flature",
            "email":"thinkground.flature@gmail.com",
            "organization":"thinkground"
        }
     */
    @PutMapping(value = "/member")
    public String putMember(@RequestBody Map<String, Object> putData) {
        StringBuilder sb = new StringBuilder();

        putData.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

    /*
    param.forEach((key, value) -> sb.append(key).append(" : ").append(value).append("\n"));
     */

        return sb.toString();
        /*
            name : flature
            email : thinkground.flature@gmail.com
            organization : thinkground
         */
    }

    // http://localhost:8080/api/v1/put-api/member2
    @PutMapping(value = "/member1")
    public String putMemberDto1(@RequestBody MemberDTO memberDTO) {
        return memberDTO.toString();
        /*
            MemberDTO{name='flature', email='thinkground.flature@gmail.com', organization='thinkground'}
         */
    }

    // http://localhost:8080/api/v1/put-api/member2
    @PutMapping(value = "/member2")
    public MemberDTO putMemberDto2(@RequestBody MemberDTO memberDTO) {
        return memberDTO;
        /*
        {
            "name": "flature",
            "email": "thinkground.flature@gmail.com",
            "organization": "thinkground"
        }
         */
    }

    // http://localhost:8080/api/v1/put-api/member2
    @PutMapping(value = "/member3")
    public ResponseEntity<MemberDTO> putMemberDto3(@RequestBody MemberDTO memberDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberDTO);
        /*
        {
            "name": "flature",
            "email": "thinkground.flature@gmail.com",
            "organization": "thinkground"
        }
         */
    }
}
