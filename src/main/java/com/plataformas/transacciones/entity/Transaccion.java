package com.plataformas.transacciones.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transacciones")
public class Transaccion {
    @Id
    private String id;
    private Long numeroTransaccion;
    private String numeroCuentaOrigen;
    private String numeroCuentaDestino;
    private Double monto;
    private LocalDateTime fecha;
}
