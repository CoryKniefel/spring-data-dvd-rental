package com.fun.springdatadvdrental.domain.store;

import com.fun.springdatadvdrental.domain.address.AddressDTOMapper;
import org.springframework.stereotype.Service;

@Service
public class StoreDTOMapper {


    AddressDTOMapper addressDTOMapper = new AddressDTOMapper();

    public StoreDTO fromStore(Store store) {
        return StoreDTO
                .builder()
                .address(addressDTOMapper.fromAddress(store.getAddress()))
                .storeId(store.getStoreId())
                .build();
    }

//    public Store fromStoreDTO(StoreDTO storeDTO) {
//        return Store.builder()
//                .storeId(storeDTO.storeId)
//                .address(addressDTOMapper.fromAddress())
//    }
}
