package com.informatorio.info_market.service.carrito.impl;

import com.informatorio.info_market.domain.*;
import com.informatorio.info_market.dto.itemCarrito.ItemCarritoDto;
import com.informatorio.info_market.enumerations.EstadoCarritoEnum;
import com.informatorio.info_market.exception.notfound.NotFoundException;
import com.informatorio.info_market.repository.carrito.CarritoRepository;
import com.informatorio.info_market.repository.factura.FacturaRepository;
import com.informatorio.info_market.repository.usuario.UsuarioRepository;
import com.informatorio.info_market.service.carrito.CarritoService;
import com.informatorio.info_market.service.factura.FacturaService;
import com.informatorio.info_market.service.item.ItemService;
import com.informatorio.info_market.service.producto.ProductoService;
import com.informatorio.info_market.service.usuario.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class CarritoServiceImpl implements CarritoService {

    private CarritoRepository carritoRepository;

    private UsuarioRepository usuarioRepository;

    private ProductoService productoService;
    private FacturaService facturaService;

    private ItemService itemService;

    @Override
    public void agregarProducto(Usuario usuario, UUID idProducto) {
        Producto producto = productoService.getProductoEntityById(idProducto);

        Optional<Carrito> carrito = getCarritoConEstado( EstadoCarritoEnum.ABIERTO, usuario.getCarritos() );

        if (carrito.isPresent()){
                ItemCarrito itemCarrito =itemService.crearItemCarrito( carrito.get(), producto, 1 );
                carrito.get().getItemsCarritos().add(  itemCarrito );

                carritoRepository.save( carrito.get() );
        }else {
            Carrito carritoNuevo = new Carrito();
            carritoNuevo.setEstadoCarrito(EstadoCarritoEnum.ABIERTO);
            carritoNuevo.setUsuario(usuario);
            carritoNuevo.setFactura(null);
            carritoNuevo.setFechaActualizacion(LocalDate.now());
            carritoNuevo.setFechaDeCreacion(LocalDate.now());
            usuario.getCarritos().add(carritoNuevo);
            ItemCarrito itemCarrito = itemService.crearItemCarrito( carritoNuevo, producto, 1 );
            carritoNuevo.getItemsCarritos().add( itemCarrito );
            carritoRepository.save( carritoNuevo );
            usuarioRepository.save(usuario);

        }

    }

    @Override
    public Optional<Carrito> getCarritoConEstado(EstadoCarritoEnum estadoCarritoEnum, List<Carrito> carritos) {
        return carritos.stream()
                .filter( carrito -> estadoCarritoEnum.equals( carrito.getEstadoCarrito() ))
                .findFirst();
    }

    @Override
    public Carrito getCarritoEntityById(UUID idCarrito){
        Optional<Carrito> carrito = carritoRepository.findById(idCarrito);
        if(carrito.isPresent()){
            return carrito.get();
        }
        else{
            throw new NotFoundException("No se encontro el carrito con id : " + idCarrito);
        }
    }

    @Override
    public Map<String, Object> cerrarCarritoUsuario(Usuario usuario) {
        Optional<Carrito> carritoACerrar = getCarritoConEstado( EstadoCarritoEnum.ABIERTO, usuario.getCarritos());
        if(carritoACerrar.isPresent()){
            return cerrarCarrito(carritoACerrar.get());
        }
        else{
            throw new IllegalStateException("El carrito ya está cerrado");
        }
    }

    @Override
    public Map<String, Object> cerrarCarrito(Carrito carrito){
        carrito.setEstadoCarrito(EstadoCarritoEnum.CERRADO);
        Factura nueva_factura = new Factura();
        facturaService.crearFactura(carrito, nueva_factura);

        carrito.setFactura(nueva_factura);
        carritoRepository.save(carrito);

        Map<String, Object> facturaFinal = new HashMap<>();
        List<ItemCarritoDto> items = itemService.listarItemsCarrito(carrito.getItemsCarritos());
        double precioTotal = itemService.obtenerValorTotal(items);
        facturaFinal.put("productos", items);
        facturaFinal.put("precioTotal",precioTotal);
        return facturaFinal;
    }

}
