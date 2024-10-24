package com.generation.mondolibri.controller;

import com.generation.mondolibri.exceptions.checked.NotEnoughStockException;
import com.generation.mondolibri.exceptions.checked.ProductNotFoundException;
import com.generation.mondolibri.exceptions.checked.UserNotFoundException;
import com.generation.mondolibri.model.entity.Product;
import com.generation.mondolibri.model.entity.Purchase;
import com.generation.mondolibri.model.entity.User;
import com.generation.mondolibri.service.implementation.ProductService;
import com.generation.mondolibri.service.implementation.PurchaseService;
import com.generation.mondolibri.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/order")
public class PurchaseController {
    private final PurchaseService purchaseService;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService, UserService userService, ProductService productService) {
        this.purchaseService = purchaseService;
        this.userService = userService;
        this.productService = productService;
    }


    @PostMapping
    public String makePurchase(Model model,
                             @RequestParam("id") Integer productId,
                             @SessionAttribute("username") String username)
            throws ProductNotFoundException, UserNotFoundException, NotEnoughStockException {
            User user = userService.findByUsername(username);
            Product product = productService.retrieveById(productId);
            Purchase purchase = purchaseService.createPurchase(user,product);
            model.addAttribute("product", purchase.getProduct().getName());
            model.addAttribute("user", user);
            return "confermaAcquisto";
    }
}

