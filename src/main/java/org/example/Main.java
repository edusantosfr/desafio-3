package org.example;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static String gameOptionTips = "s";

    public static int[] difEasy = {50, 10, 100};
    public static int[] difMedium = {100, 7, 200};
    public static int[] difHard = {200, 5, 300};
    public static int[] difDemiGod = {200, 11, 500};

    public static int totalPoints = 0;

    public static int pointsUsedOnTips = 0;

    public static int[] bestResults = new int[4];

    static ArrayList<MatchResultObj> matchList = new ArrayList<>();

    static class MatchResultObj {
        int matchPoints;
        int matchDifficulty;

        public MatchResultObj(int matchPoints, int matchDifficulty) {
            this.matchPoints = matchPoints;
            this.matchDifficulty = matchDifficulty;
        }

        public void showMatchInf() {
            String difficultyInFull = switch (matchDifficulty) {
                case 1 -> "Fácil";
                case 2 -> "Médio";
                case 3 -> "Difícil";
                default -> "Demi God";
            };

            System.out.println("Pontos: " + matchPoints + " / Dificuldade: " + difficultyInFull);
        }
    }

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        boolean continueVar = true;
        while (continueVar) {
            int option;
            System.out.println("-------Menu-------");
            System.out.println("1. Iniciar Novo Jogo");
            System.out.println("2. Ver Regras");
            System.out.println("3. Ver Histórico de Pontuação");
            System.out.println("4. Ver Melhores Pontuações");
            System.out.println("5. Opções de Jogo");
            System.out.println("0. Sair");

            System.out.print("Digite sua Opção: ");
            if (scanner.hasNextInt()) {
                option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1 -> difficultyMenu();
                    case 2 -> rules();
                    case 3 -> resultList();
                    case 4 -> bestMatches();
                    case 5 -> gameOptions();
                    case 0 -> {
                        continueVar = false;
                        System.out.println("\nObrigado por Jogar!!");
                    }
                    default -> System.out.println("\nDigite uma opção válida!!\n");
                }
            } else {
                System.out.println("\nDigite uma Entrada Válida!!\n");
                scanner.next();
            }
        }
    }

    public static void difficultyMenu() {
        while (true) {
            int difficulty;
            System.out.println("\nEscolha seu nível de dificuldade entre:");
            System.out.println("1. Fácil");
            System.out.println("2. Médio");
            System.out.println("3. Difícil");
            System.out.println("4. (Modo Secreto) DemiGod");
            System.out.println("0. Voltar");

            System.out.print("Digite sua Opção: ");
            if (scanner.hasNextInt()) {
                difficulty = Integer.parseInt(scanner.nextLine());
                if (difficulty == 0) {
                    System.out.print("\n");
                    break;
                } else if (difficulty > 0 && difficulty <= 4) {
                    playNewGame(difficulty);
                    break;
                } else {
                    System.out.println("\nDigite uma opção válida!!");
                }
            } else {
                System.out.println("\nDigite uma Entrada Válida!!");
                scanner.next();
            }
        }
    }

    public static void playNewGame(int dif) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        pointsUsedOnTips = 0;

        int difficulty = 0;
        int guess = 0;
        int points = 0;

        switch (dif) {
            case 1 -> {
                difficulty = difEasy[0];
                guess = difEasy[1];
                points = difEasy[2];
            }
            case 2 -> {
                difficulty = difMedium[0];
                guess = difMedium[1];
                points = difMedium[2];
            }
            case 3 -> {
                difficulty = difHard[0];
                guess = difHard[1];
                points = difHard[2];
            }
            case 4 -> {
                difficulty = difDemiGod[0];
                guess = difDemiGod[1];
                points = difDemiGod[2];
            }
        }

        int[] secretNumber = new int[3];
        for (int i = 0; i < secretNumber.length; i++) {
            secretNumber[i] = random.nextInt(1, difficulty);
        }
        //for (int j : secretNumber) {
        //    System.out.println(j);
        //}

        int guessedNumber;
        int pointsGuesses = 0;
        boolean result = false;

        int firstIndice;
        int secondIndice;
        int thirdIndice = 0;

        if (dif != 4) {
            System.out.println("\nSeja Bem Vindo(a) ao jogo de Adivinhação!!!");
            for (int i = 0; i < guess; i++) {
                System.out.println((i + 1) + "/" + guess + " tentativas");
                System.out.print("Digite um número de um 1 à " + difficulty + ": ");
                if (scanner.hasNextInt()) {
                    guessedNumber = Integer.parseInt(scanner.nextLine());
                    if (guessedNumber == secretNumber[0]) {
                        System.out.println("\nVocê Acertou!!");
                        result = true;
                        break;
                    } else {
                        System.out.println("Tente Novamente\n");
                        tipsMenu(guessedNumber, secretNumber[0], difficulty);
                    }
                    pointsGuesses++;
                } else {
                    System.out.println("\nDigite uma Entrada Válida!!\n");
                    scanner.next();
                }
            }
        } else {
            System.out.println("\n5eja Bem V1nd0(a) a0 jog0 de Ad1v1nhaçã0... :(");
            System.out.println("Boa Sorte, você definitivamente vai precisar...");

            for (firstIndice = 0; firstIndice < guess; firstIndice++) {
                System.out.println((firstIndice + 1) + "/" + guess + " tentativas");
                System.out.print("Digite um número de um 1 à " + difficulty + ": ");
                if (scanner.hasNextInt()) {
                    guessedNumber = Integer.parseInt(scanner.nextLine());
                    if (guessedNumber == secretNumber[0]) {
                        System.out.println("\nVocê Acertou!!");
                        break;
                    } else {
                        System.out.println("Tente Novamente\n");
                        tipsMenu(guessedNumber, secretNumber[0], difficulty);
                    }
                    pointsGuesses++;
                } else {
                    System.out.println("\nDigite uma Entrada Válida!!\n");
                    scanner.next();
                }
            }

            for (secondIndice = firstIndice + 1; secondIndice < guess; secondIndice++) {
                System.out.println((secondIndice + 1) + "/" + guess + " tentativas");
                System.out.print("Digite um número de um 1 à " + difficulty + ": ");
                if (scanner.hasNextInt()) {
                    guessedNumber = Integer.parseInt(scanner.nextLine());

                    if (guessedNumber == secretNumber[1]) {
                        System.out.println("\nVocê Acertou!!");
                        break;
                    } else {
                        System.out.println("Tente Novamente\n");
                        tipsMenu(guessedNumber, secretNumber[1], difficulty);
                    }
                    pointsGuesses++;
                } else {
                    System.out.println("\nDigite uma Entrada Válida!!\n");
                    scanner.next();
                }
            }

            for (thirdIndice = secondIndice + 1; thirdIndice < guess; thirdIndice++) {
                System.out.println((thirdIndice + 1) + "/" + guess + " tentativas");
                System.out.print("Digite um número de um 1 à " + difficulty + ": ");
                if (scanner.hasNextInt()) {
                    guessedNumber = Integer.parseInt(scanner.nextLine());

                    if (guessedNumber == secretNumber[2]) {
                        System.out.println("\nVocê Acertou!!");
                        result = true;
                        break;
                    } else {
                        System.out.println("Tente Novamente\n");
                        tipsMenu(guessedNumber, secretNumber[2], difficulty);
                    }
                    pointsGuesses++;
                } else {
                    System.out.println("\nDigite uma Entrada Válida!!\n");
                    scanner.next();
                }
            }
        }

        int matchPoints;
        if (result) {
            matchPoints = points + ((guess - thirdIndice - 1) * 50) - (pointsGuesses * 10) - pointsUsedOnTips;
            totalPoints += matchPoints;
        } else {
            matchPoints = 0;
            System.out.println("\nVocê Não Acertou...");
        }

        addingMatchToMatchList(matchPoints, dif);

        addingBestMatchResults(matchPoints, dif);

        if (dif != 4) {
            System.out.println("Número Secreto: " + secretNumber[0]);
            System.out.print("\n");
        } else {
            for (int i = 0; i < secretNumber.length; i++) {
                System.out.println((i + 1) + "º Número Secreto: " + secretNumber[i]);
            }
            System.out.print("\n");
        }
    }

    public static void addingMatchToMatchList(int matchPoints, int dif) {
        if (matchList.size() < 10) {
            matchList.add(new MatchResultObj(matchPoints, dif));
        } else {
            matchList.remove(0);
            matchList.add(new MatchResultObj(matchPoints, dif));
        }
    }

    public static void addingBestMatchResults(int matchPoints, int dif) {
        if (dif == 1 && bestResults[0] < matchPoints) {
            bestResults[0] = matchPoints;
        } else if (dif == 2 && bestResults[1] < matchPoints) {
            bestResults[1] = matchPoints;
        } else if (dif == 3 && bestResults[2] < matchPoints) {
            bestResults[2] = matchPoints;
        } else if (dif == 4 && bestResults[3] < matchPoints) {
            bestResults[3] = matchPoints;
        }
    }

    public static void rules() {
        System.out.println("\n---------------------Regras--------------------");
        System.out.println("Ao iniciar um novo jogo, o jogador pode escolher entre três níveis:");
        System.out.println("Fácil: Adivinhar um número entre 1 e 50, com 10 tentativas");
        System.out.println("Médio: Adivinhar um número entre 1 e 100, com 7 tentativas");
        System.out.println("Difícil: Adivinhar um número entre 1 e 200, com 5 tentativas");
        System.out.println("(Modo Secreto)DemiGod: Adivinhar 3 números entre 1 e 200, com 11 tentativas");
        System.out.print("\n");
        System.out.println("Pontuação base por nível:");
        System.out.println("Fácil (100)");
        System.out.println("Médio (200)");
        System.out.println("Difícil (300)");
        System.out.println("A cada tentativa usada, são descontados 10 pontos.");
        System.out.println("Bônus por conclusão rápida: +50 pontos para cada tentativa não utilizada.");
        System.out.print("\n");
        System.out.println("Pontuação por uso de Dicas: ");
        System.out.println("Dica de paridade (par/ímpar): -10 pontos");
        System.out.println("Dica de intervalo (metade superior/inferior): -20 pontos");
        System.out.println("Dica de proximidade (quente/frio): -15 pontos\n");
    }

    public static void resultList() {
        System.out.println("\n10 partidas anteriores:");
        for (MatchResultObj matchResult : matchList) {
            matchResult.showMatchInf();
        }
        System.out.println("\nTotal de Pontos: " + totalPoints + "\n");
    }

    public static void bestMatches() {
        System.out.println("\nMelhores partidas:");
        System.out.print("Fácil: ");
        System.out.println(bestResults[0] + " pontos");
        System.out.print("Médio: ");
        System.out.println(bestResults[1] + " pontos");
        System.out.print("Difícil: ");
        System.out.println(bestResults[2] + " pontos");
        System.out.print("DemiGod: ");
        System.out.println(bestResults[3] + " pontos\n");
    }

    public static void tipsMenu(int guessedNumber, int secretNumber, int difficulty) {
        if (gameOptionTips.equals("s")) {
            while(true) {
                System.out.print("Quer uma dica? (Sim - S/Não - N): ");
                String tipOption = scanner.nextLine().toLowerCase();

                if (tipOption.equals("s")) {
                    pointsUsedOnTips += tipSystem(secretNumber, difficulty, guessedNumber);
                    break;
                } else if (tipOption.equals("n")) {
                    System.out.println("Então vai lá bomzão");
                    break;
                } else {
                    System.out.println("Digite uma opção válida!!");
                }
            }
        }
    }

    public static int tipSystem(int secretNumber, int difficulty, int guessedNumber) {
        int pointsUsed = 0;
        boolean continueVar = true;
        while(continueVar) {
            System.out.println("\n------------Dicas------------");
            System.out.println("1. Pariedade (Par/Ímpar) / -10 pontos");
            System.out.println("2. Intervalo (Metade Inferior/Superior) / -20 pontos");
            System.out.println("3. Proximidade (Quente/Frio) / -15 pontos");
            System.out.println("0. Sair");
            System.out.print("Digite sua opção: ");
            if (scanner.hasNextInt()) {
                int tipOptions = Integer.parseInt(scanner.nextLine());

                switch (tipOptions) {
                    case 1 -> {
                        pointsUsed += 10;
                        if (secretNumber % 2 == 0) {
                            System.out.println("\nO número é Par!");
                        } else {
                            System.out.println("\nO número é Ímpar!");
                        }
                    }
                    case 2 -> {
                        pointsUsed += 20;
                        if (secretNumber > (difficulty/2)) {
                            System.out.println("\nO número está na metade Superior!");
                        } else {
                            System.out.println("\nO número está na metade Inferior!");
                        }
                    }
                    case 3 -> {
                        pointsUsed += 15;
                        if (Math.abs(secretNumber - guessedNumber) <= 10) {
                            System.out.println("\nEstá quente!");
                        } else {
                            System.out.println("\nEstá frio...");
                        }
                    }
                    case 0 -> continueVar = false;
                    default -> System.out.println("Escolha uma opção válida!!");
                }
            } else {
                System.out.println("\nDigite uma Entrada Válida!!\n");
                scanner.next();
            }
        } return pointsUsed;
    }

    public static void gameOptions() {
        while(true) {
            System.out.print("\nQuer dicas em seu jogo (Sim - S/Não - N): ");
            gameOptionTips = scanner.nextLine().toLowerCase();

            if (gameOptionTips.equals("s") || gameOptionTips.equals("n")) {
                System.out.println("Opções de Jogo Atualizadas.\n");
                break;
            } else {
                System.out.println("Digite uma opção válida!!");
            }
        }
    }
}
