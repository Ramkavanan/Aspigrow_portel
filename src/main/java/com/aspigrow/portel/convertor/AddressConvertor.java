package com.aspigrow.portel.convertor;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.aspigrow.portel.model.AddressModel;
import com.aspigrow.persistence.entities.user.Address;


@Component("addressConvertor")
public class AddressConvertor implements Convertor<AddressModel, Address> {
	
    @Override
    public  Address convertToEntity(AddressModel model) {
        if (model == null) {
            return null;
        }
        Address address = new Address();
        address.setState(model.getState());
        address.setCity(model.getCity());
        address.setAddressLine1(model.getAddressLine1());
        address.setAddressLine2(model.getAddressLine2());
        address.setPincode(model.getPincode());
        return address;
    }

    @Override
    public AddressModel convertToModel(Address entity) {
        if (entity == null) {
            return null;
        }
        AddressModel address = new AddressModel();
        address.setState(entity.getState());
        address.setCity(entity.getCity());
        address.setAddressLine1(entity.getAddressLine1());
        address.setAddressLine2(entity.getAddressLine2());
        address.setPincode(entity.getPincode());
        return address;
    }

    @Override
    public void updateEntityWithModel(Address toUpdate, AddressModel model) {

    }
}
