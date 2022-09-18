package ru.finance.my.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Transfer {

  private Long id;

  private Long fromAccountId;

  private Long toAccountId;

  private Long sum;

  private LocalDate date;
}
