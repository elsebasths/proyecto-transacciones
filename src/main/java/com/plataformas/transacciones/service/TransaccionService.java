package com.plataformas.transacciones.service;


import com.plataformas.transacciones.entity.Cuenta;
import com.plataformas.transacciones.entity.Transaccion;
import com.plataformas.transacciones.repository.CuentaRepository;
import com.plataformas.transacciones.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransaccionService {

    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private TransaccionRepository transaccionRepository;


    public Mono<Transaccion> procesarTransaccion (Mono<Transaccion> transaccionMono){
        return transaccionMono.flatMap(transaccion -> {
            Mono<Cuenta> origen = cuentaRepository.findByNumeroCuenta(transaccion.getNumeroCuentaOrigen())
                    .switchIfEmpty(Mono.error(new RuntimeException("Cuenta Origen no Encontrada")));
            Mono<Cuenta> destino = cuentaRepository.findByNumeroCuenta(transaccion.getNumeroCuentaDestino())
                    .switchIfEmpty(Mono.error(new RuntimeException("Cuenta Destino no Encontrada")));

            return Mono.zip(origen,destino).flatMap(tuple -> {
                    Cuenta cuentaOrigen = tuple.getT1();
                    Cuenta cuentaDestino = tuple.getT2();
                    double monto = transaccion.getMonto();
                    if (cuentaOrigen.getSaldo() < monto){
                        return Mono.error(new RuntimeException("Saldo Insuficiente"));
                    }
                    cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - monto);
                    cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);

                    return cuentaRepository.save(cuentaOrigen)
                            .then(cuentaRepository.save(cuentaDestino))
                            .then(transaccionRepository.save(transaccion));
            });
        });
    }

    public Flux<Transaccion> obtenerTransacciones (){
        return transaccionRepository.findAll();
    }

    public Mono<Transaccion> obtenerTransaccion (Long numTransaccion){
        return transaccionRepository.findByNumeroTransaccion(numTransaccion);
    }

}
