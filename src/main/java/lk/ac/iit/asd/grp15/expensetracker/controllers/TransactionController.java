package lk.ac.iit.asd.grp15.expensetracker.controllers;


import lk.ac.iit.asd.grp15.expensetracker.enums.TransactionType;
import lk.ac.iit.asd.grp15.expensetracker.factories.FilterFactory;
import lk.ac.iit.asd.grp15.expensetracker.entity.Transaction;
import lk.ac.iit.asd.grp15.expensetracker.services.imp.TransactionServiceImp;
import lk.ac.iit.asd.grp15.expensetracker.services.imp.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionServiceImp transactionService;

    private final UserServiceImp userService;


    @GetMapping({"/", "/transactions"})
    public String transactions(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(required = false, name = "filter") String filter, Model model) {
        model.addAttribute("incomeForm", new Transaction());
        model.addAttribute("outcomeForm", new Transaction());

        List<Transaction> transactions = transactionService.findByUsername(userDetails.getUsername());

        if (filter != null) {
            transactions = FilterFactory.getInstance().getFilter(filter).meets(transactions);
        }

        model.addAttribute("transactions", transactions);
        model.addAttribute("sum", transactionService.sum(transactions));

        return "transactions";
    }

    @PostMapping("/income")
    public String income(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("incomeForm") Transaction incomeForm, BindingResult bindingResult) {
//        incomeForm.setUser(userService.findByUsername(userDetails.getUsername()));
        incomeForm.setType(TransactionType.INCOME);
        incomeForm.setTransactionDate(new Date());
        if (incomeForm.getId() == -1)
            incomeForm.setId(null);
        transactionService.save(incomeForm);

        return "redirect:/transactions";
    }

    @PostMapping("/outcome")
    public String outcome(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute("outcomeForm") Transaction outcomeForm, BindingResult bindingResult) {
//        outcomeForm.setUser(userService.findByUsername(userDetails.getUsername()));
        outcomeForm.setType(TransactionType.EXPENSE);
        outcomeForm.setTransactionDate(new Date());
        if (outcomeForm.getId() == -1)
            outcomeForm.setId(null);
        transactionService.save(outcomeForm);

        return "redirect:/transactions";
    }

    @GetMapping(value = "delete/{id}")
    public String delete(@PathVariable Long id) {
        transactionService.deleteById(id);

        return "redirect:/transactions";
    }

    @GetMapping("/search")
    public String search(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(name = "query") String query, Model model) {
        List<Transaction> transactions = transactionService.findByDescriptionContaining(userDetails.getUsername(), query);
        model.addAttribute("transactions", transactions);
        model.addAttribute("sum", transactionService.sum(transactions));

        return "transactions";
    }
}
