package br.com.fabex.app.web.controller;

import br.com.fabex.app.web.model.Product;
import br.com.fabex.app.web.services.ProductsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/products")
    public String getProducts(Model page) {
        List<Product> products = this.productsService.findAll();
        page.addAttribute("products", products);
        return "products.html";
    }

    @PostMapping("/products")
    public String addProduct(Product product, Model page) {
        this.productsService.addProduct(product);
        List<Product> products = this.productsService.findAll();
        page.addAttribute("products", products);
        return "products.html";
    }
}
