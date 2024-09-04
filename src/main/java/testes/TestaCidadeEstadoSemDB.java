package testes;

import model.Cidade;
import model.Estado;
import model.Pessoa;

public class TestaCidadeEstadoSemDB {
    public static void main(String[] args){
        Estado estado = new Estado("Amazonas", "AM");
        Cidade cidade = new Cidade("Manaus",estado);

        Pessoa pessoa1 = new Pessoa("45698712345","pessoa1",16);
        Pessoa pessoa2 = new Pessoa("45698712344","pessoa2",17);
        Pessoa pessoa3 = new Pessoa("45698712343","pessoa3",18);

        cidade.addPessoa(pessoa1);
        cidade.addPessoa(pessoa2);
        cidade.addPessoa(pessoa3);

        cidade.getPessoas().forEach((pessoa) -> {
           System.out.println(pessoa.toString());
        });
    }
}
