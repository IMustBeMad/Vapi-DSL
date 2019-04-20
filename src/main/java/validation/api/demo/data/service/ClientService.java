package validation.api.demo.data.service;

import org.springframework.stereotype.Service;
import validation.api.demo.data.common.Client;

@Service
public class ClientService {

    public Client getOne(Long id) {
        return new Client();
    }
}
