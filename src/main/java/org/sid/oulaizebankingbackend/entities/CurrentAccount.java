package org.sid.oulaizebankingbackend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@DiscriminatorValue("CA")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CurrentAccount extends BankAccount{
    private double OverDraft;
}