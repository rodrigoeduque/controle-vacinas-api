package br.com.rodrigoeduque.ApiControleVacinacaoBrasil.rest;

import br.com.rodrigoeduque.ApiControleVacinacaoBrasil.model.entity.Usuario;
import br.com.rodrigoeduque.ApiControleVacinacaoBrasil.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {


    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@Valid @RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @GetMapping
    public List<Usuario> listar() {
        List<Usuario> usuario = usuarioRepository.findAll();
        return usuario;
    }

    @GetMapping("{id}")
    public Usuario buscarPorId(@PathVariable Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void deletar(@PathVariable Integer id) {

        usuarioRepository.findById(id)
                .map( usuario -> {
                    System.out.println("Entrei no delete : Id " + usuario  );
                    usuarioRepository.deleteById(id);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("{id}")
    public void atualizar(@PathVariable Integer id,@Valid @RequestBody Usuario usuarioAtualizado){
        usuarioRepository.findById(id)
                .map(usuario -> {
                    usuarioAtualizado.setId(usuario.getId());
                   return usuarioRepository.save(usuarioAtualizado);
                })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }


}
