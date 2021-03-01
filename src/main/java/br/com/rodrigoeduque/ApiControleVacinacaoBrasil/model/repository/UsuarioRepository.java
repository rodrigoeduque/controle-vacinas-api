package br.com.rodrigoeduque.ApiControleVacinacaoBrasil.model.repository;

import br.com.rodrigoeduque.ApiControleVacinacaoBrasil.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
