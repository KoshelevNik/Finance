package ru.finance.my.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.finance.my.controller.path.Path;
import ru.finance.my.entity.Income;
import ru.finance.my.service.IncomeService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(Path.INCOME_PATH)
public class IncomeController {

  @Autowired
  private IncomeService incomeService;

  @GetMapping
  public List<Income> getAll(
      @RequestParam("before-date") String beforeDate,
      @RequestParam("after-date") String afterDate,
      @RequestParam("user-id") Long userId
  ) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate before = LocalDate.parse(beforeDate, formatter);
    LocalDate after = LocalDate.parse(afterDate, formatter);
    return incomeService.getAll(before, after, userId);
  }

  @PostMapping
  public ResponseEntity<Income> createNew(@RequestBody Income income) {
    incomeService.createNew(income);
    return ResponseEntity.status(HttpStatus.CREATED).body(income);
  }

  @DeleteMapping("/{incomeId}")
  public void deleteById(@PathVariable Long incomeId) {
    incomeService.deleteById(incomeId);
  }

  @PutMapping("/{incomeId}")
  public Income deleteById(@PathVariable Long incomeId, @RequestBody Income income) {
    income.setId(incomeId);
    incomeService.update(income);
    return income;
  }

  @GetMapping("/{incomeId}")
  public Income getById(@PathVariable Long incomeId) {
    return incomeService.getById(incomeId);
  }
}
