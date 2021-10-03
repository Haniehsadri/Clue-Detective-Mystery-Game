/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miantermproject;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static Card[] mapOfGame = new Card[10];
    static Card[] ChoicesForRoom = new Card[9];
    static Card[] ChoicesForGun = new Card[6];
    static Card[] ChoicesForCharacter = new Card[6];
    static Card resultRoom;
    static Card resultGun;
    static Card resultCharacter;
    static Player[] players;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Tedade sherkat konande ha ro vared konid:");
        int numberOfPlayers = input.nextInt();
        if (numberOfPlayers < 3 || numberOfPlayers > 6) {
            System.out.println("Voroudi eshtebah hast. Tedade sherkat konande ha bayad beine 3 va 6 bashad");
            System.exit(0);
        }
        players = new Player[numberOfPlayers];
        players[0] = new Player(true);
        for (int i = 1; i < numberOfPlayers; i++) {
            players[i] = new Player(false);
        }
        shuffleArayOfPlayers(players);
        ChoicesForRoom[0] = new Card(Name.piano, Type.Room);
        ChoicesForRoom[1] = new Card(Name.golkhane, Type.Room);
        ChoicesForRoom[2] = new Card(Name.bilyard, Type.Room);
        ChoicesForRoom[3] = new Card(Name.ketabkhane, Type.Room);
        ChoicesForRoom[4] = new Card(Name.motalee, Type.Room);
        ChoicesForRoom[5] = new Card(Name.paziraiee, Type.Room);
        ChoicesForRoom[6] = new Card(Name.khab, Type.Room);
        ChoicesForRoom[7] = new Card(Name.ghazakhori, Type.Room);
        ChoicesForRoom[8] = new Card(Name.ashpazkhane, Type.Room);
        ChoicesForGun[0] = new Card(Name.achar, Type.Gun);
        ChoicesForGun[1] = new Card(Name.tanab, Type.Gun);
        ChoicesForGun[2] = new Card(Name.mileahani, Type.Gun);
        ChoicesForGun[3] = new Card(Name.chaghoo, Type.Gun);
        ChoicesForGun[4] = new Card(Name.biil, Type.Gun);
        ChoicesForGun[5] = new Card(Name.tiigh, Type.Gun);
        ChoicesForCharacter[0] = new Card(Name.scarlet, Type.Character);
        ChoicesForCharacter[1] = new Card(Name.plum, Type.Character);
        ChoicesForCharacter[2] = new Card(Name.peacock, Type.Character);
        ChoicesForCharacter[3] = new Card(Name.mustard, Type.Character);
        ChoicesForCharacter[4] = new Card(Name.green, Type.Character);
        ChoicesForCharacter[5] = new Card(Name.orchid, Type.Character);
        Card[] ChoicesForRoom2 = new Card[9];
        Card[] ChoicesForGun2 = new Card[6];
        Card[] ChoicesForCharacter2 = new Card[6];
        for (int i = 0; i < ChoicesForRoom.length; i++) {
            ChoicesForRoom2[i] = ChoicesForRoom[i];
        }
        for (int i = 0; i < ChoicesForCharacter.length; i++) {
            ChoicesForCharacter2[i] = ChoicesForCharacter[i];
        }
        for (int i = 0; i < ChoicesForGun.length; i++) {
            ChoicesForGun2[i] = ChoicesForGun[i];
        }
        shuffleArayOfCards(ChoicesForRoom2);
        shuffleArayOfCards(ChoicesForGun2);
        shuffleArayOfCards(ChoicesForCharacter2);
        Random rand = new Random();
        int resultIndexRoom = rand.nextInt(ChoicesForRoom2.length);
        int resultIndexGun = rand.nextInt(ChoicesForGun2.length);
        int resultIndexCharacter = rand.nextInt(ChoicesForCharacter2.length);
        resultRoom = ChoicesForRoom2[resultIndexRoom];
        resultGun = ChoicesForGun2[resultIndexGun];
        resultCharacter = ChoicesForCharacter2[resultIndexCharacter];
        Card[] totalCardsForDistributing = new Card[18];
        int j = 0;
        for (int i = 0; i < ChoicesForRoom2.length; i++) {
            if (i != resultIndexRoom) {
                totalCardsForDistributing[j] = ChoicesForRoom2[i];
                j++;
            }
        }
        for (int i = 0; i < ChoicesForGun2.length; i++) {
            if (i != resultIndexGun) {
                totalCardsForDistributing[j] = ChoicesForGun2[i];
                j++;
            }
        }
        for (int i = 0; i < ChoicesForCharacter2.length; i++) {
            if (i != resultIndexCharacter) {
                totalCardsForDistributing[j] = ChoicesForCharacter2[i];
                j++;
            }
        }
        shuffleArayOfCards(totalCardsForDistributing);
        distributeCardsBetweenPlayers(totalCardsForDistributing, players);
        mapOfGame[1] = new Card(Name.paziraiee, Type.Room);
        mapOfGame[2] = new Card(Name.piano, Type.Room);
        mapOfGame[3] = new Card(Name.golkhane, Type.Room);
        mapOfGame[4] = new Card(Name.motalee, Type.Room);
        mapOfGame[5] = new Card(Name.bilyard, Type.Room);
        mapOfGame[6] = new Card(Name.khab, Type.Room);
        mapOfGame[7] = new Card(Name.ghazakhori, Type.Room);
        mapOfGame[8] = new Card(Name.ketabkhane, Type.Room);
        mapOfGame[9] = new Card(Name.ashpazkhane, Type.Room);
        NamayesheKoleCartHa();
        while (true) {
            for (int i = 0; i < players.length; i++) {
                players[i].hadsZadan();
            }
        }
    }

    public static void shuffleArayOfCards(Card[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = rand.nextInt(array.length);
            Card temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
    }

    public static void shuffleArayOfPlayers(Player[] array) {
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = rand.nextInt(array.length);
            Player temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
    }

    public static void distributeCardsBetweenPlayers(Card[] totalCards, Player[] players) {
        if (players.length == 3) {
            Card[] cardsForPlayer1 = new Card[6];
            Card[] cardsForPlayer2 = new Card[6];
            Card[] cardsForPlayer3 = new Card[6];
            int j = 0;
            for (int i = 0; i < 6; i++) {
                cardsForPlayer1[i] = totalCards[j];
                j++;
            }
            for (int i = 0; i < 6; i++) {
                cardsForPlayer2[i] = totalCards[j];
                j++;
            }
            for (int i = 0; i < 6; i++) {
                cardsForPlayer3[i] = totalCards[j];
                j++;
            }
            players[0].giveCardsToPlayer(cardsForPlayer1);
            players[1].giveCardsToPlayer(cardsForPlayer2);
            players[2].giveCardsToPlayer(cardsForPlayer3);
        }
        if (players.length == 4) {
            Card[] cardsForPlayer1 = new Card[5];
            Card[] cardsForPlayer2 = new Card[5];
            Card[] cardsForPlayer3 = new Card[4];
            Card[] cardsForPlayer4 = new Card[4];
            int j = 0;
            for (int i = 0; i < 5; i++) {
                cardsForPlayer1[i] = totalCards[j];
                j++;
            }
            for (int i = 0; i < 5; i++) {
                cardsForPlayer2[i] = totalCards[j];
                j++;
            }
            for (int i = 0; i < 4; i++) {
                cardsForPlayer3[i] = totalCards[j];
                j++;
            }
            for (int i = 0; i < 4; i++) {
                cardsForPlayer4[i] = totalCards[j];
                j++;
            }
            players[0].giveCardsToPlayer(cardsForPlayer1);
            players[1].giveCardsToPlayer(cardsForPlayer2);
            players[2].giveCardsToPlayer(cardsForPlayer3);
            players[3].giveCardsToPlayer(cardsForPlayer4);
        }
        if (players.length == 5) {
            Card[] cardsForPlayer1 = new Card[4];
            Card[] cardsForPlayer2 = new Card[4];
            Card[] cardsForPlayer3 = new Card[4];
            Card[] cardsForPlayer4 = new Card[3];
            Card[] cardsForPlayer5 = new Card[3];
            int j = 0;
            for (int i = 0; i < 4; i++) {
                cardsForPlayer1[i] = totalCards[j];
                j++;
            }
            for (int i = 0; i < 4; i++) {
                cardsForPlayer2[i] = totalCards[j];
                j++;
            }
            for (int i = 0; i < 4; i++) {
                cardsForPlayer3[i] = totalCards[j];
                j++;
            }
            for (int i = 0; i < 3; i++) {
                cardsForPlayer4[i] = totalCards[j];
                j++;
            }
            for (int i = 0; i < 3; i++) {
                cardsForPlayer5[i] = totalCards[j];
                j++;
            }
            players[0].giveCardsToPlayer(cardsForPlayer1);
            players[1].giveCardsToPlayer(cardsForPlayer2);
            players[2].giveCardsToPlayer(cardsForPlayer3);
            players[3].giveCardsToPlayer(cardsForPlayer4);
            players[4].giveCardsToPlayer(cardsForPlayer5);
        }
        if (players.length == 6) {
            Card[] cardsForPlayer1 = new Card[3];
            Card[] cardsForPlayer2 = new Card[3];
            Card[] cardsForPlayer3 = new Card[3];
            Card[] cardsForPlayer4 = new Card[3];
            Card[] cardsForPlayer5 = new Card[3];
            Card[] cardsForPlayer6 = new Card[3];
            int j = 0;
            for (int i = 0; i < 3; i++) {
                cardsForPlayer1[i] = totalCards[j];
                j++;
            }
            for (int i = 0; i < 3; i++) {
                cardsForPlayer2[i] = totalCards[j];
                j++;
            }
            for (int i = 0; i < 3; i++) {
                cardsForPlayer3[i] = totalCards[j];
                j++;
            }
            for (int i = 0; i < 3; i++) {
                cardsForPlayer4[i] = totalCards[j];
                j++;
            }
            for (int i = 0; i < 3; i++) {
                cardsForPlayer5[i] = totalCards[j];
                j++;
            }
            for (int i = 0; i < 3; i++) {
                cardsForPlayer6[i] = totalCards[j];
                j++;
            }
            players[0].giveCardsToPlayer(cardsForPlayer1);
            players[1].giveCardsToPlayer(cardsForPlayer2);
            players[2].giveCardsToPlayer(cardsForPlayer3);
            players[3].giveCardsToPlayer(cardsForPlayer4);
            players[4].giveCardsToPlayer(cardsForPlayer5);
            players[5].giveCardsToPlayer(cardsForPlayer6);
        }
    }

    public static void NamayesheKoleCartHa() {
        System.out.println("Koliyeye cart ha be sharhe zir hast va shoma mitavanid hads hayetan ra az mian in cart ha entekhab konid:");
        System.out.print("Otagh ha: { ");
        for (int i = 0; i < ChoicesForRoom.length; i++) {
            System.out.print(ChoicesForRoom[i].name);
            if (i != ChoicesForRoom.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" }");
        System.out.print("Maznoon ha: { ");
        for (int i = 0; i < ChoicesForCharacter.length; i++) {
            System.out.print(ChoicesForCharacter[i].name);
            if (i != ChoicesForCharacter.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" }");
        System.out.print("Aslahe ha: { ");
        for (int i = 0; i < ChoicesForGun.length; i++) {
            System.out.print(ChoicesForGun[i].name);
            if (i != ChoicesForGun.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" }");
    }
}
