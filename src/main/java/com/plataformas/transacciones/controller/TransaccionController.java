package com.plataformas.transacciones.controller;


import com.plataformas.transacciones.entity.Transaccion;
import com.plataformas.transacciones.service.CuentaService;
import com.plataformas.transacciones.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("transacciones")
public class TransaccionController {

    @Autowired
    private CuentaService cuentaService;
    @Autowired
    private TransaccionService transaccionService;

   @GetMapping
   public Flux<Transaccion> obtenerTransacciones (){
       return transaccionService.obtenerTransacciones();
   }

   @GetMapping("/{numeroTransaccion}")
    public Mono<Transaccion> obtenerTransaccion (@PathVariable Long numeroTransaccion){
       return transaccionService.obtenerTransaccion(numeroTransaccion);
   }

   @PostMapping
    public Mono<Transaccion> crearTransaccion (@RequestBody Mono<Transaccion> transaccionMono){
       return transaccionService.procesarTransaccion(transaccionMono);
   }

   @PostMapping("archivo")
    public Flux<Transaccion> crearTransacciones (@RequestBody Flux<Transaccion> transaccionFlux){
       return transaccionFlux.flatMap(transaccion -> transaccionService.procesarTransaccion(Mono.just(transaccion)).
               onErrorResume(e -> Mono.empty()));
   }

}
