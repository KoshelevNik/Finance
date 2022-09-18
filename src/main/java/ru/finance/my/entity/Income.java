package ru.finance.my.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Income {

  private Long id;

  private Long sum;

  private Long accountId;

  private LocalDate date;

  private IncomeCategory incomeCategory;
}
