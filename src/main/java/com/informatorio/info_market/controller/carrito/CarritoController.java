package com.informatorio.info_market.controller.carrito;

import com.informatorio.info_market.domain.Carrito;
import com.informatorio.info_market.domain.Usuario;
import com.informatorio.info_market.service.carrito.CarritoService;
import com.informatorio.info_market.service.usuario.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/carritos")
@AllArgsConstructor
public class CarritoController {

    private CarritoService carritoService;
    private UsuarioService usuarioService;

    @PostMapping("agregarProducto/{idUser}/{idProducto}")
    public ResponseEntity agregarProducto(
            @PathVariable UUID idUser,
            @PathVariable UUID idProducto) {

        Usuario usuario =usuarioService.getUsuarioEntityById(idUser);
        carritoService.agregarProducto(usuario, idProducto);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{idCarrito}")
    public Carrito getCarritoById(@PathVariable UUID idCarrito){
        return carritoService.getCarritoEntityById(idCarrito);
    }

}
