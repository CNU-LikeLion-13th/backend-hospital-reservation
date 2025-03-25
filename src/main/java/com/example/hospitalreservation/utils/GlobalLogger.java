package com.example.hospitalreservation.utils;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GlobalLogger {
    public static void log(String message){
        log.info("{}",message);
    }
}
