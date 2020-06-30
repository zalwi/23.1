package pl.zalwi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zalwi.data.Item;
import pl.zalwi.data.ItemCategory;
import pl.zalwi.data.ItemRepository;

import java.math.BigDecimal;

@Controller
public class ProductsController {

    ItemRepository itemRepository;

    public ProductsController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("categories", ItemCategory.values());
        return "home";
    }

    @GetMapping("/list")
    public String details(@RequestParam String category, Model model) {

        if(category != null) {
            model.addAttribute("fullCategoryDescription", ItemCategory.getFullDescription(category));
            model.addAttribute("items", itemRepository.getListOfItemInCategory(category));
            model.addAttribute("totalPrice", itemRepository.totalPriceOfItemsInCategory(category));
            return "list";
        } else  {
            return "redirect:/";
        }
    }

    @GetMapping("/addForm")
    public String addForm(Model model) {
        model.addAttribute("categories", ItemCategory.values());
        return "addForm";
    }

    @PostMapping("/add")
    public String add(@RequestParam String name,  @RequestParam String category, @RequestParam BigDecimal price) {
        itemRepository.addItem(new Item(name, ItemCategory.getItemCategoryByShortDescription(category), price));
        return "redirect:/list?category="+category;
    }
}
