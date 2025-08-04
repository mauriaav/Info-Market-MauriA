package com.informatorio.info_market.service.factura.impl;

import com.informatorio.info_market.domain.Carrito;
import com.informatorio.info_market.domain.Factura;
import com.informatorio.info_market.repository.factura.FacturaRepository;
import com.informatorio.info_market.service.factura.FacturaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class FacturaServiceImpl implements FacturaService {

    private FacturaRepository facturaRepository;

    @Override
    public void crearFactura(Carrito carrito, Factura factura) {
        factura.setCarrito(carrito);
        factura.setFechaDeEmision(LocalDate.now());
        facturaRepository.save(factura);
    }
}
