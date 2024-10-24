package com.generation.mondolibri.controller;

import com.generation.mondolibri.exceptions.checked.ProductAlreadyInCartException;
import com.generation.mondolibri.exceptions.checked.ProductNotFoundException;
import com.generation.mondolibri.exceptions.checked.UserNotFoundException;
import com.generation.mondolibri.model.entity.Cart;
import com.generation.mondolibri.model.entity.User;
import com.generation.mondolibri.service.implementation.CartService;
import com.generation.mondolibri.service.implementation.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/cart")
public class CartController {
    private final UserService userService;
    private final CartService cartService;

    @Autowired
    public CartController(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping
    @Transactional
    public String cart(@SessionAttribute("username") String username, Model model)
            throws UserNotFoundException {
        try {
            User user = userService.findByUsername(username);
            Integer userid = user.getId();
            Cart toBePassed = userService.GetRelatedCart(userid);
            model.addAttribute("cart", toBePassed);
            return "privata/cart";
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return "redirect:/error";
        }
    }

    @PostMapping("/add")
    public String addToCart(Model model,
                          @RequestParam("id") Integer productId,
                          @SessionAttribute("username") String username)
            throws UserNotFoundException, ProductAlreadyInCartException, ProductNotFoundException {

            User user = userService.findByUsername(username);
            Integer userid = user.getId();
            cartService.addProductToCart(userid, productId);
            Cart updatedCart = userService.GetRelatedCart(userid);
            model.addAttribute("cart", updatedCart);
            return "privata/cart";
    }

    @PostMapping("/delete")
    public String removeFromCart(Model model,
                               @RequestParam("id") Integer productId,
                               @SessionAttribute("username") String username)
            throws UserNotFoundException, ProductNotFoundException {
            User user = userService.findByUsername(username);
            Integer userId = user.getId();
            cartService.removeProductFromCart(userId, productId);
            Cart updatedCart = userService.GetRelatedCart(userId);
            model.addAttribute("cart", updatedCart);
            return "privata/cart";
    }
}

