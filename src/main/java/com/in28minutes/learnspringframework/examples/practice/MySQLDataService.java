package com.in28minutes.learnspringframework.examples.practice;

import org.springframework.stereotype.Component;

@Component
public class MySQLDataService {
    public int[] retrieveData() {
        return new int[] { 1, 2, 3, 4, 5 };
    }
}
