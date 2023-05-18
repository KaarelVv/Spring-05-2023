//package com.kaarel.playtech;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//@RestController
//public class CardController {
//    private Cards cards = new Cards();
//    private Random random = new Random();
//    private int remainingGuesses;
//    private String baseCard;
//    private String guessCard;
//    private List<String> allGuesses = new ArrayList<>();
//
//
//
//    @GetMapping("cards")
//    public String getCards(@RequestParam String index) {
//
//        if (remainingGuesses == 10) {
//            int randomIndex = random.nextInt(cards.getCardNumber().size());
//            baseCard = cards.getCardNumber().get(randomIndex);
//        }
//        if (remainingGuesses == 0)
//            return "Game over! All guesses used" + allGuesses;
//
//        if (index == null || index.isEmpty()) {
//            guessCard = cards.getCardNumber().get(Integer.parseInt(index));
//            allGuesses.add(guessCard);
//
//            if (guessCard.equals(baseCard)) {
//                remainingGuesses = 0;
//                return "Correct card";
//            } else {
//                remainingGuesses--;
//                if (remainingGuesses == 0) {
//                    return "Game over! Out of guesses";
//                }
//                return "Wrong guess" + getHint();
//            }
//        }
//        return "You base card is: " + baseCard;
//    }
//    public String getHint(){
//        if(Integer.parseInt(guessCard) > Integer.parseInt(baseCard)) {
//            return "Too big!";
//        }else
//            return "Too small";
//    }
//
//}
//
////    int providedIndex = Integer.parseInt(index);
////
////        if(providedIndex == Integer.parseInt(randomElement)){
////            return "Same card";
////        }
////        else if(providedIndex > Integer.parseInt(randomElement) ) {
////            return " Too big ";
////        }else
////            return "Too small" ;
////    }
