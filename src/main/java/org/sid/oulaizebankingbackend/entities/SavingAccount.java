package org.sid.oulaizebankingbackend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("SA")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SavingAccount  extends BankAccount{
    private double interestRate;

}