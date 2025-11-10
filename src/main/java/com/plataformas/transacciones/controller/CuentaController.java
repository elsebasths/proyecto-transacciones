package com.plataformas.transacciones.controller;


import com.plataformas.transacciones.entity.Cuenta;
import com.plataformas.transacciones.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("cuenta")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    public Mono<Cuenta> guardarCuenta (@RequestBody Mono<Cuenta> cuenta){
        return cuentaService.guardarCuenta(cuenta);
    }

    @GetMapping
    public Flux<Cuenta> obtenerCuentas (){
        return cuentaService.obtenerCuentas();
    }

    @GetMapping("/{numeroCuenta}")
    public Mono<Cuenta> obtenerCuenta (@PathVariable String numeroCuenta){
        return cuentaService.obtenerCuenta(numeroCuenta);
    }


}
