package Business;

import Presentation.Input;
import Presentation.Output;

import java.util.ArrayList;
import Business.CharacterManager;

public class MemberManager {

    public ArrayList<Member> getMembers() {
        ArrayList<Member> members = new ArrayList<>();
        CharacterManager characterManager = new CharacterManager();
        Input input = new Input();

        for (int i = 1; i <= 4; i++) {
            String member = input.askString("\nPlease enter name or id for character #" + i + ": ");
            Character character = characterManager.getCharacterByIdOrName(member);
            if (character == null) {
                Output.printPhrase("\nCharacter #" + i + " does not exist");
                i--;
            } else {
                String strategy = input.askStrategy(i);
                Member member1 = new Member(character.getId(), strategy);
                member1.setName(character.getName());
                members.add(member1);
            }
        }
        return members;
    }

}
