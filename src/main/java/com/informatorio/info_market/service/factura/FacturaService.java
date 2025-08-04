package com.informatorio.info_market.service.factura;

import com.informatorio.info_market.domain.Carrito;
import com.informatorio.info_market.domain.Factura;

public interface FacturaService {
    void crearFactura(Carrito carrito, Factura factura);
}
