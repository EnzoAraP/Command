package org.example;

public class AlterarPrecoComando implements Comando {
    private final Animal animal;
    private final double novoPreco;
    private Double precoAnterior;

    public AlterarPrecoComando(Animal animal, double novoPreco) {
        if (animal == null) {
            throw new IllegalArgumentException("Animal não pode ser nulo");
        }
        this.animal = animal;
        this.novoPreco = novoPreco;
    }

    @Override
    public void executar() {
        precoAnterior = animal.getPreco();
        animal.setPreco(novoPreco);
    }

    @Override
    public void cancelar() {
        if (precoAnterior == null) {
            throw new IllegalStateException("O comando ainda não foi executado");
        }
        animal.setPreco(precoAnterior);
        precoAnterior = null;
    }
}
