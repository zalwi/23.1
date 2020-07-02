package pl.zalwi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zalwi.data.Item;
import pl.zalwi.data.ItemCategory;
import pl.zalwi.data.ItemRepository;

import java.math.BigDecimal;

@Controller
public class ProductsController {

    private ItemRepository itemRepository;

    @Autowired
    public ProductsController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("categories", ItemCategory.values());
        return "home";
    }

    @GetMapping("/list")
    public String details(@RequestParam(required = false) String category, Model model) {

        if(category != null) {
            model.addAttribute("fullCategoryDescription", ItemCategory.getFullDescription(category));
            model.addAttribute("items", itemRepository.getListOfItemInCategory(category));
            model.addAttribute("totalPrice", itemRepository.totalPriceOfItemsInCategory(category));
            return "list";
        } else  {
            model.addAttribute("fullCategoryDescription", "Wszystkie produkty");
            model.addAttribute("items", itemRepository.getItemList());
            model.addAttribute("totalPrice", itemRepository.totalPriceOfAllItems());
            return "list";
        }
    }

    @GetMapping("/addForm")
    public String addForm(Model model) {
        model.addAttribute("categories", ItemCategory.values());
        return "addForm";
    }

    @PostMapping("/add")
    public String add(@RequestParam(required = false) String name,
                      @RequestParam(required = false) String category,
                      @RequestParam(required = false) BigDecimal price) {
        if(name.isEmpty()||category.isEmpty()||price == null){
            return "redirect:/err";
        }
        itemRepository.addItem(new Item(name, ItemCategory.getItemCategoryByShortDescription(category), price));
        return "redirect:/list?category="+category;
    }

    @GetMapping("/err")
    public String error(){
        return "err";
    }
}
