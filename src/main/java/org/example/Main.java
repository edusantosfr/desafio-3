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
            String difficultyInFull = "";
            if (matchDifficulty == 1) {
                difficultyInFull = "Fácil";
            } else if (matchDifficulty == 2) {
                difficultyInFull = "Médio";
            } else if (matchDifficulty == 3) {
                difficultyInFull = "Difícil";
            } else if (matchDifficulty == 4) {
                difficultyInFull = "Demi God";
            }
            System.out.println("Pontos: " + matchPoints + " / Dificuldade: " + difficultyInFull);
        }
    }

    public static void main(String[] args) {

        while (true) {
            int option;
            System.out.println("-------Menu-------");
            System.out.println("1. Iniciar Novo Jogo");
            System.out.println("2. Ver Regras");
            System.out.println("3. Ver Histórico de Pontuação");
            System.out.println("4. Ver Melhores Pontuações");
            System.out.println("5. Opções de Jogo");
            System.out.println("0. Sair");

            System.out.print("Digite sua Opção: ");
            option = Integer.parseInt(scanner.nextLine());

            if (option == 1) {
                while (true) {
                    int difficulty;
                    System.out.println("\nEscolha seu nível de dificuldade entre:");
                    System.out.println("1. Fácil");
                    System.out.println("2. Médio");
                    System.out.println("3. Difícil");
                    System.out.println("4. (Modo Secreto) DemiGod");

                    System.out.print("Digite sua Opção: ");
                    difficulty = Integer.parseInt(scanner.nextLine());

                    if (difficulty > 0 && difficulty <= 4) {
                        playNewGame(difficulty);
                        break;
                    } else {
                        System.out.println("\nDigite uma opção válida!!");
                    }
                }
            } else if (option == 2) {
                rules();
            } else if (option == 3) {
                resultList();
            } else if (option == 4) {
                bestMatches();
            } else if (option == 5) {
                gameOptions();
            } else if (option == 0) {
                break;
            }
        }
    }

    public static void playNewGame(int dif) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int difficulty = 0;
        int guess = 0;
        int points = 0;

        if (dif == 1) {
            difficulty = difEasy[0];
            guess = difEasy[1];
            points = difEasy[2];
        } else if (dif == 2) {
            difficulty = difMedium[0];
            guess = difMedium[1];
            points = difMedium[2];
        } else if (dif == 3) {
            difficulty = difHard[0];
            guess = difHard[1];
            points = difHard[2];
        } else if (dif == 4) {
            difficulty = difDemiGod[0];
            guess = difDemiGod[1];
            points = difDemiGod[2];
        }

        int[] secretNumber = new int[3];
        for (int i = 0; i < secretNumber.length; i++) {
            secretNumber[i] = random.nextInt(1, difficulty);
        }
        for (int j : secretNumber) {
            System.out.println(j);
        }

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
                guessedNumber = Integer.parseInt(scanner.nextLine());

                if (guessedNumber == secretNumber[0]) {
                    result = true;
                    break;
                } else {
                    System.out.println("Tente Novamente\n");
                    if (gameOptionTips.equals("s")) {
                        while(true) {
                            System.out.print("Quer uma dica? (Sim - S/Não - N): ");
                            String tipOption = scanner.nextLine().toLowerCase();

                            if (tipOption.equals("s")) {
                                tipSystem();
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
                pointsGuesses++;
            }
        } else {
            System.out.println("\n5eja Bem V1nd0(a) a0 jog0 de Ad1v1nhaçã0... :(");
            System.out.println("Boa Sorte, você definitivamente vai precisar...");

            for (firstIndice = 0; firstIndice < guess; firstIndice++) {
                System.out.println((firstIndice + 1) + "/" + guess + " tentativas");
                System.out.print("Digite um número de um 1 à " + difficulty + ": ");
                guessedNumber = Integer.parseInt(scanner.nextLine());

                if (guessedNumber == secretNumber[0]) {
                    System.out.println("\nVocê Acertou!!");
                    break;
                }
                pointsGuesses++;
            }
            for (secondIndice = firstIndice + 1; secondIndice < guess; secondIndice++) {
                System.out.println((secondIndice + 1) + "/" + guess + " tentativas");
                System.out.print("Digite um número de um 1 à " + difficulty + ": ");
                guessedNumber = Integer.parseInt(scanner.nextLine());

                if (guessedNumber == secretNumber[1]) {
                    System.out.println("\nVocê Acertou!!");
                    break;
                }
                pointsGuesses++;
            }
            for (thirdIndice = secondIndice + 1; thirdIndice < guess; thirdIndice++) {
                System.out.println((thirdIndice + 1) + "/" + guess + " tentativas");
                System.out.print("Digite um número de um 1 à " + difficulty + ": ");
                guessedNumber = Integer.parseInt(scanner.nextLine());

                if (guessedNumber == secretNumber[2]) {
                    result = true;
                    break;
                }
                pointsGuesses++;
            }
        }

        int matchPoints;
        if (result) {
            matchPoints = points + ((guess - thirdIndice - 1) * 50) - (pointsGuesses * 10);
            totalPoints += matchPoints;
            System.out.println("\nVocê Acertou!!");
        } else {
            matchPoints = 0;
            System.out.println("\nVocê Não Acertou...");
        }

        if (matchList.size() < 10) {
            matchList.add(new MatchResultObj(matchPoints, dif));
        } else {
            matchList.remove(0);
            matchList.add(new MatchResultObj(matchPoints, dif));
        }

        if (dif == 1 && bestResults[0] < matchPoints) {
            bestResults[0] = matchPoints;
        } else if (dif == 2 && bestResults[1] < matchPoints) {
            bestResults[1] = matchPoints;
        } else if (dif == 3 && bestResults[2] < matchPoints) {
            bestResults[2] = matchPoints;
        } else if (dif == 4 && bestResults[3] < matchPoints) {
            bestResults[3] = matchPoints;
        }

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

    public static void tipSystem() {

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