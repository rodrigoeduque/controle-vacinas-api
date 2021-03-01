package br.com.rodrigoeduque.ApiControleVacinacaoBrasil.rest;

import br.com.rodrigoeduque.ApiControleVacinacaoBrasil.model.entity.AplicacaoVacina;
import br.com.rodrigoeduque.ApiControleVacinacaoBrasil.model.entity.Usuario;
import br.com.rodrigoeduque.ApiControleVacinacaoBrasil.model.repository.AplicacaoVacinaRepository;
import br.com.rodrigoeduque.ApiControleVacinacaoBrasil.model.repository.UsuarioRepository;
import br.com.rodrigoeduque.ApiControleVacinacaoBrasil.rest.dto.AplicacaoVacinaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/controle-vacinacao")
public class AplicacaoVacinaController {
    private final UsuarioRepository usuarioRepository;
    private final AplicacaoVacinaRepository aplicacaoVacinaRepository;

    public AplicacaoVacinaController(UsuarioRepository usuarioRepository, AplicacaoVacinaRepository aplicacaoVacinaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.aplicacaoVacinaRepository = aplicacaoVacinaRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AplicacaoVacina salvar(@RequestBody @Valid AplicacaoVacinaDTO aplicacaoVacinaDTO){

        LocalDate data = LocalDate.parse(aplicacaoVacinaDTO.getDataAplicacao(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer idUsuario = aplicacaoVacinaDTO.getIdUsuario();

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Usuário não existe"));


        AplicacaoVacina aplicacaoVacina = new AplicacaoVacina();
        aplicacaoVacina.setNomeVacina(aplicacaoVacinaDTO.getNomeVacina());
        aplicacaoVacina.setDataAplicacao(data);
        aplicacaoVacina.setUsuario(usuario);

        return aplicacaoVacinaRepository.save(aplicacaoVacina);

    }

    @GetMapping
    public List<AplicacaoVacina> listar(){
        List<AplicacaoVacina> aplicacaoVacinas = aplicacaoVacinaRepository.findAll();
        return aplicacaoVacinas;
    }

/*    @GetMapping("{id}")
    public List<AplicacaoVacina> buscarPorId(@PathVariable Integer id){
        List<AplicacaoVacina> aplicacaoVacinas = aplicacaoVacinaRepository.buscaDetalhada(id);
        System.out.println("ID :" + id);
        return aplicacaoVacinas;
    }*/


    @GetMapping("{id}")
    public AplicacaoVacina buscarPorId(@PathVariable Integer id){
        return aplicacaoVacinaRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Codigo de Vacinação não existe"));
    }

}
