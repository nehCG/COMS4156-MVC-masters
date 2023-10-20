package com.mvcmasters.ems;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the EMS (Entity Management Service).
 */
@SpringBootApplication
@MapperScan("com.mvcmasters.ems.repository")
public class EmsApplication {

    /**
     * The main entry point for the EMS
     * (Entity Management Service).
     *
     * @param args The command-line arguments (if any).
     */
    public static void main(final String[] args) {
        SpringApplication.run(EmsApplication.class, args);
    }
}
