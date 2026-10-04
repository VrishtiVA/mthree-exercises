package com.mthree.academy.c458.vrishti_va.hello_security.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Forces out application to load up and use our SecurityConfig.
 * Just by existing, Spring Security will know what to do.
 */
public class SecurityWebApplicationInitialiser extends AbstractSecurityWebApplicationInitializer {
}
