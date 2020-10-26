package com.millet.service.address;

import com.millet.dao.impl.AddressDaoImpl;
import com.millet.dao.utilDao;
import com.millet.pojo.Address;

import java.util.List;

public class AddressServiceImpl implements AddressService{
    private utilDao addressDaoImpl;

    public AddressServiceImpl() {
        this.addressDaoImpl = new AddressDaoImpl();
    }


    public boolean insert(Address address) {
        return addressDaoImpl.insert(address);
    }

    public boolean update(Address address) {
        return addressDaoImpl.update(address);
    }

    public boolean delete(Address address) {
        return addressDaoImpl.delete(address);
    }

    public List<Address> list(Address address) {
        return addressDaoImpl.list(address);
    }

    public Address single(String name) {
        return (Address) addressDaoImpl.single(name);
    }
}
