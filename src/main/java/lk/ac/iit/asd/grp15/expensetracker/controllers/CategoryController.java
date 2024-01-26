package lk.ac.iit.asd.grp15.expensetracker.controllers;

import jakarta.validation.Valid;
import lk.ac.iit.asd.grp15.expensetracker.entity.Category;
import lk.ac.iit.asd.grp15.expensetracker.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Jamith Nimantha
 */
@Controller()
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping("")
    public String getCategoryPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("categoryForm", new Category());
        model.addAttribute("categories", categoryService.findAllByUser(userDetails.getUsername()));
        return "category";
    }

    @GetMapping(value = "delete/{id}")
    public String delete(@PathVariable Long id) {
        categoryService.deleteById(id);

        return "redirect:/category";
    }

    @PostMapping("")
    public String saveCategory(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("categoryForm") Category category, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        categoryService.save(category);

        return "redirect:/category";
    }
}
