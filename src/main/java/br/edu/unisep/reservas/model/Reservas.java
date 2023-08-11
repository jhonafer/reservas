package br.edu.unisep.reservas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.mapping.Set;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Table(name = "reservas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Reservas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @Column(name = "dataReserva", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date dataReserva;

    @Column(name = "dataDevolucao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date dataDevolucao;

    @ManyToOne
    @JoinColumn(name = "equipamento")
    private Equipamento equipamento;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @Column(name = "criado_em", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date criadoEm;

    @Column(name = "criado_por", nullable = false)
    @CreatedBy
    private String criadoPor;
    @Column(name = "alterado_em")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date alteradoEm;
    @Column(name = "alterado_por")
    @LastModifiedBy
    private String alteradoPor;
}