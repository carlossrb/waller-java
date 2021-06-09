package com.walller.api.wallerjava.Repositories;
import com.walller.api.wallerjava.Models.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório é a parte responsável para interagir com o banco de dados
 * É uma interface que herda os métodos do JPA (spring jpa)
 * Não precisa criar nenhum método, a não ser alguma query mais específica
 */

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    
}
