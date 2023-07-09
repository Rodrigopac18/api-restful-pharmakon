package edu.ufrn.tads.apirestpharmakon.domain;


import edu.ufrn.tads.apirestpharmakon.controller.PessoaController;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@SQLDelete(sql = "UPDATE pessoa SET deleted_at = CURRENT_TIMESTAMP WHERE id=?")
@Where(clause = "deleted_at is null")
@Table(name = "pessoa")
public class Pessoa extends AbstractEntity{
    String nome;
    Integer idade;
    String password;
    Boolean admin = false;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "endereco_id")
    Endereco endereco;



    @Data
    public static class DtoRequest{
        @NotBlank(message = "Usuário com nome em branco")
        String nome;
        @Min(value = 18, message = "Usuário com idade insuficiente")
        Integer idade;
        Endereco endereco;

        public static Pessoa convertToEntity(DtoRequest dto, ModelMapper mapper){
            return mapper.map(dto, Pessoa.class);
        }
    }

    @Data
    public static class DtoResponse extends RepresentationModel<DtoResponse> {
        String nome;
        Integer idade;
        Endereco endereco;

        public static DtoResponse convertToDto(Pessoa p, ModelMapper mapper){

            return mapper.map(p, DtoResponse.class);
        }
    }

}
