package controller;

import model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.ProductService;

import java.util.List;
@Controller
@RequestMapping("/product-ex")
public class ProductController {
     ProductService productService = new ProductService();
    @GetMapping("")

    public String display(Model model){
        List<Product> productList = productService.findAll();
        model.addAttribute("list",productList);
        return "/index";
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/create");
        return modelAndView;
    }
    @PostMapping("/createPost")
    public String save(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("price")int price,@RequestParam("description")String description ){
        Product product = new Product(id,name,price,description)  ;
        productService.save(product);
        return "redirect:/product-ex";
    }

    @GetMapping ("/edit")
    public ModelAndView showEditForm(@RequestParam("id") int id){
        ModelAndView modelAndView = new ModelAndView("/edit");
        Product product = productService.findById(id);
        modelAndView.addObject("edit",product);
        return modelAndView;
    }

    @PostMapping("/edit")
    public String edit(@RequestParam("id") int id,@RequestParam("name") String name,@RequestParam("price") int price,@RequestParam("description") String description){
        Product product = new Product(id, name, price,description);
        productService.update(id,product);
        return "redirect:/product-ex";
    }

    @GetMapping("/delete")
    public ModelAndView showDeleteForm(@RequestParam("id") int id){
        ModelAndView modelAndView = new ModelAndView("/delete");
        Product product = productService.findById(id);
        modelAndView.addObject("delete",product);
        return modelAndView;
    }
    @PostMapping("/delete")
    public String  delete(@RequestParam("id")int id){
        productService.remove(id);
        return "redirect:/product-ex";
    }

    @GetMapping("/view")
    public String view(@RequestParam("id") int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/view";
    }


    @GetMapping ("/search")
    public ModelAndView showSearch(@RequestParam("name") String name){
        List<Product> products= (List<Product>) productService.findByName(name);
        ModelAndView modelAndView = new ModelAndView("/search");
        modelAndView.addObject("list",products);
        return modelAndView;
    }
}
