package org.example;

public class VendaConsumoComando implements Comando {
    private final Animal animal;
    private Double precoAnterior;

    public VendaConsumoComando(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("Animal não pode ser nulo");
        }
        this.animal = animal;
    }

    @Override
    public void executar() {
        precoAnterior = animal.getPreco();
        animal.VenderConsumo();
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
