package pl.netpaper.shop.service;

import pl.netpaper.shop.model.dao.User;

public interface SecurityService {

    boolean hasAccessToUser(Long id);

}
