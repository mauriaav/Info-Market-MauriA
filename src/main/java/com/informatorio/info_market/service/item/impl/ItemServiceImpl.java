package com.informatorio.info_market.service.item.impl;

import com.informatorio.info_market.domain.Carrito;
import com.informatorio.info_market.domain.ItemCarrito;
import com.informatorio.info_market.domain.Producto;
import com.informatorio.info_market.dto.itemCarrito.ItemCarritoDto;
import com.informatorio.info_market.mapper.itemCarrito.ItemCarritoMapper;
import com.informatorio.info_market.repository.item.ItemCarritoRepository;
import com.informatorio.info_market.service.item.ItemService;
import com.informatorio.info_market.service.producto.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private ItemCarritoRepository itemCarritoRepository;

    private ProductoService productoService;
    private ItemCarritoMapper itemCarritoMapper;


    @Override
    public ItemCarrito crearItemCarrito(Carrito carrito, Producto producto, int cantidad) {
        Optional<ItemCarrito> itemCarrito = getItemCarritoById( carrito.getItemsCarritos(), producto.getId() );
        productoService.descontarStock( producto, cantidad );

        if ( itemCarrito.isPresent() ) {
            itemCarrito.get().setCantidad( itemCarrito.get().getCantidad() + cantidad );
            return itemCarritoRepository.save(itemCarrito.get());
        }else {

            ItemCarrito nuevoItemCarrito = new ItemCarrito();
            nuevoItemCarrito.setCantidad( cantidad );
            nuevoItemCarrito.setProducto( producto );
            nuevoItemCarrito.setCarrito( carrito );

            return itemCarritoRepository.save(nuevoItemCarrito);
        }
    }

    private Optional<ItemCarrito> getItemCarritoById(List<ItemCarrito> items, UUID idProducto) {

        if ( items == null || items.isEmpty() ){
            return Optional.empty();
        }

        Optional<ItemCarrito> itemCarrito = items
                .stream()
                .filter(item -> item.getProducto().getId().equals( idProducto ))
                .findFirst();

        return itemCarrito;
    }

    @Override
    public List<ItemCarritoDto> listarItemsCarrito(List<ItemCarrito> productos) {
        List<ItemCarritoDto> itemsCarrito = new ArrayList<>();
        itemsCarrito.addAll(
                productos.stream()
                        .map(item -> itemCarritoMapper.itemCarritoToItemCarritoDto(item))
                        .toList()
        );

        return itemsCarrito;
    }

    @Override
    public Double obtenerValorTotal(List<ItemCarritoDto> items) {
        return items.stream()
                .mapToDouble(item -> item.getPrecioTotal())
                .sum();
    }
}
