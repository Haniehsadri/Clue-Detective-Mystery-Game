/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miantermproject;

/**
 *
 * @author Asus
 */
enum Name {
    achar, tanab, mileahani, chaghoo, biil, tiigh, piano, golkhane, bilyard, ketabkhane, motalee, paziraiee, khab, ghazakhori, ashpazkhane, scarlet, plum, peacock, mustard, green, orchid;
}

enum Type {
    Character, Gun, Room;
}

public class Card {

    Name name;
    Type type;

    public Card(Name name, Type type) {
        this.name = name;
        this.type = type;
    }

}
