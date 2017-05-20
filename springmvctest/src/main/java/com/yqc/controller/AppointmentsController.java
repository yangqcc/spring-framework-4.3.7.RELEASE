package com.yqc.controller;

import com.yqc.entity.User;
import com.yqc.service.AppointmentBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangqc on 2017/3/27.
 */
@RestController
@RequestMapping
public class AppointmentsController {

    private final AppointmentBook appointmentBook;

    @Autowired
    public AppointmentsController(AppointmentBook appointmentBook) {
        this.appointmentBook = appointmentBook;
    }

    @RequestMapping("/index")
    public String index() {
        return "demo";
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> manyUsersXml() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 3);
        calendar.set(Calendar.DAY_OF_MONTH, 10);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 40);
        calendar.set(Calendar.SECOND, 10);
        List<User> userList = new ArrayList<>(1000);
        for (int i = 0; i < 100000; i++) {
            userList.add(new User("yangqc" + i, i, "dog" + i));
        }
        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/json;charset=utf-8");
        System.out.println("yes，fuck me!");
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES))
                .lastModified(new Date().getTime())
                .headers(headers)
                .body(userList);
    }

    @GetMapping("/users_no_cache")
    public ResponseEntity<List<User>> manyUsersXmlNoCache() {
        List<User> userList = new ArrayList<>(1000);
        for (int i = 0; i < 100000; i++) {
            userList.add(new User("yangqc" + i, i, "dog" + i));
        }
        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/json;charset=utf-8");
        System.out.println("yes,fuck me ***2***!");
        return ResponseEntity.ok()
                .headers(headers)
                .cacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES))
                .lastModified(new Date().getTime())
                .body(userList);
    }

}
