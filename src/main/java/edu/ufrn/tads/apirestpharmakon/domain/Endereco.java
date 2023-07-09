package edu.ufrn.tads.apirestpharmakon.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Endereco extends AbstractEntity {
    String cidade;
    String rua;

    @OneToOne(mappedBy="endereco")
    private Pessoa pessoa;
}
