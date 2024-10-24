package com.generation.mondolibri.controller;

import com.generation.mondolibri.exceptions.checked.ProductAlreadyExists;
import com.generation.mondolibri.exceptions.checked.ProductNotFoundException;
import com.generation.mondolibri.model.entity.Author;
import com.generation.mondolibri.model.entity.Genre;
import com.generation.mondolibri.model.entity.Product;
import com.generation.mondolibri.service.implementation.AuthorService;
import com.generation.mondolibri.service.implementation.GenreService;
import com.generation.mondolibri.service.implementation.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/catalogue")
public class ProductController {

    private final ProductService productService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Autowired
    public ProductController(ProductService productService, AuthorService authorService, GenreService genreService) {
        this.productService = productService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    // Display the product catalogue with optional search filters
    @GetMapping
    public String getCatalogue(Model model,
                               @RequestParam(value = "keyword", required = false) String keyword,
                               @RequestParam(value = "minPrice", required = false) Double minPrice,
                               @RequestParam(value = "maxPrice", required = false) Double maxPrice) {
        List<Product> products = productService.searchProducts(keyword, minPrice, maxPrice);
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/admin/catalogue")
    public String getAdminCatalogue(Model model) {
        List<Product> allBooks = productService.findAll();
        model.addAttribute("products", allBooks);
        return "privata/admin/admincatalogo";
    }

    // Add a new product to the catalogue
    @PostMapping("admin/add")
    public String addProduct(@RequestParam(value = "author") String authorString,
                             @RequestParam(value = "genre") String genreString,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "price") Double price,
                             @RequestParam(value = "quantity") Integer quantity,
                             @RequestParam(value = "description") String description,
                             @RequestParam(value = "language") String language,
                             @RequestParam(value = "imageURL") String imageURL)
            throws ProductAlreadyExists {
        Author author = authorService.findOrCreateAuthorByName(authorString);
        Genre genre = genreService.findOrCreateGenreByName(genreString);
        Product product = new Product(name, author, genre, price, quantity, description, language, imageURL);
        Product productBuilt = productService.createProduct(product);
        return "redirect:/catalogue/admin/catalogue";
    }

    // Remove a product from the catalogue
    @PostMapping("admin/remove")
    public String removeProduct(@RequestParam("id") Integer productId)
            throws ProductNotFoundException {
        Product product = productService.retrieveById(productId);
        productService.deleteProduct(product.getId());
        return "redirect:/catalogue/admin/catalogue";
    }
}
