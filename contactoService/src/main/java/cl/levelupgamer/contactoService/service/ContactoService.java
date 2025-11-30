package cl.levelupgamer.contactoService.service;

import cl.levelupgamer.contactoService.model.Contacto;
import cl.levelupgamer.contactoService.repository.ContactoRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class ContactoService {

    private final ContactoRepository contactoRepository;

    public ContactoService(ContactoRepository contactoRepository) {
        this.contactoRepository = contactoRepository;
    }

    // GUARDAR CONTACTO
    public String guardar(Contacto contacto) throws ExecutionException, InterruptedException {
        // Fecha en español
        SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy, HH:mm:ss");
        contacto.setCreadoEn(sdf.format(new Date()));

        return contactoRepository.guardarContacto(contacto);
    }

    // BUSCAR POR ID
    public Contacto buscarPorId(String id) throws ExecutionException, InterruptedException {
        return contactoRepository.obtenerPorId(id);
    }
}
