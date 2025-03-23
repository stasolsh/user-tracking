package com.user.tracking.imperative;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan("com.user.tracking")
@Profile("test")
public class ApplicationTestConfiguration {

}
