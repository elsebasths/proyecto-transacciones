package com.plataformas.transacciones.service;

import com.plataformas.transacciones.entity.Cuenta;
import com.plataformas.transacciones.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class CuentaService {
    @Autowired
    private CuentaRepository cuentaRepository;

    public Mono<Cuenta> guardarCuenta(Mono<Cuenta> cuenta){
        return cuenta.flatMap(cuenta1 -> cuentaRepository.findByNumeroCuenta(cuenta1.getNumeroCuenta())
                .flatMap(existe -> Mono.<Cuenta>error(new RuntimeException("No se pudo crear")))
                .switchIfEmpty(cuentaRepository.save(cuenta1)));
    }

    public Mono<Cuenta> obtenerCuenta(String numeroCuenta){
        return cuentaRepository.findByNumeroCuenta(numeroCuenta);
    }

    public Flux<Cuenta> obtenerCuentas(){
        return cuentaRepository.findAll();
    }

}
