package com.plataformas.transacciones.repository;

import com.plataformas.transacciones.entity.Cuenta;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CuentaRepository extends ReactiveMongoRepository<Cuenta, String>{

    Mono<Cuenta> findByNumeroCuenta(String numeroCuenta);
}
