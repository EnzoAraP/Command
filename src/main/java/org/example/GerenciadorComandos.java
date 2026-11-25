package org.example;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorComandos {
    private final List<Comando> comandos = new ArrayList<>();

    public void executarComando(Comando comando) {
        if (comando == null) {
            throw new IllegalArgumentException("Comando não pode ser nulo");
        }
        comando.executar();
        comandos.add(comando);
    }

    public void cancelarUltimoComando() {
        if (!comandos.isEmpty()) {
            int ultimoIndice = comandos.size() - 1;
            Comando comando = comandos.remove(ultimoIndice);
            comando.cancelar();
        }
    }
}
