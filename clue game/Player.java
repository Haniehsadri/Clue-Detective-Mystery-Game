/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miantermproject;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Asus
 */
enum Gun {
    achar, tanab, mileahani, chaghoo, biil, tiigh
}

enum Character {
    scarlet, plum, peacock, mustard, green, orchid
}

enum Room {
    piano, golkhane, bilyard, ketabkhane, motalee, paziraiee, khab, ghazakhori, ashpazkhane
}

public class Player {

    static int numberOfPlayersSoFar = 0;
    int idOfPlayer;
    int positionOfPlayerInMap = 0;
    Card[] cards;
    Boolean realPlayer;
    ArrayList<Card> seenRoomsSoFar = new ArrayList();
    ArrayList<Card> seenGunsSoFar = new ArrayList();
    ArrayList<Card> seenCharactersSoFar = new ArrayList();

    public Player(Boolean realPlayer) {
        numberOfPlayersSoFar++;
        idOfPlayer = numberOfPlayersSoFar;
        this.realPlayer = realPlayer;
    }

    public void giveCardsToPlayer(Card[] cards) {
        this.cards = cards;
        if (realPlayer) {
            for (int i = 0; i < cards.length; i++) {
                if (cards[i].type == Type.Room) {
                    seenRoomsSoFar.add(cards[i]);
                }
                if (cards[i].type == Type.Gun) {
                    seenGunsSoFar.add(cards[i]);
                }
                if (cards[i].type == Type.Character) {
                    seenCharactersSoFar.add(cards[i]);
                }
            }
        }
    }

    public void hadsZadan() {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        //In ghesmate code marboot be zamni hast ke player yek karbare vagheiee bashad
        if (realPlayer == true) {
            System.out.println("Hamaknoon nobate shomast!");
            namayesheKartHayeDideShode();
            int firstDice = random.nextInt(6) + 1;
            int secondDice = random.nextInt(6) + 1;
            int totalOfTwoDices = firstDice + secondDice;
            int shomareOtagh = 0;
            if (totalOfTwoDices % 2 == 0) {
                while (true) {
                    if (positionOfPlayerInMap != 0) {
                        System.out.print("Shoma ham aknoon dar otaghe shomareye " + positionOfPlayerInMap + " gharar darid.");
                    }
                    System.out.println(" Adade taas zoj shod, pas yek addade zoj vared konid ke marboot be otaghi hast ke mikhaieed be an harekat konid: ");
                    shomareOtagh = input.nextInt();
                    if (shomareOtagh % 2 == 1) {
                        System.out.println("Shoma be eshtebah yek addade fard vared kardid");
                        continue;
                    }
                    if (shomareOtagh < 1 || shomareOtagh > 9) {
                        System.out.println("Shoma be eshtebah yek addad kharej az bazeye 1 ta 9 entekhab kardid");
                        continue;
                    }
                    if (positionOfPlayerInMap != 0 && ((shomareOtagh == positionOfPlayerInMap + 1) || (shomareOtagh == positionOfPlayerInMap - 1) || (shomareOtagh == positionOfPlayerInMap))) {
                        System.out.println("Shoma ham aknoon dar otaghe shomareye " + positionOfPlayerInMap + " gharar darid va be eshtebah otaghe mojaver be in otagh ya haman otagh ra entekhab kardid");
                        continue;
                    }
                    positionOfPlayerInMap = shomareOtagh;
                    break;
                }
            }
            if (totalOfTwoDices % 2 == 1) {
                while (true) {
                    if (positionOfPlayerInMap != 0) {
                        System.out.print("Shoma ham aknoon dar otaghe shomareye " + positionOfPlayerInMap + " gharar darid.");
                    }
                    System.out.println(" Adade taas fard shod, pas yek addade fard vared konid ke marboot be otaghi hast ke mikhaieed be an harekat konid: ");
                    shomareOtagh = input.nextInt();
                    if (shomareOtagh % 2 == 0) {
                        System.out.println("Shoma be eshtebah yek addade zoj vared kardid");
                        continue;
                    }
                    if (shomareOtagh < 1 || shomareOtagh > 9) {
                        System.out.println("Shoma be eshtebah yek addad kharej az bazeye 1 ta 9 entekhab kardid");
                        continue;
                    }
                    if (positionOfPlayerInMap != 0 && ((shomareOtagh == positionOfPlayerInMap + 1) || (shomareOtagh == positionOfPlayerInMap - 1) || (shomareOtagh == positionOfPlayerInMap))) {
                        System.out.println("Shoma ham aknoon dar otaghe shomareye " + positionOfPlayerInMap + " gharar darid va be eshtebah otaghe mojaver be in otagh ya haman otagh ra entekhab kardid");
                        continue;
                    }
                    positionOfPlayerInMap = shomareOtagh;
                    break;
                }
            }
            Card room = Main.mapOfGame[shomareOtagh];
            System.out.println("Shoma varede otaghe " + room.name + " shodid, hamaknoon shoma bayad hads bezanid va yek maznoon va aslahe entekhab konid:");
            String gunName;
            while (true) {
                System.out.println("Slahe ra vared konid:");
                gunName = input.next();
                gunName = gunName.toLowerCase();
                if (containsInGuns(gunName) == false) {
                    System.out.println("slaheiee ba chenin nami vojood nadarad");
                    continue;
                }
                break;
            }
            Card gun = null;
            for (int i = 0; i < Main.ChoicesForGun.length; i++) {
                if (Main.ChoicesForGun[i].name == Name.valueOf(gunName)) {
                    gun = Main.ChoicesForGun[i];
                    break;
                }
            }
            String characterName;
            while (true) {
                System.out.println("Maznoon ra vared konid:");
                characterName = input.next();
                characterName = characterName.toLowerCase();
                if (containsInCharacters(characterName) == false) {
                    System.out.println("Maznooni ba chenin nami vojood nadarad");
                    continue;
                }
                break;
            }
            Card character = null;
            for (int i = 0; i < Main.ChoicesForCharacter.length; i++) {
                if (Main.ChoicesForCharacter[i].name == Name.valueOf(characterName)) {
                    character = Main.ChoicesForCharacter[i];
                    break;
                }
            }
            pasokhDadanBePorsesh(room, gun, character);
            System.out.println("Agar ghasd darid ke hadse nahaiee ra bezanid 1 ra vared konid va dar gheire insoorat 0 ra vared konid: ");
            if (input.nextInt() == 1) {
                String roomName;
                while (true) {
                    System.out.println("Otagh ra vared konid:");
                    roomName = input.next();
                    if (containsInRooms(roomName) == false) {
                        System.out.println("Otaghi ba chenin nami vojood nadarad");
                        continue;
                    }
                    break;
                }
                room = null;
                for (int i = 0; i < Main.ChoicesForRoom.length; i++) {
                    if (Main.ChoicesForRoom[i].name == Name.valueOf(roomName)) {
                        room = Main.ChoicesForRoom[i];
                        break;
                    }
                }
                while (true) {
                    System.out.println("Slahe ra vared konid:");
                    gunName = input.next();
                    if (containsInGuns(gunName) == false) {
                        System.out.println("slaheiee ba chenin nami vojood nadarad");
                        continue;
                    }
                    break;
                }
                gun = null;
                for (int i = 0; i < Main.ChoicesForGun.length; i++) {
                    if (Main.ChoicesForGun[i].name == Name.valueOf(gunName)) {
                        gun = Main.ChoicesForGun[i];
                        break;
                    }
                }
                while (true) {
                    System.out.println("Maznoon ra vared konid:");
                    characterName = input.next();
                    if (containsInCharacters(characterName) == false) {
                        System.out.println("Maznooni ba chenin nami vojood nadarad");
                        continue;
                    }
                    break;
                }
                character = null;
                for (int i = 0; i < Main.ChoicesForCharacter.length; i++) {
                    if (Main.ChoicesForCharacter[i].name == Name.valueOf(characterName)) {
                        character = Main.ChoicesForCharacter[i];
                        break;
                    }
                }
                if (Main.resultRoom.name == room.name && Main.resultGun.name == gun.name && Main.resultCharacter.name == character.name) {
                    System.out.println("Hadse shoma dorost hast. Shoma barande shodid. Javabe dorost be sharhe zir hast");
                    System.out.println("{ " + room.name + ", " + gun.name + ", " + character.name + " }");
                    System.exit(0);
                } else {
                    System.out.println("Hadse shoma dorost nabood. Shoma az bazi kharej mishavid");
                    System.exit(0);
                }
            }
        }
        //in ghesmate code baraye zamani hast ke computer be onvane bazikon bazi mikonad
        if (realPlayer == false) {
            System.out.println("Hamaknoon nobate bazikone shomareye " + idOfPlayer + " hast!");
            int firstDice = random.nextInt(6) + 1;
            int secondDice = random.nextInt(6) + 1;
            int totalOfTwoDices = firstDice + secondDice;
            int shomareOtagh = 0;
            if (totalOfTwoDices % 2 == 0) {
                while (true) {
                    shomareOtagh = random.nextInt(9) + 1;
                    if (shomareOtagh % 2 == 1 || shomareOtagh == positionOfPlayerInMap || shomareOtagh == positionOfPlayerInMap + 1 || shomareOtagh == positionOfPlayerInMap - 1) {
                        continue;
                    }
                    break;
                }
            }
            if (totalOfTwoDices % 2 == 1) {
                while (true) {
                    shomareOtagh = random.nextInt(9) + 1;
                    if (shomareOtagh % 2 == 0 || shomareOtagh == positionOfPlayerInMap || shomareOtagh == positionOfPlayerInMap + 1 || shomareOtagh == positionOfPlayerInMap - 1) {
                        continue;
                    }
                    break;
                }
            }
            Card room = Main.mapOfGame[shomareOtagh];
            Card gun = Main.ChoicesForGun[random.nextInt(Main.ChoicesForGun.length)];
            Card character = Main.ChoicesForCharacter[random.nextInt(Main.ChoicesForCharacter.length)];
            System.out.println("Hads haye bazikone shomareye " + idOfPlayer + " baraye otagh, aslahe, va maznoon be tartib be sharhe zir hast:");
            System.out.println("{ " + room.name + ", " + gun.name + ", " + character.name + " }");
            pasokhDadanBePorsesh(room, gun, character);
        }
    }

    public void namayesheKartHayeDideShode() {
        System.out.println("cart haiee ke dar daste shomast be alaveye cart haiee ke dar dast bazikonan digar ta konoon moshahede kardid be sharhe zir hast:");
        System.out.print("Otagh ha: { ");
        for (int i = 0; i < seenRoomsSoFar.size(); i++) {
            System.out.print(seenRoomsSoFar.get(i).name);
            if (i != seenRoomsSoFar.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" }");
        System.out.print("Maznoon ha: { ");
        for (int i = 0; i < seenCharactersSoFar.size(); i++) {
            System.out.print(seenCharactersSoFar.get(i).name);
            if (i != seenCharactersSoFar.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" }");
        System.out.print("Aslahe ha: { ");
        for (int i = 0; i < seenGunsSoFar.size(); i++) {
            System.out.print(seenGunsSoFar.get(i).name);
            if (i != seenGunsSoFar.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" }");
    }

    public boolean containsInGuns(String str) {

        for (Gun gun : Gun.values()) {
            if (gun.name().equals(str)) {
                return true;
            }
        }

        return false;
    }

    public boolean containsInCharacters(String str) {

        for (Character character : Character.values()) {
            if (character.name().equals(str)) {
                return true;
            }
        }

        return false;
    }

    public boolean containsInRooms(String str) {

        for (Room room : Room.values()) {
            if (room.name().equals(str)) {
                return true;
            }
        }

        return false;
    }

    //pasokh dadan be porseshe hads ha tavassote bazikonane digar
    public void pasokhDadanBePorsesh(Card room, Card gun, Card character) {
        int indexOfThisPlayer = -1;
        for (int i = 0; i < Main.players.length; i++) {
            if (Main.players[i].idOfPlayer == this.idOfPlayer) {
                indexOfThisPlayer = i;
                break;
            }
        }
        for (int i = indexOfThisPlayer + 1; i < Main.players.length; i++) {
            Card moshabeh = searchInCards(Main.players[i].cards, room, gun, character);
            if (moshabeh != null) {
                if (this.realPlayer == true) {
                    System.out.println("Bazikone shomareye " + Main.players[i].idOfPlayer + " hadeaghal yeki az hads haye shoma ke " + moshabeh.name + " mibashad ra dar cart haye khod darad");
                    if (moshabeh.type == Type.Room && !isSameCardInArray(seenRoomsSoFar, moshabeh)) {
                        seenRoomsSoFar.add(moshabeh);
                    }
                    if (moshabeh.type == Type.Gun && !isSameCardInArray(seenGunsSoFar, moshabeh)) {
                        seenGunsSoFar.add(moshabeh);
                    }
                    if (moshabeh.type == Type.Character && !isSameCardInArray(seenCharactersSoFar, moshabeh)) {
                        seenCharactersSoFar.add(moshabeh);
                    }
                    return;
                } else {
                    System.out.println("Bazikone shomareye " + Main.players[i].idOfPlayer + " hadeaghal yeki az in cart ha ra darad ");
                    return;
                }
            }
        }
        for (int i = 0; i < indexOfThisPlayer; i++) {
            Card moshabeh = searchInCards(Main.players[i].cards, room, gun, character);
            if (moshabeh != null) {
                if (this.realPlayer == true) {
                    System.out.println("Bazikone shomareye " + Main.players[i].idOfPlayer + " hadeaghal yeki az cart haye shoma ke " + moshabeh.name + " mibashad ra dar cart haye khod darad");
                    if (moshabeh.type == Type.Room && !isSameCardInArray(seenRoomsSoFar, moshabeh)) {
                        seenRoomsSoFar.add(moshabeh);
                    }
                    if (moshabeh.type == Type.Gun && !isSameCardInArray(seenGunsSoFar, moshabeh)) {
                        seenGunsSoFar.add(moshabeh);
                    }
                    if (moshabeh.type == Type.Character && !isSameCardInArray(seenCharactersSoFar, moshabeh)) {
                        seenCharactersSoFar.add(moshabeh);
                    }
                    return;
                } else {
                    System.out.println("Bazikone shomareye " + Main.players[i].idOfPlayer + " hadeaghal yeki az in cart ha ra darad ");
                    return;
                }
            }
        }
        if (realPlayer == true) {
            System.out.println("Hich kasi hich kodoom az hads haye shoma ra dar cart hayash nadarad");
        } else {
            System.out.println("Hich kasi hich kodoom az hads haye bazikone shomareye " + idOfPlayer + " ra dar cart hayash nadarad");
        }
    }

    // in tabe komak mikonad ta dar yek daste card marboot be yek bazikon search konim va bebinim ke hads ha dar an daste card vojood darand ya na
    public Card searchInCards(Card[] cards, Card room, Card gun, Card character) {
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].name == room.name) {
                return room;
            }
            if (cards[i].name == gun.name) {
                return gun;
            }
            if (cards[i].name == character.name) {
                return character;
            }
        }
        return null;
    }

    //This method checks if there is a card in the arraylist with the same name or not
    public boolean isSameCardInArray(ArrayList<Card> arraylist, Card card) {
        for (int i = 0; i < arraylist.size(); i++) {
            if (arraylist.get(i).name == card.name) {
                return true;
            }
        }
        return false;
    }
}
