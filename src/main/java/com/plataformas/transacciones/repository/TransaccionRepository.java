package com.plataformas.transacciones.repository;

import com.plataformas.transacciones.entity.Transaccion;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface TransaccionRepository extends ReactiveMongoRepository<Transaccion, String> {

    Mono<Transaccion> findByNumeroTransaccion(Long numTransaccion);

}
