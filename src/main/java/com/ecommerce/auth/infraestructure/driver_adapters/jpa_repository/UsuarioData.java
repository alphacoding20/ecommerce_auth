package com.ecommerce.auth.infraestructure.driver_adapters.jpa_repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
@Data
public class UsuarioData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUsuario;

    private String nombre;
    @Column(length = 50, nullable = false)
    private String correo;
    private String clave;
    private String rol;
    private Integer edad;
    private String numeroTelefonico;

}

/*ESTA CLASE ES LA REPRESENTACIÓN DEL OBJETO USUARIO PERO PARA LA BASE DE DATOS, ACÁ SE CREA COMO UNA ENTIDAD NO COMO OBJETO*/