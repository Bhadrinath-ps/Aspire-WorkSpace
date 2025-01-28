package productUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Serve the page that lists all products
    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);  
        return "product-list";  
    }

    // Serve the product details page
    @GetMapping("/{id}")
    public String getProductById(@PathVariable int id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get()); 
            return "product-details"; 
        }
        return "404";  
    }

    // Add a new product (GET method for displaying the form)
    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());  
        return "add-product";  
    }

    // Add a new product (POST method for handling form submission)
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);  
        return "redirect:/product"; 
    }

    // Update an existing product (GET method for displaying the form)
    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable int id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "edit-product"; 
        }
        return "404";  
    }

    // Update an existing product (POST method for handling form submission)
    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable int id, @ModelAttribute Product product) {
        productService.updateProduct(id, product);
        return "redirect:/product"; 
    }

    // Delete a product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "redirect:/product"; 
    }
}
