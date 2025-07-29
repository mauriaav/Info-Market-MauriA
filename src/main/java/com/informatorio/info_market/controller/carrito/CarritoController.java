package com.informatorio.info_market.controller.carrito;

import com.informatorio.info_market.domain.Carrito;
import com.informatorio.info_market.service.carrito.CarritoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/carritos")
@AllArgsConstructor
public class CarritoController {

    private CarritoService carritoService;

    @PostMapping("agregarProducto/{idUser}/{idProducto}")
    public ResponseEntity agregarProducto(
            @PathVariable UUID idUser,
            @PathVariable UUID idProducto) {

        carritoService.agregarProducto(idUser, idProducto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{idCarrito}/cerrar")
    public ResponseEntity cerrarCarrito(@PathVariable UUID idCarrito){
        carritoService.cerrarCarrito(idCarrito);
        return  ResponseEntity.ok().build();
    }

    @GetMapping("/{idCarrito}")
    public Carrito getCarritoById(@PathVariable UUID idCarrito){
        return carritoService.getCarritoEntityById(idCarrito);
    }

}
