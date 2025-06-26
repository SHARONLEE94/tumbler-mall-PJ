package com.tumblermall.products.service;

import com.tumblermall.products.dto.ProductDTO;
import com.tumblermall.products.dto.ProductDetailDTO;
import com.tumblermall.products.dto.ProductResponseDTO;
import com.tumblermall.products.dto.SizeOptionDTO;
import com.tumblermall.products.mapper.ProductsMapper;
import com.tumblermall.products.vo.ProductDetailVO;
import com.tumblermall.products.vo.ProductVO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class ProductService {
    private final ProductsMapper productsMapper;

    public ProductService(ProductsMapper productsMapper) {
        this.productsMapper = productsMapper;
    }


    public List<ProductResponseDTO> getProductResponse(Long productId) {
        List<ProductVO> productVOList = productsMapper.selectProductById(productId);
        if (productVOList == null || productVOList.isEmpty()) {
            return null;
        }

        ProductVO productVO = productVOList.get(0);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productVO.getId());
        productDTO.setName(productVO.getName());
        productDTO.setPrice(productVO.getPrice());
        // ... 필요한 필드 추가 설정

        List<ProductDetailVO> detailVOList = productsMapper.selectDetailsByProductId(productId);

        // ✔️ 중복 방지용 Map<사이즈, 추가금>
        Map<String, String> sizeMap = new LinkedHashMap<>();
        // ✔️ 색상 리스트
        Set<String> colorSet = new LinkedHashSet<>();
        ProductDetailDTO detailDTO = new ProductDetailDTO();

        if (detailVOList != null) {
            for (ProductDetailVO vo : detailVOList) {
                // 중복 사이즈 제거 + 추가금 세팅
                String size = vo.getSize();
                String price = vo.getAdditionalPrice();
                if (!sizeMap.containsKey(size)) {
                    sizeMap.put(size, price);
                }

                // 색상 추가 (중복 제거용 Set)
                colorSet.add(vo.getColor());

                // 상세 정보는 한 번만 세팅
                if (detailDTO.getId() == null) {
                    detailDTO.setId(vo.getId());
                    detailDTO.setProductId(vo.getProductId());
                    detailDTO.setMadeInCountry(vo.getMadeInCountry());
                    detailDTO.setCountry(vo.getCountry());
                    detailDTO.setMaterial(vo.getMaterial());
                    detailDTO.setMaterialId(vo.getMaterialId());
                    detailDTO.setWeight(vo.getWeight());
                    detailDTO.setDimensions(vo.getDimensions());
                    detailDTO.setHeatRetentionTime(vo.getHeatRetentionTime());
                    detailDTO.setColdRetentionTime(vo.getColdRetentionTime());
                    detailDTO.setIceRetentionTime(vo.getIceRetentionTime());
                    detailDTO.setSealed(vo.getSealed());
                    detailDTO.setComponents(vo.getComponents());
                    detailDTO.setAsAvailable(vo.getAsAvailable());
                    detailDTO.setAsPeriod(vo.getAsPeriod());
                    detailDTO.setCareNoteId(vo.getCareNoteId());
                    detailDTO.setProductStrengths(vo.getProductStrengths());
                }
            }
        }

        // 최종 색상/사이즈 세팅
        List<SizeOptionDTO> sizeOptions = sizeMap.entrySet().stream()
                .map(entry -> {
                    SizeOptionDTO dto = new SizeOptionDTO();
                    dto.setSize(entry.getKey());
                    dto.setAdditionalPrice(entry.getValue());
                    return dto;
                })
                .collect(Collectors.toList());

        detailDTO.setColor(new ArrayList<>(colorSet));
        detailDTO.setSizeOptions(sizeOptions);

        // 응답 세팅
        ProductResponseDTO responseDTO = new ProductResponseDTO(productDTO, List.of(detailDTO));
        return List.of(responseDTO);
    }

    public String getProductOptionId(String productId, String color, String size) {
        List<ProductDetailVO> details = productsMapper.selectDetailsByProductId(Long.parseLong(productId));
        for (ProductDetailVO detail : details) {
            if (detail.getColor().equals(color) && detail.getSize().equals(size)) {
                return String.valueOf(detail.getId());
            }
        }
        throw new IllegalArgumentException("Invalid option: color=" + color + ", size=" + size);
    }

    public List<ProductVO> getProductsByCategoryId(String categoryId) {
        return productsMapper.selectProductsByCategoryId(categoryId);
    }

    public List<ProductVO> getProductsBySeriesId(String seriesId) {
        return productsMapper.selectProductsBySeriesId(seriesId);
    }
}

