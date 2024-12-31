package com.ps.productservice.mapper;

import com.ps.productservice.dto.*;
import com.ps.productservice.enums.EventType;
import com.ps.productservice.event.ProductCreatedEvent;
import com.ps.productservice.event.ProductUpdatedEvent;
import com.ps.productservice.model.Image;
import com.ps.productservice.model.Inventory;
import com.ps.productservice.model.Product;
import com.ps.productservice.model.Variant;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public Product toEntity(CreateProductRequest dto) {
        if (dto == null) {
            return null;
        }
        Product product = new Product();
        product.setSku(dto.getSku());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategoryId(dto.getCategoryId());
        product.setInventory(toEntity(dto.getInventory()));
        product.setVariants(toVariantEntities(dto.getVariants()));
        product.setImages(toImageEntities(dto.getImages()));
        product.setAdditionalParams(dto.getAdditionalParams());
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());
        return product;
    }

    public void updateEntity(UpdateProductRequest dto, Product product) {
        if (dto == null || product == null) {
            return;
        }
        product.setSku(dto.getSku());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategoryId(dto.getCategoryId());
        product.setInventory(toEntity(dto.getInventory()));
        product.setVariants(toVariantEntities(dto.getVariants()));
        product.setImages(toImageEntities(dto.getImages()));
        product.setAdditionalParams(dto.getAdditionalParams());
        product.setUpdatedAt(new Date());
    }

    public ProductResponse toDto(Product entity) {
        if (entity == null) {
            return null;
        }
        ProductResponse response = new ProductResponse();
        response.setId(entity.getId());
        response.setSku(entity.getSku());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setPrice(entity.getPrice());
        response.setCategoryId(entity.getCategoryId());
        response.setInventory(toDto(entity.getInventory()));
        response.setVariants(toVariantDTOs(entity.getVariants()));
        response.setImages(toImageDTOs(entity.getImages()));
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        response.setAdditionalParams(entity.getAdditionalParams());
        return response;
    }

    public ProductCreatedEvent toEvent(CreateProductRequest request, String correlationId) {
        ProductCreatedEvent event = new ProductCreatedEvent();
        event.setCorrelationId(correlationId);
        event.setCreateProductRequest(request);
        event.setEventType(EventType.PRODUCT_CREATED);
        return event;
    }

    public ProductUpdatedEvent toEvent(UpdateProductRequest request, String correlationId) {
        ProductUpdatedEvent event = new ProductUpdatedEvent();
        event.setCorrelationId(correlationId);
        event.setUpdateProductRequest(request);
        event.setEventType(EventType.PRODUCT_UPDATED);
        return event;
    }

    public Product toEntity(ProductCreatedEvent event) {
        if (event == null) {
            return null;
        }
        return toEntity(event.getCreateProductRequest());
    }

/*    public Product toEntity(ProductUpdatedEvent event) {
        if (event == null) {
            return null;
        }
        return toEntity(event.getUpdateProductRequest());
    }*/

    private Inventory toEntity(InventoryDTO dto) {
        if (dto == null) {
            return null;
        }
        Inventory inventory = new Inventory();
        inventory.setQuantity(dto.getQuantity());
        return inventory;
    }

    private List<Variant> toVariantEntities(List<VariantDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    private Variant toEntity(VariantDTO dto) {
        if (dto == null) {
            return null;
        }
        Variant variant = new Variant();
        variant.setName(dto.getName());
        variant.setValue(dto.getValue());
        return variant;
    }

    private List<Image> toImageEntities(List<ImageDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    private Image toEntity(ImageDTO dto) {
        if (dto == null) {
            return null;
        }
        Image image = new Image();
        image.setUrl(dto.getUrl());
        return image;
    }

    private InventoryDTO toDto(Inventory entity) {
        if (entity == null) {
            return null;
        }
        InventoryDTO dto = new InventoryDTO();
        dto.setQuantity(entity.getQuantity());
        return dto;
    }

    private List<VariantDTO> toVariantDTOs(List<Variant> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    private VariantDTO toDto(Variant entity) {
        if (entity == null) {
            return null;
        }
        VariantDTO dto = new VariantDTO();
        dto.setName(entity.getName());
        dto.setValue(entity.getValue());
        return dto;
    }

    private List<ImageDTO> toImageDTOs(List<Image> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    private ImageDTO toDto(Image entity) {
        if (entity == null) {
            return null;
        }
        ImageDTO dto = new ImageDTO();
        dto.setUrl(entity.getUrl());
        return dto;
    }
}
