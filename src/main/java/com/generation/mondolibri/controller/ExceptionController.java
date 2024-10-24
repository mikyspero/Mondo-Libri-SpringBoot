package com.generation.mondolibri.controller;

import com.generation.mondolibri.exceptions.checked.*;
import jakarta.persistence.PersistenceException;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.NoSuchAlgorithmException;

@ControllerAdvice
public class ExceptionController {
    //A lot of these are extremely redundant but since they were easy to generate they were built in case of future expansion

    @ExceptionHandler(AuthenticationException.class)
    public String handleAuthenticationException(AuthenticationException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(NotEnoughStockException.class)
    public String handleNotEnoughStockException(NotEnoughStockException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(ProductAlreadyExists.class)
    public String handleProductAlreadyExists(ProductAlreadyExists ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(ProductAlreadyInCartException.class)
    public String handleProductAlreadyInCartException(ProductAlreadyInCartException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public String handleProductNotFoundException(ProductNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public String handleRoleNotFoundException(RoleNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleUserAlreadyExistsException(UserAlreadyExistsException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(UserNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(NoSuchAlgorithmException.class)
    public String handleNoSuchAlgorithmException(NoSuchAlgorithmException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(DataAccessException.class)
    public String handleDataAccessException( Model model) {
        model.addAttribute("errorMessage", "A database error occurred. Please try again later.");
        return "error";
    }

    @ExceptionHandler(PersistenceException.class)
    public String handlePersistenceException( Model model) {
        model.addAttribute("errorMessage", "A persistence error occurred. Please contact support.");
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(RuntimeException ex, Model model){
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }
}
