package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GerenciadorComandosTest {
    private GerenciadorComandos gerenciador;
    private Animal animal;

    @BeforeEach
    void setUp() {
        gerenciador = new GerenciadorComandos();
        animal = new AnimalTeste(100.0);
    }

    @Test
    void deveAlterarPrecoAoExecutarComando() {
        gerenciador.executarComando(new AlterarPrecoComando(animal, 250.0));

        assertEquals(250.0, animal.getPreco());
    }

    @Test
    void deveRestaurarPrecoAoCancelarAlteracao() {
        gerenciador.executarComando(new AlterarPrecoComando(animal, 250.0));

        gerenciador.cancelarUltimoComando();

        assertEquals(100.0, animal.getPreco());
    }

    @Test
    void deveDesfazerVendaParaConsumo() {
        Animal animalQueValePorPeso = new AnimalTeste(100.0) {
            @Override
            public void VenderConsumo() {
                setPreco(300.0);
            }
        };

        gerenciador.executarComando(new VendaConsumoComando(animalQueValePorPeso));
        assertEquals(300.0, animalQueValePorPeso.getPreco());

        gerenciador.cancelarUltimoComando();

        assertEquals(100.0, animalQueValePorPeso.getPreco());
    }

    @Test
    void naoDeveCancelarComandoQueAindaNaoFoiExecutado() {
        VendaConsumoComando comando = new VendaConsumoComando(animal);

        assertThrows(IllegalStateException.class, comando::cancelar);
    }

    private static class AnimalTeste implements Animal {
        private double preco;
        private String dono;

        private AnimalTeste(double preco) {
            this.preco = preco;
        }

        @Override
        public double getPreco() {
            return preco;
        }

        @Override
        public void setPreco(double preco) {
            this.preco = preco;
        }

        @Override
        public void VenderConsumo() {
            setPreco(preco + 50.0);
        }

        @Override
        public String getDono() {
            return dono;
        }

        @Override
        public void setDono(String dono) {
            this.dono = dono;
        }
    }
}
