package com.letscode.cookBook.view;

import com.letscode.cookBook.domain.Ingrediente;
import com.letscode.cookBook.domain.Receita;
import com.letscode.cookBook.domain.Rendimento;
import com.letscode.cookBook.enums.Categoria;
import com.letscode.cookBook.enums.TipoMedida;
import com.letscode.cookBook.enums.TipoRendimento;

import java.util.Scanner;

public class NovaReceitaView {
    Scanner scanner;
    Receita receita;
    String nome;
    String tempo_preparo;

    public NovaReceitaView() {
        this.scanner = new Scanner(System.in);
    }

    public Receita montarReceita() {
        scanner = new Scanner(System.in);

        receita = new Receita(askNome(), askCategoria());
        receita.setTempoPreparo(askTempoPreparo());
        receita.setRendimento(askRendimento());
        askIngredientes();
        askModoDePreparo();

        return receita;
    }

    private String askNome() {
        System.out.println("Qual o nome da receita?");
        nome = isBlank("O nome da receita é inválido! Tente novamente.");

        return nome;
    }

    private Categoria askCategoria() {
        System.out.println("Qual a categoria da receita?");
        for (Categoria cat : Categoria.values()) {
            System.out.printf("%d - %s %n", cat.ordinal(), cat.name());
        }

        int categoria = isInt("Categoria inválida! Escolha um das opções permitidas.",
                                "Categoria inválida! Escolha um das opções permitidas.");

        return Categoria.values()[categoria];
    }

    private int askTempoPreparo() {
        System.out.println("Qual o tempo de preparo da receita em segundos?");

        int tempoPreparo = isInt("Tempo de preparo inválido! O tempo de preparo deve ser em segundos.",
                "Tempo de preparo inválido!");

        return tempoPreparo;
    }

    private Rendimento askRendimento() {
        this.scanner = new Scanner(System.in);

        int rendimento;
        int tipo;

        System.out.println("Qual o rendimento da receita?");

        rendimento = isInt("Redimento inválido! O redimento deve ser inteiro.",
                "Rendimento inválido!");

        System.out.println("Qual o tipo do rendimento da receita?");
        for (TipoRendimento tr : TipoRendimento.values()) {
            System.out.printf("%d - %s %n", tr.ordinal(), tr.name());
        }

        tipo = isInt("Tipo de rendimento inválido! Escolha um das opções permitidas.",
                "Tipo de rendimento inválido! Escolha um das opções permitidas.");

        return new Rendimento(rendimento, TipoRendimento.values()[tipo]);
    }

    private void askIngredientes() {
        this.scanner = new Scanner(System.in);

        System.out.println("\n##### INSIRA OS INGREDIENTES DE SUA RECEITA #####");

        int count = 1;
        String ingrediente;
        double quantidade;
        int medida;
        String verificacao;

        do {
            System.out.println("Qual o nome do " + count + "º ingrediente da receita?");
            ingrediente = isBlank("O nome do ingrediente está inválido! Tente novamente");

            System.out.println("Qual a quantidade do " + count + "º ingrediente da receita?");
            quantidade = isDouble("Quantidade do ingrediente inválida! A quantidade deve ser numérica.",
                    "Quantidade do ingrediente inválida!");

            System.out.println("Qual o tipo da medida do " + count + "º ingrediente da receita?");
            for (TipoMedida tmi : TipoMedida.values()) {
                System.out.printf("%d - %s %n", tmi.ordinal(), tmi.name());
            }

            medida = isInt("Tipo de medida inválido! Escolha um das opções permitidas.",
                    "Tipo de medida inválido! Escolha um das opções permitidas.");

            receita.setIngredientes(new Ingrediente[]{new Ingrediente(ingrediente, quantidade, TipoMedida.values()[medida])});
            count++;

            do {
                System.out.print("Deseja inserir um novo ingrediente? (S/N) ");
                verificacao = scanner.nextLine().toUpperCase();
            } while (!verificacao.equals("S") && !verificacao.equals("N"));
        } while (!verificacao.equals("N"));
    }

    private void askModoDePreparo() {
        this.scanner = new Scanner(System.in);

        System.out.println("\n##### INSIRA OS PASSOS DO MODE DE PREPARO DE SUA RECEITA #####");

        int count = 1;
        String modoDePreparo;
        String verificacao;

        do {
            System.out.println("Qual o " + count + "º passo do modo de preparo da receita?");
            modoDePreparo = isBlank("O modo de preparo está inválido! Tente novamente.");

            receita.setModoPreparo(new String[]{modoDePreparo});
            count++;

            do {
                System.out.print("Deseja inserir mais um passo ao modo de preparo? (S/N) ");
                verificacao = scanner.nextLine().toUpperCase();
            } while (!verificacao.equals("S") && !verificacao.equals("N"));
        } while (!verificacao.equals("N"));
    }

    private int isInt(String msg1, String msg2) {
        int valor;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println(msg1);
                scanner.next();
            }

            valor = scanner.nextInt();
            if (valor < 0) {
                System.out.println(msg2);
            }
        } while (valor < 0);

        return valor;
    }

    private double isDouble(String mensagemUm, String mensagemDois) {
        double valor;
        do {
            while (!scanner.hasNextDouble()) {
                System.out.println(mensagemUm);
                scanner.next();
            }

            valor = scanner.nextDouble();
            if (valor < 0) {
                System.out.println(mensagemDois);
            }
        } while (valor < 0);

        return valor;
    }

    private String isBlank(String mensagem) {
        String nome = scanner.nextLine();

        do {
            if (nome.isBlank()) {
                System.out.println(mensagem);
                askNome();
            }
        } while (nome.isBlank());

        return nome;
    }
}
