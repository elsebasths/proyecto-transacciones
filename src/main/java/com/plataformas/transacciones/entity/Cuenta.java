package com.plataformas.transacciones.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cuentas")
public class Cuenta {
    @Id
    private String id;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private Double saldo;
}
