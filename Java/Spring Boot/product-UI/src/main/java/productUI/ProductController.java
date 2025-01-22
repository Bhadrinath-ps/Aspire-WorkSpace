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
        model.addAttribute("products", products);  // Add products to the model
        return "product-list";  // Thymeleaf will look for 'product-list.html'
    }

    // Serve the product details page
    @GetMapping("/{id}")
    public String getProductById(@PathVariable int id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());  // Add product to the model
            return "product-details";  // Thymeleaf will look for 'product-details.html'
        }
        return "404";  // Return a 404 page if product not found
    }

    // Add a new product (GET method for displaying the form)
    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());  // Create an empty product object
        return "add-product";  // Thymeleaf will look for 'add-product.html'
    }

    // Add a new product (POST method for handling form submission)
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);  // Save the product
        return "redirect:/product";  // Redirect to product list after adding the product
    }

    // Update an existing product (GET method for displaying the form)
    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable int id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "edit-product";  // Thymeleaf will look for 'edit-product.html'
        }
        return "404";  // Return 404 if the product is not found
    }

    // Update an existing product (POST method for handling form submission)
    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable int id, @ModelAttribute Product product) {
        productService.updateProduct(id, product);
        return "redirect:/product";  // Redirect to product list after updating
    }

    // Delete a product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "redirect:/product";  // Redirect to product list after deleting the product
    }
}
