package basicStuff.Static;

public class MainS {

    // static - modifier. A single copy of a variable(field)/method is created  and shared.
    //              The class "owns" the static member.
    public static void main(String[] args) {

        Friend friend1 = new Friend("Patrick");
        Friend friend2 = new Friend("SpongeBob");
        Friend friend3 = new Friend("Squid");
        Friend friend4 = new Friend("Sandy");


        System.out.println(Friend.numberOfFriends); // Friend is class name and numberOfFriends is static field
        System.out.println(friend1); //as you can see you cannot call static field and print it out


        Friend.displayFriends(); // static method to display friends
    }
}
