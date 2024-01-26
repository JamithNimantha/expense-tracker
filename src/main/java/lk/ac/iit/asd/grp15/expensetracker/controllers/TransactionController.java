package lk.ac.iit.asd.grp15.expensetracker.controllers;


import lk.ac.iit.asd.grp15.expensetracker.enums.TransactionType;
import lk.ac.iit.asd.grp15.expensetracker.factories.FilterFactory;
import lk.ac.iit.asd.grp15.expensetracker.entity.Transaction;
import lk.ac.iit.asd.grp15.expensetracker.services.ICategoryService;
import lk.ac.iit.asd.grp15.expensetracker.services.ITransactionService;
import lk.ac.iit.asd.grp15.expensetracker.services.IUserService;
import lk.ac.iit.asd.grp15.expensetracker.services.imp.TransactionServiceImp;
import lk.ac.iit.asd.grp15.expensetracker.services.imp.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionService transactionService;

    private final ICategoryService categoryService;


    @GetMapping({"/", "/transactions"})
    public String transactions(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(required = false, name = "filter") String filter, Model model) {
        model.addAttribute("incomeForm", new Transaction());
        model.addAttribute("outcomeForm", new Transaction());
        model.addAttribute("types", TransactionType.values());
        model.addAttribute("categories", categoryService.findAllByUser(userDetails.getUsername()));
        model.addAttribute("transactionDate", LocalDate.now());

        List<Transaction> transactions = transactionService.findByUsername(userDetails.getUsername());

        if (filter != null) {
            transactions = FilterFactory.getInstance().getFilter(filter).meets(transactions);
        }

        model.addAttribute("transactions", transactions);
        model.addAttribute("sum", transactionService.sum(transactions));

        return "transactions";
    }

    @PostMapping("/transactions/income")
    public String income(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("incomeForm") Transaction incomeForm, BindingResult bindingResult) {
        incomeForm.setType(TransactionType.INCOME);
        transactionService.save(incomeForm);

        return "redirect:/transactions";
    }


    @PostMapping("/transactions/outcome")
    public String outcome(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("outcomeForm") Transaction outcomeForm, BindingResult bindingResult) {
        outcomeForm.setType(TransactionType.EXPENSE);
        transactionService.save(outcomeForm);

        return "redirect:/transactions";
    }

    @GetMapping(value = "/transactions/delete/{id}")
    public String delete(@PathVariable Long id) {
        transactionService.deleteById(id);

        return "redirect:/transactions";
    }

    @GetMapping("/transactions/search")
    public String search(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(name = "query") String query, Model model) {
        List<Transaction> transactions = transactionService.findByDescriptionContaining(userDetails.getUsername(), query);
        model.addAttribute("transactions", transactions);
        model.addAttribute("sum", transactionService.sum(transactions));
        model.addAttribute("types", TransactionType.values());
        model.addAttribute("categories", categoryService.findAllByUser(userDetails.getUsername()));
        model.addAttribute("transactionDate", LocalDate.now());

        return "transactions";
    }
}
